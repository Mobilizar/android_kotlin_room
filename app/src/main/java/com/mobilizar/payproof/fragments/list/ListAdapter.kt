package com.mobilizar.payproof.fragments.list


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mobilizar.payproof.R
import com.mobilizar.payproof.databinding.CustomRowBinding
import com.mobilizar.payproof.model.Debit

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {


    private var debitList = emptyList<Debit>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CustomRowBinding.bind(itemView)

        fun bind(debit: Debit) {
            with(binding) {
                idTxt.text = debit.id.toString()
                descriptionTxt.text = debit.description
            }
        }
    }

    fun setData(debitList: List<Debit>) {
        this.debitList = debitList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.custom_row, parent, false)
        )
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = debitList[position]

        holder.bind(currentItem)
        holder.itemView.setOnClickListener() {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)

        }

    }

    override fun getItemCount(): Int {
        return debitList.size
    }
}