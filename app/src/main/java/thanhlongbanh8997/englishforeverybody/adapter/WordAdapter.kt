package thanhlongbanh8997.englishforeverybody.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_word_list.view.*
import org.apache.commons.lang3.StringUtils
import org.jetbrains.anko.sdk25.coroutines.onLongClick
import org.jetbrains.anko.textColor
import thanhlongbanh8997.englishforeverybody.R
import thanhlongbanh8997.englishforeverybody.model.Word
import thanhlongbanh8997.englishforeverybody.utils.DialogUtils

class WordAdapter(context: Context, wordList: MutableList<Word>): RecyclerView.Adapter<WordAdapter.ViewHolder>(){

    private var wordList = wordList
    private var mainContext = context
    private var textSize = 20
    private var textColor =  ContextCompat.getColor(context, android.R.color.white)


    fun settingColor(textColor: Int) {
        this.textColor = textColor
    }

    fun settingTextSize(textSize: Int) {
        this.textSize = textSize
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.item) {
            tvKeyWord.text = wordList[position].keyword
            tvKeyWord.textSize = textSize.toFloat()
            tvKeyWord.textColor = textColor

            tvMean.text = " - ${wordList[position].mean}"
            tvMean.textSize = textSize.toFloat()
            tvMean.textColor = textColor

            if (StringUtils.isNotBlank(wordList[position].type)) {
                tvType.text = " (${wordList[position].type}) "
                tvType.textSize = textSize.toFloat()
                tvType.textColor = textColor
            }

            onLongClick {
                DialogUtils.showOkCancelDialog(mainContext, null , R.string.dialog_remove_word) {
                    dialog, view ->
                    removeItem(position)
                    dialog.dismiss()
                }

            }
        }
    }

    private fun removeItem(position: Int) {
        wordList.removeAt(position)
        notifyItemRemoved(position)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.item_word_list, parent, false).let {
                WordAdapter.ViewHolder(it)
            }

    override fun getItemCount(): Int = wordList.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var item: View = itemView
    }

}
