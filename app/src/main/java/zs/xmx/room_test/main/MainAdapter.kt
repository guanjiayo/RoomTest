package zs.xmx.room_test.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import zs.xmx.room_test.R

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var data: List<String> = emptyList()
    private var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return MainViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item
        holder.textView.setOnClickListener {
            onItemClickListener?.onItemClick(position, holder.textView.text.toString())
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<String>) {
        data = newData
        notifyDataSetChanged()  // 通知数据已改变，更新UI
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.text)
    }


    interface OnItemClickListener {
        fun onItemClick(position: Int, text: String)
    }
}