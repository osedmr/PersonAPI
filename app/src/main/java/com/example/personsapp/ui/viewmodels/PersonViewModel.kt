package com.example.personsapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.personsapp.data.entity.Kisiler
import com.example.personsapp.data.repository.KisilerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PersonViewModel @Inject constructor(val krepo: KisilerRepository):ViewModel() {
    var kisilerListesi= MutableLiveData<List<Kisiler>>()



    init {
        kisileriYukle()
    }

    fun kisileriYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                kisilerListesi.value=  krepo.kisileriYukle()
            }catch (e:Exception){
                e.printStackTrace()
            }

        }
    }

     fun sil(id:Int){
         CoroutineScope(Dispatchers.Main).launch {
             krepo.sil(id)
         }
         kisileriYukle()
    }
    fun search(ara: String){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                kisilerListesi.value=krepo.search(ara)
            }catch (e:Exception){
                e.printStackTrace()
            }

        }

    }
}