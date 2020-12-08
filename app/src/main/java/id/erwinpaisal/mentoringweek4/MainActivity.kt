package id.erwinpaisal.mentoringweek4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import id.erwinpaisal.mentoringweek4.adapter.OnItemClickListener
import id.erwinpaisal.mentoringweek4.adapter.PengunjungAdapter
import id.erwinpaisal.mentoringweek4.model.action.ActionResponse
import id.erwinpaisal.mentoringweek4.model.data.DataItem
import id.erwinpaisal.mentoringweek4.model.data.PengunjungResponse
import id.erwinpaisal.mentoringweek4.networking.ConfigNetwork
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showData()

        fab_add.setOnClickListener{
            val intent = Intent(this@MainActivity, TambahActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        showData()
    }

    private fun showData() {
        val listPengunjung = ConfigNetwork.service().getDataPengunjung()
        listPengunjung.enqueue(object : Callback<PengunjungResponse>{
            override fun onFailure(call: Call<PengunjungResponse>, t: Throwable) {
                Log.d(TAG, "onFailure list pengunjung : " + t.message)
            }

            override fun onResponse(
                call: Call<PengunjungResponse>,
                response: Response<PengunjungResponse>
            ) {
                if (response.isSuccessful){
                    val data = response.body()?.data
                    val adapter = PengunjungAdapter(data, object : OnItemClickListener{
                        override fun detail(item: DataItem?) {
                           val intent = Intent(this@MainActivity, TambahActivity::class.java)
                           intent.putExtra(EXTRA_DATA_PENGUNJUNG, item)
                           startActivity(intent)
                        }

                        override fun hapus(item: DataItem?) {
                            AlertDialog.Builder(this@MainActivity).apply {
                                setTitle("Hapus data")
                                setMessage("Yakin ingin hapus ${item?.namaPengunjung}")
                                setPositiveButton("Hapus") { dialog, which ->
                                    hapusData(item?.id)
                                    dialog.dismiss()
                                }
                                setNegativeButton("Batal") { dialog, which ->
                                    dialog.dismiss()
                                }
                            }.show()
                        }
                    })
                    rv_pengunjung.adapter = adapter
                }
            }

        })
    }

    private fun hapusData(id: String?) {
        val hapus = ConfigNetwork.service().deletePengunjung(id)
        hapus.enqueue(object : Callback<ActionResponse>{
            override fun onFailure(call: Call<ActionResponse>, t: Throwable) {
                Log.d(TAG, "onFailure hapus: " +t.message)
            }

            override fun onResponse(
                call: Call<ActionResponse>,
                response: Response<ActionResponse>
            ) {
                if (response.isSuccessful){
                    val message = response.body()?.message
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                    showData()
                }
            }

        })
    }

    companion object {
        const val TAG = "MainActivity"
        const val EXTRA_DATA_PENGUNJUNG = "extra_data_pengunjung"
    }
}