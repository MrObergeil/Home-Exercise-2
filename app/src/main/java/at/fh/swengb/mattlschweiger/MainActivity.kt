package at.fh.swengb.mattlschweiger

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        val EXTRA_MOVIE_ID = "MOVIE_ID_EXTRA"
        //val REQUEST_DETAILS = 1
    }


    val movieAdapter = MovieAdapter() {
        val intent = Intent(
            this,
            MovieDetailsActivity::class.java
        )
        intent.putExtra(
            EXTRA_MOVIE_ID,
            it.id
        )
        startActivity(intent)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MovieRepository.moviesList(
            success = {
                // handle success
                movieAdapter.updateList(it)
            },
            error = {
                // handle error
                Log.e("Error", it)
            }
        )

        movie_recycler_view.layoutManager = GridLayoutManager(this,3)
        movie_recycler_view.adapter = movieAdapter

    }


    }







