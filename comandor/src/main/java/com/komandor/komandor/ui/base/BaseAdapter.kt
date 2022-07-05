package com.komandor.komandor.ui.base

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<M,
        VH : RecyclerView.ViewHolder?
        > : RecyclerView.Adapter<VH>() {

    private val TAG = BaseAdapter::class.java.simpleName


    var dataList = mutableListOf<M>()
    var selectedPos: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return createViewHolder(parent.context, parent, viewType)
    }

    override fun getItemCount(): Int = if (!dataList.isNullOrEmpty()) dataList.size else 0

    abstract override fun getItemViewType(position: Int): Int

    abstract fun createViewHolder(ctx: Context, parent: ViewGroup?, viewType: Int): VH

    fun getItem(pos: Int): M? {
        return if (pos >= 0 && pos < dataList.size) dataList[pos] else null
    }

    fun removeItem(p: M) {
        //L.d(TAG, "p = $p")
        dataList.remove(p)
        notifyDataSetChanged()
    }

    open fun setList(data: List<M>) {
        dataList = data.toMutableList()

        dataList.forEach{model ->
            if (model is ISelected && (model as ISelected).isSelected()){

                selectedPos = dataList.indexOf(model)
                //L.d(TAG, "selectedPos = "+selectedPos)
                return@forEach
            }
        }
        notifyDataSetChanged()
    }

    fun updateSelectedItem(pos:Int) {
        if (selectedPos>=0){
            val model = dataList.get(selectedPos)
            if (model is ISelected) {
                (model as ISelected).setSelected(false);
                notifyItemChanged(selectedPos)
            }
        }
        if (pos>=0) {
            selectedPos = pos
            val model = dataList.get(selectedPos)
            if (model is ISelected) {
                (model as ISelected).setSelected(true);
                notifyItemChanged(selectedPos)
            }
        }
    }
}