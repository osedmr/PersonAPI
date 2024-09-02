package com.example.personsapp.data.datasource

import android.util.Log
import com.example.personsapp.data.entity.Kisiler
import com.example.personsapp.refrofit.KisilerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class KisilerDataSource(var kdao: KisilerDao) {

    suspend fun kisileriYukle():List<Kisiler> =

        withContext(Dispatchers.IO){

            return@withContext kdao.kisileriYukle().kisiler
        }



    suspend fun save(name: String, num: String) {
        kdao.kaydet(name, num)
    }

    suspend fun update(kisi_id:Int, kisi_ad:String, kisi_tel:String){
        kdao.update(kisi_id,kisi_ad,kisi_tel)
    }
    suspend fun sil(id:Int){
        kdao.sil(id)
    }

    suspend fun search(ara: String): List<Kisiler> =
        withContext(Dispatchers.IO) {
            val sonuc = kdao.search(ara).kisiler
            return@withContext if (sonuc.isNotEmpty()) {
                sonuc
            } else {
                emptyList()
            }
        }

}