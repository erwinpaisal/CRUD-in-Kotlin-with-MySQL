package id.erwinpaisal.mentoringweek4.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.erwinpaisal.mentoringweek4.R
import id.erwinpaisal.mentoringweek4.model.data.DataItem

class PengunjungAdapter(private val data: ArrayList<DataItem>?, private val itemClick: OnItemClickListener): RecyclerView.Adapter<PengunjungAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val txtNama: TextView = itemView.findViewById<TextView>(R.id.txt_name_pengunjung)
        val txtJenisKelamin = itemView.findViewById<TextView>(R.id.txt_jk_pengunjung)
        val txtAlamat = itemView.findViewById<TextView>(R.id.txt_alamat_pengunjung)
        val btnHapus = itemView.findViewById<ImageView>(R.id.btn_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pengunjung, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    @SuppressLint("SetTextI18n")

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = data?.get(position)
        holder.txtNama.text = item?.namaPengunjung
        holder.txtAlamat.text = item?.alamat
        if (item?.jenisKelamin == "L") {
            holder.txtJenisKelamin.text = "Laki-Laki"
        } else if (item?.jenisKelamin == "P") {
            holder.txtJenisKelamin.text = "Perempuan"
        }

        holder.itemView.setOnClickListener {
            itemClick.detail(item)
        }
        holder.btnHapus.setOnClickListener {
            itemClick.hapus(item)
        }
    }

}

interface OnItemClickListener {
    fun detail(item: DataItem?)
    fun hapus(item: DataItem?)
}