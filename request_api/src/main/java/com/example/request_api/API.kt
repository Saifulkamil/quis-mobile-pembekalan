package com.example.request_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.GET

interface JsonPlaceholderApi {
    @GET("photos")
    fun getPhotos(): Call<List<Photo>>
}
data class Photo(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)

class API : AppCompatActivity() {

    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)

        resultTextView = findViewById(R.id.resultTextView)

        fetchData()
    }

    private fun fetchData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(JsonPlaceholderApi::class.java)
        val call = api.getPhotos()

        call.enqueue(object : Callback<List<Photo>> {
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                if (response.isSuccessful) {
                    val photos = response.body()
                    val result = photos?.joinToString("\n") { it.title }
                    resultTextView.text = result
                } else {
                    resultTextView.text = "Error fetching data"
                }
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                resultTextView.text = "Error fetching data: ${t.message}"
            }
        })
    }
}
