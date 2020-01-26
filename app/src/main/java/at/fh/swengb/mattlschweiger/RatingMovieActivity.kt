package at.fh.swengb.mattlschweiger

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_rating_movie.*

class RatingMovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating_movie)

        val movieId2 = intent.getStringExtra(MovieDetailsActivity.EXTRA_MOVIE_ID_DETAILS)
        if (movieId2 == null) {
            Toast.makeText(this,"NULL!",Toast.LENGTH_LONG).show()
            finish()
        } else {
            MovieRepository.movieById(movieId2,
                success = {
                    rate_movie_header.text = it.title
                },
                error = {
                    // handle error
                    Log.e("Error", it)
                }
            )


            rate_movie_button.setOnClickListener {
                Toast.makeText(this, resources.getString(R.string.rating_movie_toast), Toast.LENGTH_SHORT).show()
                val newReview = Review(
                    rate_movie_rating_bar.rating.toDouble(),
                    rate_movie_feedback.text.toString()
                )

                MovieRepository.rateMovie(movieId2,
                    newReview,
                    success = {
                        setResult(Activity.RESULT_OK)
                        finish()
                    },
                    error = {
                        // handle error
                        Log.e("Error", it)
                    })

            }
        }


    }
}
