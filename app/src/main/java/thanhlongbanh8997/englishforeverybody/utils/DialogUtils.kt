package thanhlongbanh8997.englishforeverybody.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.dialog_default.*
import org.apache.commons.lang3.StringUtils
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import thanhlongbanh8997.englishforeverybody.R

class DialogUtils: AnkoLogger{

    companion object {
        @JvmStatic
        fun showOkDialog(context: Context, title: String?,
                         message: String, isCancel: Boolean, isWarning: Boolean, listener: ((Dialog, View) -> Unit)?) {
            val dialog = Dialog(context)
            with(dialog) {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setContentView(R.layout.dialog_default)
                setCanceledOnTouchOutside(true)
                window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                window!!.setLayout(CommonUtils.ScreenSize(context)[0] * 7 / 8, WindowManager.LayoutParams.WRAP_CONTENT)


                find<TextView>(R.id.tvTitle).apply {
                    visibility = if (StringUtils.isNotBlank(title)) View.VISIBLE else View.GONE
                    text = title
                }
                find<TextView>(R.id.tvDescription).text = message
                find<Button>(R.id.btnCancel).apply {
                    visibility = if(!isCancel) View.GONE else View.VISIBLE
                    onClick {
                        dismiss()
                    }
                }

                if (isWarning) {
                    imvNotifyIconFail.visibility = View.VISIBLE
                    imvNotifyIconSuccess.visibility = View.GONE
                } else {
                    imvNotifyIconFail.visibility = View.GONE
                    imvNotifyIconSuccess.visibility = View.VISIBLE
                }

                find<Button>(R.id.btnSubmit).onClick {
                    if (listener == null) {
                        dismiss()
                    } else {
                        listener(dialog, it!!)
                    }
                }

                try {
                    show()
                } catch (e: Exception) {
                    error { e.message }
                }

            }

        }

        fun showOkDialog(context: Context, titleId: Int?,
                         messageId: Int, listener: ((Dialog, View) -> Unit)?) {
            val t = if(titleId != null) context.getString(titleId) else null
            showOkDialog(context, t, context.getString(messageId), false, false, listener)
        }

        fun showOkCancelDialog(context: Context, titleId: Int?,
                               messageId: Int, listener: (Dialog, View) -> Unit) {
            val t = if(titleId != null) context.getString(titleId) else null
            showOkDialog(context, t, context.getString(messageId), true, false, listener)
        }

        @JvmStatic
        fun showOKWarningDialog(context: Context, titleId: Int?,
                                messageId: Int, listener: ((Dialog, View) -> Unit)?) {
            val t = if(titleId != null) context.getString(titleId) else null
            showOkDialog(context, t, context.getString(messageId), false, true, listener)
        }

    }
}
