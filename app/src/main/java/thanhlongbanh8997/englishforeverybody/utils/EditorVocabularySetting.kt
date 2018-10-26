package thanhlongbanh8997.englishforeverybody.utils

import android.content.Context
import android.support.v4.content.ContextCompat
import thanhlongbanh8997.englishforeverybody.R

class EditorVocabularySetting {
    companion object {
        fun colorHex(context: Context, color: String): Int =

            when(color){
                "White" -> ContextCompat.getColor(context, android.R.color.white)
                "Black" -> ContextCompat.getColor(context, android.R.color.black)
                "Orange" -> ContextCompat.getColor(context, android.R.color.holo_orange_light)
                "Blue" -> ContextCompat.getColor(context, android.R.color.holo_blue_light)
                "Green" -> ContextCompat.getColor(context, android.R.color.holo_green_light)
                "Yellow" -> ContextCompat.getColor(context, R.color.yellow_light)
                "Pink" -> ContextCompat.getColor(context, R.color.pink_light)
                "Gray" -> ContextCompat.getColor(context, android.R.color.darker_gray)
                "Red" -> ContextCompat.getColor(context, android.R.color.holo_red_light)
                else -> ContextCompat.getColor(context, android.R.color.white)
            }
    }
}
