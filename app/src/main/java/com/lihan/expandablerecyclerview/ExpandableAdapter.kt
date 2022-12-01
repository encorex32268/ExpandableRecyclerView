package com.lihan.expandablerecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.lihan.expandablerecyclerview.model.GroupItem
import com.lihan.expandablerecyclerview.model.Item

class ExpandableAdapter(
    private val data : GroupItem
) : RecyclerView.Adapter<ViewHolder>() {

    companion object{
        private const val VIEW_TYPE_HEADER = 1
        private const val VIEW_TYPE_ITEM = 2
    }
    private var isExpand = false
    var onClick :(()->Unit)?= null
    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_HEADER else VIEW_TYPE_ITEM
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_HEADER -> TitleViewHolder(
                inflater.inflate(
                    R.layout.item_title,
                    parent,
                    false
                )
            )
            else -> ContentViewHolder(
                inflater.inflate(
                    R.layout.item_cotent,
                    parent,
                    false
                )
            )
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is TitleViewHolder -> {
                holder.toBind(
                    data.title
                )

                holder.itemView.setOnClickListener {
                    toggle()
                }
            }
            is ContentViewHolder-> {
                holder.toBind(data.items[position-1])
                holder.itemView.setOnClickListener {
                    onClick?.let { it1 ->
                        it1()
                    }
                }
            }
        }
    }

    override fun getItemCount() = if (isExpand) data.items.size + 1   else 1

    private fun toggle(){
        isExpand = !isExpand
        if (isExpand) {
            notifyItemRangeInserted(1, data.items.size)
            //To update the header expand icon
            notifyItemChanged(0)
        } else {
            notifyItemRangeRemoved(1, data.items.size)
            //To update the header expand icon
            notifyItemChanged(0)
        }
    }

}






class TitleViewHolder(itemView : View) : ViewHolder(itemView){

    private val titleTextView = itemView.findViewById<TextView>(R.id.titleTextView)
    private val titleImageView = itemView.findViewById<ImageView>(R.id.titleImageView)
    fun toBind(title : String){
        titleTextView.text = title
    }

}

class ContentViewHolder(itemView : View) : ViewHolder(itemView){

    private val contentTextView = itemView.findViewById<TextView>(R.id.contentTextView)
    private val contentImage = itemView.findViewById<ImageView>(R.id.contentImage)
    fun toBind(item: Item){
        contentTextView.text = "${item.id} - ${item.name}"
        contentImage.setOnClickListener {
            //go to
        }
    }


}