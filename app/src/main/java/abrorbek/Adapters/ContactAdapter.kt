package abrorbek.Adapters

import abrorbek.Models.User
import abrorbek.uz.imthon.databinding.ItemRvBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapte(private val list: List<User>) :
    RecyclerView.Adapter<ContactAdapte.VH>() {
    inner class VH(var itemRv: ItemRvBinding) :
        RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(user: User, position: Int) {
            itemRv.tvName.text = user.name
            itemRv.tvData.text = user.date


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position], position)
    }


    override fun getItemCount(): Int = list.size

}