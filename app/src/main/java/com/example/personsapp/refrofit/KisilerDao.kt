package com.example.personsapp.refrofit

import com.example.personsapp.data.entity.CRUDCevap
import com.example.personsapp.data.entity.KisilerCevap
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface KisilerDao {
    @GET("kisiler/tum_kisiler.php")
    suspend fun kisileriYukle(): KisilerCevap

    @POST("kisiler/insert_kisiler.php")
    @FormUrlEncoded
    suspend fun kaydet(@Field("kisi_ad") name: String, @Field("kisi_tel") num: String) : CRUDCevap

    @POST("kisiler/update_kisiler.php")
    @FormUrlEncoded
    suspend fun update(
        @Field("kisi_id") id:Int,
        @Field("kisi_ad") name: String,
        @Field("kisi_tel") num: String) : CRUDCevap

    @POST("kisiler/delete_kisiler.php")
    @FormUrlEncoded
    suspend fun sil(@Field("kisi_id") id:Int) : CRUDCevap

    @POST("kisiler/tum_kisiler_arama.php")
    @FormUrlEncoded
    suspend fun search(@Field("kisi_ad") ara:String) : KisilerCevap



}