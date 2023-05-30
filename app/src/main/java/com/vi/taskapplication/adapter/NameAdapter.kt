package com.vi.taskapplication.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.vi.taskapplication.EditNameActivity
import com.vi.taskapplication.R
import com.vi.taskapplication.TableData

class NameAdapter  (private var nameList :ArrayList<TableData>) :
    RecyclerView.Adapter<NameAdapter.SectorHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SectorHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.name_item,
            parent, false
        )
    )

    override fun onBindViewHolder(holder: SectorHolder, position: Int) {

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,EditNameActivity::class.java)
            intent.putExtra("name",nameList[position].taskName)
            intent.putExtra("id",nameList[position].taskId)
            holder.itemView.context.startActivity(intent)
        }
        holder.txtNameItem.text = nameList[position].taskName

    }

    override fun getItemCount() = nameList.size

    class SectorHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val clNameItem = itemView.findViewById<ConstraintLayout>(R.id.clNameItem)
        val txtNameItem = itemView.findViewById<TextView>(R.id.textView)

    }

    internal fun setItems(items: List<TableData>) {
        this.nameList = items.toMutableList() as ArrayList<TableData>
        notifyDataSetChanged()
    }

}
