package thanhlongbanh8997.englishforeverybody

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.widget.Toast
import com.crashlytics.android.Crashlytics
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.content_home.*
import io.fabric.sdk.android.Fabric
import org.apache.commons.lang3.StringUtils
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.io.FileNotFoundException
import io.fabric.sdk.android.services.settings.IconRequest.build
import thanhlongbanh8997.englishforeverybody.adapter.ThemeAdapter
import thanhlongbanh8997.englishforeverybody.utils.Constants
import thanhlongbanh8997.englishforeverybody.R

class HomeActivity : BaseToolbarActivity(), NavigationView.OnNavigationItemSelectedListener, AnkoLogger {
    override var layout = R.layout.activity_home

    companion object {
        const val RESULT_LOAD_IMG = 99
    }

    private lateinit var themeAdapter: ThemeAdapter


    override fun initView() {
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        val mAdView = find<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

    }

    override fun initData() {
        val layoutManagerTheme = GridLayoutManager(this, 2)
        lvGridThemes.layoutManager = layoutManagerTheme
        themeAdapter = ThemeAdapter(this@HomeActivity)
        lvGridThemes.adapter = themeAdapter



        fabOpenGallery.onClick {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG)
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navFeedback -> {
                val intentFeedback = Intent(this, FeedbackActivity::class.java)
                startActivity(intentFeedback)
            }
            R.id.navAbout -> {
                val intentAbout = Intent(this, AboutMeActivity::class.java)
                startActivity(intentAbout)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    override fun onActivityResult(reqCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(reqCode, resultCode, data)


        if (resultCode == Activity.RESULT_OK) {
            try {
                val imageUri = data?.data
                info {"$imageUri"}

                if (imageUri != null) {
                    startActivity(
                            Intent(this@HomeActivity, EditorVocabularyActivity::class.java).apply {
                                putExtra(Constants.kGalleryUri, imageUri.toString())
                            }
                    )
                } else {
                    Toast.makeText(this, getString(R.string.toast_can_not_get_image), Toast.LENGTH_SHORT).show()
                }
                //val imageStream = contentResolver.openInputStream(imageUri!!)
                //val selectedImage = BitmapFactory.decodeStream(imageStream)
                //image_view.setImageBitmap(selectedImage)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                Toast.makeText(this@HomeActivity, "Something went wrong", Toast.LENGTH_LONG).show()
            }

        } else {
            Toast.makeText(this@HomeActivity, "You haven't picked Image", Toast.LENGTH_LONG).show()
        }
    }


}