package thanhlongbanh8997.englishforeverybody.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.widget.FrameLayout

class ImageUtils {
    companion object {
        fun createBitmap(v: View): Bitmap =
                with (v) {
                    layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
                    measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
                    layout(0, 0, v.measuredWidth, v.measuredHeight)
                    val bitmap = Bitmap.createBitmap(v.measuredWidth, v.measuredHeight, Bitmap.Config.ARGB_8888)

                    val c = Canvas(bitmap)
                    layout(v.left, v.top, v.right, v.bottom)
                    draw(c)
                    bitmap
                }

    }
}
