package com.example.personsapp.ui.adapter

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.personsapp.data.entity.Kisiler
import com.example.personsapp.databinding.PersonCartBinding
import com.example.personsapp.ui.fragments.PersonDirections
import com.example.personsapp.ui.viewmodels.PersonViewModel

class KisilerAdapter(var mContext: Context,var kisilerListesi:MutableList<Kisiler>,var viewModel: PersonViewModel)
    :RecyclerView.Adapter<KisilerAdapter.PersonCartHolder>() {

    inner class PersonCartHolder(val binding:PersonCartBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonCartHolder {
        val binding=PersonCartBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return PersonCartHolder(binding)
    }

    override fun getItemCount(): Int {
        return kisilerListesi.size
    }

    override fun onBindViewHolder(holder: PersonCartHolder, position: Int) {

        val kisi= kisilerListesi.get(position)
        holder.binding.nameText.text=kisi.kisi_ad
        holder.binding.numberText.text=kisi.kisi_tel

        holder.binding.personCartView.setOnClickListener {
            val personToDetailPerson=PersonDirections.personToDetailPerson(kisi)
            Navigation.findNavController(it).navigate(personToDetailPerson)
        }
        holder.binding.deleteName.setOnClickListener {

            val message="${kisi.kisi_ad} adlı kişiyi silmek istediğinize emin misiniz?"
            deleteDialog(it,message){
                //silme işlemi yeri
                kisilerListesi.removeAt(position)
                notifyItemRemoved(position)

            }
        }


    }

    fun sil(id:Int){
        viewModel.sil(id)
    }
    fun deleteDialog(view: View,message:String,onDeleteConfirmed:() -> Unit){
        val builder=AlertDialog.Builder(view.context)
        builder.setTitle("Silme İşlemi")
        builder.setMessage(message)
        builder.setPositiveButton("Evet"){dialog,_ ->
            onDeleteConfirmed()
            dialog.dismiss()
        }
        builder.setNegativeButton("Hayır"){dialog,_ ->
            dialog.dismiss()
        }
        val dialog:AlertDialog=builder.create()
        dialog.show()
    }


}