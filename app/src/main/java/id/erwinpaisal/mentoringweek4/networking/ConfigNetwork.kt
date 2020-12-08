package id.erwinpaisal.mentoringweek4.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConfigNetwork {

    companion object {
        private fun getRetrofit() : Retrofit {
            return Retrofit.Builder()
                    .baseUrl("http://10.0.2.2/final/") //avd
                    //.baseUrl("http://192.168.54.162/final/") ip device
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }

        fun service(): PengunjungService {
            return getRetrofit().create(PengunjungService::class.java)
        }

    }
}