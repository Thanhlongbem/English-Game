package thanhlongbanh8997.englishforeverybody

import android.content.Intent
import android.net.Uri
import kotlinx.android.synthetic.main.activity_feedback.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class FeedbackActivity: BaseToolbarActivity() {

    override var layout = R.layout.activity_feedback

    override fun initView() {
        with(supportActionBar!!) {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.activity_feedback)
        }
    }

    override fun initData() {
        btnSendFeedback.onClick {
            val intent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","thanhlongny@gmail.com", null))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Vona Feedback app")
            startActivity(Intent.createChooser(intent, "Vona send feedback:"))
        }
    }


}
