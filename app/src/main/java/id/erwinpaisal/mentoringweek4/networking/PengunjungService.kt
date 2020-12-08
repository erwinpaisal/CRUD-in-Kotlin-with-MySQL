package id.erwinpaisal.mentoringweek4.networking

import id.erwinpaisal.mentoringweek4.model.action.ActionResponse
import id.erwinpaisal.mentoringweek4.model.data.PengunjungResponse
import retrofit2.Call
import retrofit2.http.*


interface PengunjungService{
    //get data pengunjung
    @GET("getDataPengunjung.php")
    fun getDataPengunjung(): Call<PengunjungResponse>

    //get data by id
    @GET("getDataPengunjung.php")
    fun getPengunjungById(
            @Query("id") id: Int
    ): Call<PengunjungResponse>

    // insert data pengunjung
    @FormUrlEncoded
    @POST("storePengunjung.php")
    fun storePengunjung(
            @Field("nama_pengunjung") namaPengunjung: String,
            @Field("jenis_kelamin") jenisKelamin: String?,
            @Field("alamat") alamat: String

    ): Call<ActionResponse>

    // update data pengunjung
    @FormUrlEncoded
    @POST("updatePengunjung.php")
    fun updatePengunjung(
            @Field("id") id: String,
            @Field("nama_pengunjung") namaPengunjung: String,
            @Field("jenis_kelamin") jenisKelamin: String?,
            @Field("alamat") alamat: String
    ): Call<ActionResponse>

    // delete data pengunjung
    @FormUrlEncoded
    @POST("hapusPengunjung.php")
    fun deletePengunjung(
            @Field("id") id: String?
    ): Call<ActionResponse>
}