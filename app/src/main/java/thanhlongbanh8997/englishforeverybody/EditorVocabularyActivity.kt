package thanhlongbanh8997.englishforeverybody

import android.app.Dialog
import android.app.ProgressDialog
import android.app.WallpaperManager
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import kotlinx.android.synthetic.main.activity_editor_vocabulary.*
import org.jetbrains.anko.*
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.info
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.io.IOException
import android.speech.RecognizerIntent
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.dialog_new_word.*
import org.json.JSONArray
import java.util.*
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.CardView
import android.util.Log
import android.widget.*
import kotlinx.android.synthetic.main.dialog_setting.*
import org.jetbrains.anko.sdk25.coroutines.onCheckedChange
import org.jetbrains.anko.sdk25.coroutines.onItemSelectedListener
import thanhlongbanh8997.englishforeverybody.adapter.WordAdapter
import thanhlongbanh8997.englishforeverybody.data.ThemeCatalog
import thanhlongbanh8997.englishforeverybody.model.Word
import thanhlongbanh8997.englishforeverybody.service.RequestAPI
import thanhlongbanh8997.englishforeverybody.service.ServiceAPI
import thanhlongbanh8997.englishforeverybody.utils.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream



class EditorVocabularyActivity: BaseToolbarActivity(), AnkoLogger {


    //Long dep trai
    override var layout: Int = R.layout.activity_editor_vocabulary

    companion object {
        const val REQUEST_CODE_KW = 6969
        const val REQUEST_CODE_MEAN = 6970
    }

    private lateinit var dialog: Dialog
    private lateinit var wordList: MutableList<Word>
    private lateinit var wordAdapter: WordAdapter
    private lateinit var layoutWallpaper: FrameLayout
    private lateinit var viewOpacity: ImageView
    private lateinit var layoutWordList: RelativeLayout
    private lateinit var cardViewVocabulary: CardView

    private var isUsingShadow = true

    private var tempTextColorIdx: Int = 0
    private var tempTextSizeIdx: Int = 8
    private var tempTextPaddingIdx: Int = 0


    override fun initView() {

        with(supportActionBar!!) {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        wordList = ArrayList()
        this.layoutWallpaper = find(R.id.layoutWallpaper)
        this.viewOpacity = find(R.id.viewOpacity)
        this.layoutWordList = find(R.id.layoutWordList)
        this.cardViewVocabulary = find(R.id.cardViewVocabulary)

        find<FloatingActionButton>(R.id.fabAddNew).onClick {
            showNewWordDialog()
        }

    }

    override fun initData() {

        //layoutWallpaper.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        if (intent.hasExtra(Constants.kThemePosition)) {
            val position = intent.extras[Constants.kThemePosition] as Int
            layoutWallpaper.backgroundResource = ThemeCatalog[position].resId
        } else {
            val imageUri = intent.extras[Constants.kGalleryUri] as String
            val imageStream = contentResolver.openInputStream(Uri.parse(imageUri))
            val dr = BitmapDrawable(resources, imageStream)
            layoutWallpaper.background = dr
        }
        layoutWallpaper.post {
            viewOpacity.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, layoutWallpaper.height)
            viewOpacity.requestLayout()
        }

        wordAdapter = WordAdapter(this, wordList)
        with (lvWordList) {
            layoutManager = LinearLayoutManager(this@EditorVocabularyActivity).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            isNestedScrollingEnabled = false
            isFocusable = false
            adapter = wordAdapter
        }

        this.cardViewVocabulary.post {
            val width = cardViewVocabulary.layoutParams.width
            cardViewVocabulary.layoutParams.height = width * 16 / 9
        }
    }

    private fun showNewWordDialog() {
        dialog = Dialog(this@EditorVocabularyActivity)
        with(dialog) {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.dialog_new_word)
            setCanceledOnTouchOutside(false)
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window.setLayout(CommonUtils.ScreenSize(context)[0] * 7 / 8, WindowManager.LayoutParams.WRAP_CONTENT)

            find<Button>(R.id.btnCancel).onClick { dismiss() }

            find<Button>(R.id.btnSubmit).onClick {
                wordList.add(Word(edtKeyword.text.toString(), edtType.text.toString(), edtMean.text.toString(), edtDescription.text.toString(), ArrayList()))
                wordAdapter.notifyDataSetChanged()
                dismiss()
            }

            btnVoiceKeyword.onClick { startVoiceRecognition(REQUEST_CODE_KW) }

            btnVoiceMean.onClick {  startVoiceRecognition(REQUEST_CODE_MEAN) }

            cbVonaSupport.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    if (edtKeyword.text.toString().isEmpty()) {
                        DialogUtils.showOKWarningDialog(this@EditorVocabularyActivity, null, R.string.dialog_edt_keyword_empty, null)
                        buttonView.isChecked = false
                    } else {
                        requestGoogleTranslate(edtKeyword.text.toString())
                    }

                }
            }

            show()
        }
    }

    private fun showDialogSetting() {
        val dialogSetting = Dialog(this@EditorVocabularyActivity)
        with(dialogSetting) {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.dialog_setting)
            setCanceledOnTouchOutside(true)

            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
            window.attributes.gravity = Gravity.BOTTOM

            val textColorList = resources.getStringArray(R.array.dialog_setting_colors)
            find<Spinner>(R.id.spinnerTextColor).setSelection(tempTextColorIdx)
            find<Spinner>(R.id.spinnerTextColor).onItemSelectedListener {
                onItemSelected { adapterView, view, i, l ->
                    tempTextColorIdx = i
                    val textColor = textColorList[i]
                    val colorHex = EditorVocabularySetting.colorHex(this@EditorVocabularyActivity, textColor)
                    wordAdapter.settingColor(colorHex)
                    wordAdapter.notifyDataSetChanged()
                }

            }


            find<CheckBox>(R.id.cbUsingShadow).apply {
                isChecked = this@EditorVocabularyActivity.isUsingShadow
                onCheckedChange { buttonView, isChecked ->
                    this@EditorVocabularyActivity.isUsingShadow = isChecked
                    if (isChecked) {
                        this@EditorVocabularyActivity.viewOpacity.visibility = View.VISIBLE
                    } else {
                        this@EditorVocabularyActivity.viewOpacity.visibility = View.GONE
                    }
                }
            }

            val textSizeList = resources.getStringArray(R.array.dialog_setting_text_size)
            find<Spinner>(R.id.spinnerTextSize).setSelection(tempTextSizeIdx)
            find<Spinner>(R.id.spinnerTextSize).onItemSelectedListener {
                onItemSelected { adapterView, view, i, l ->
                    tempTextSizeIdx = i
                    val textSize = textSizeList[i]
                    wordAdapter.settingTextSize(textSize.replace("sp", "").toInt())
                    wordAdapter.notifyDataSetChanged()
                }
            }

            find<Spinner>(R.id.spinnerTextPadding).setSelection(tempTextPaddingIdx)
            find<Spinner>(R.id.spinnerTextPadding).onItemSelectedListener {
                onItemSelected { adapterView, view, i, l ->
                    tempTextPaddingIdx = i
                    val textPadding = resources.getStringArray(R.array.dialog_setting_text_padding)[i]
                    val paddingSize = CommonUtils.dpToPx(this@EditorVocabularyActivity, textPadding.replace("dp", "").toInt())

                    this@EditorVocabularyActivity.layoutWordList.padding = paddingSize
                    this@EditorVocabularyActivity.layoutWordList.requestLayout()
                }
            }

            imvTextSizeMinus.onClick {
                tempTextSizeIdx--
                find<Spinner>(R.id.spinnerTextSize).setSelection(tempTextSizeIdx)
            }

            imvTextSizePlus.onClick {
                tempTextSizeIdx++
                find<Spinner>(R.id.spinnerTextSize).setSelection(tempTextSizeIdx)
            }

            imvTextPaddingMinus.onClick {
                tempTextPaddingIdx--
                find<Spinner>(R.id.spinnerTextPadding).setSelection(tempTextSizeIdx)
            }

            imvTextPaddingPlus.onClick {
                tempTextPaddingIdx++
                find<Spinner>(R.id.spinnerTextPadding).setSelection(tempTextSizeIdx)
            }


            show()
        }

    }

    private fun startVoiceRecognition(requestCode: Int) {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.dialog_voice_recognition))
        startActivityForResult(intent, requestCode)
    }

    private fun requestGoogleTranslate(keyword: String) {

        val lang = Locale.getDefault().language

        info { "Default lang = $lang" }

        val url = "${ServiceAPI.GOOGLE_TRANSLATE}?client=gtx&sl=auto&tl=$lang&dt=t&dt=md&hl=auto&q=${keyword.trim().replace(" ", "+")}"
        Log.e("BANHLONG", ServiceAPI.GOOGLE_TRANSLATE)
        val dialog = ProgressDialog.show(this@EditorVocabularyActivity, "", getString(R.string.dialog_loading_message), true)
        RequestAPI.get(this@EditorVocabularyActivity, url, null, dialog) {
            success, status, msg, data ->
            if (success) {
                info { data.toString() }
                val jsonArray = JSONArray(data)
                Log.e("BANHBEO", data)
                if (jsonArray.length() > 0) {
                    val jMean = jsonArray.getJSONArray(0)?.getJSONArray(0)
                    this.dialog.edtMean.setText(jMean?.get(0).toString())
                    if (jsonArray.length() >= 12) {
                        val jArrExplain = jsonArray.getJSONArray(12)
                        info { jArrExplain.toString() }
                        if (jArrExplain.length() > 0) {
                            this.dialog.edtType.setText(jArrExplain.getJSONArray(0)?.getString(0))
                            this.dialog.edtDescription.setText(jArrExplain.getJSONArray(0)?.getJSONArray(1)?.getJSONArray(0)?.getString(0))
                        }
                    }
                }
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_KW && resultCode == RESULT_OK) {
            val matches = data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if (matches.size > 0 && dialog != null && dialog.isShowing) {
                dialog.edtKeyword.setText(matches[0].toLowerCase())
            }
        }
        if (requestCode == REQUEST_CODE_MEAN && resultCode == RESULT_OK) {
            val matches = data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if (matches.size > 0 && dialog != null && dialog.isShowing) {
                dialog.edtMean.setText(matches[0].toLowerCase())
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.menuHomeScreen -> {
                item.isEnabled
                val progressDialog = ProgressDialog.show(this@EditorVocabularyActivity, "", getString(R.string.dialog_loading_message), true)

                val wallpaper = WallpaperManager.getInstance(this@EditorVocabularyActivity)
                val bitmap = ImageUtils.createBitmap(layoutWallpaper)
                try {
                    wallpaper.setBitmap(bitmap)
                    DialogUtils.showOkDialog(this@EditorVocabularyActivity, R.string.dialog_title_congratulations, R.string.dialog_btn_wallpaper_success, {
                        dialog, view ->
                        val startMain = Intent(Intent.ACTION_MAIN)
                        startMain.addCategory(Intent.CATEGORY_HOME)
                        startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(startMain)
                        dialog.dismiss()
                    })
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                item.isEnabled = true
                progressDialog.dismiss()
            }
            R.id.menuSetting -> {
                showDialogSetting()
            }
            R.id.menuLockScreen -> {
                item.isEnabled = false
                val progressDialog = ProgressDialog.show(this@EditorVocabularyActivity, "", getString(R.string.dialog_loading_message), true)

                //val wallpaper = WallpaperManager.getInstance(this@EditorVocabularyActivity, WallpaperManager.FLAG_LOCK)
                val bitmap = ImageUtils.createBitmap(layoutWallpaper)
                try {
                    val bos = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos)
                    val bitmapByte = bos.toByteArray()
                    val bs = ByteArrayInputStream(bitmapByte)

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        WallpaperManager.getInstance(this@EditorVocabularyActivity).setStream(bs, null, true, WallpaperManager.FLAG_LOCK)
                    }

                    DialogUtils.showOkDialog(this@EditorVocabularyActivity, R.string.dialog_title_congratulations, R.string.dialog_btn_wallpaper_success, {
                        dialog, view ->
                        val pm: PowerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
                        val wl: PowerManager.WakeLock =  pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "SET_HOME_SCREEN")
                        wl.acquire()
                        wl.release()

                        dialog.dismiss()
                    })
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                item.isEnabled = true
                progressDialog.dismiss()
            }

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_editor_vocabulary, menu)
        return true
    }

}

