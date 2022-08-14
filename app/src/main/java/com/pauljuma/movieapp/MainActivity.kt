package com.pauljuma.movieapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val moviesAdapter = MoviesAdapter()

        val request = ServiceBuilder.buildService(TmdbEndpoints::class.java)
        val call = request.getMovies(getString(R.string.api_key))
        call.enqueue(object : Callback<PopularMovies> {
            override fun onResponse(
                call: Call<PopularMovies>,
                response: Response<PopularMovies>
            ) {
                if (response.isSuccessful) {
                    progress_bar.visibility = View.GONE
                    Log.d("================>", "onResponse: ${response.body()}")
                    moviesAdapter.addMovies(response.body()?.results!!)

                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(
                            this@MainActivity,
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                        adapter = moviesAdapter
                    }
                }
                else{
                    Log.d("================>", "onResponse: ${response.body()}")
                }
                //handle response
            }

            override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                Log.d("================>", "onResponse: ${call.request().url}")
                t.printStackTrace()

            }
        })
    }
}
