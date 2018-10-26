package thanhlongbanh8997.englishforeverybody.adapter

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.item_grid_theme.view.*
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.sdk25.coroutines.onClick

import thanhlongbanh8997.englishforeverybody.EditorVocabularyActivity
import thanhlongbanh8997.englishforeverybody.R
import thanhlongbanh8997.englishforeverybody.data.ThemeCatalog
import thanhlongbanh8997.englishforeverybody.model.Theme
import thanhlongbanh8997.englishforeverybody.utils.Constants

class ThemeAdapter(activity: Activity): RecyclerView.Adapter<ThemeAdapter.ViewHolder>() {

    private var activity = activity

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imvThumbnailTheme.backgroundResource = ThemeCatalog[position].resId
        holder.itemView.onClick {
            activity.startActivity(
                    Intent(activity, EditorVocabularyActivity::class.java).apply {
                        putExtra(Constants.kThemePosition, position)
                    }
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.item_grid_theme, parent, false).let {
                ThemeAdapter.ViewHolder(it)
            }

    override fun getItemCount(): Int = ThemeCatalog.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var imvThumbnailTheme: ImageView = itemView.imvThumbnailTheme

    }
}