package thanhlongbanh8997.englishforeverybody.data

import thanhlongbanh8997.englishforeverybody.R
import thanhlongbanh8997.englishforeverybody.model.Theme

open class ThemeCatalog(private val list: MutableList<Theme>) : MutableList<Theme> by list {

    companion object : ThemeCatalog(mutableListOf())

    init {
        list.add(Theme(9, "Theme 09", "theme_09", "", R.drawable.bg_note_01))

        list.add(Theme(5, "Theme 05", "theme_05", "", R.drawable.bg_note_05))
        list.add(Theme(6, "Theme 06", "theme_06", "", R.drawable.bg_note_06))
        list.add(Theme(7, "Theme 07", "theme_07", "", R.drawable.bg_note_07))
        list.add(Theme(8, "Theme 08", "theme_08", "", R.drawable.bg_note_08))
        list.add(Theme(3, "Theme 03", "theme_03", "", R.drawable.bg_note_03))
        list.add(Theme(4, "Theme 04", "theme_04", "", R.drawable.bg_note_04))
        list.add(Theme(10, "Theme 10", "theme_10", "", R.drawable.bg_note_10))
        list.add(Theme(2, "Theme 02", "theme_02", "", R.drawable.bg_note_02))
        list.add(Theme(1, "Theme 01", "theme_01", "", R.drawable.bg_note_09))
    }
}
