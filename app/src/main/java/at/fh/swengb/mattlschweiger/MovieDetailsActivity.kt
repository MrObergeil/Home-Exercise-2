package at.fh.swengb.mattlschweiger

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.item_movie.*

class MovieDetailsActivity : AppCompatActivity() {

    companion object {
        val EXTRA_MOVIE_ID_DETAILS = "MOVIE_ID_EXTRA"
        val RATING_REQUEST = 1
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RATING_REQUEST && resultCode == Activity.RESULT_OK){
            //Toast.makeText(this,"KommtRein",Toast.LENGTH_SHORT).show()
            setResult(Activity.RESULT_OK)
            finish()
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)


        val movieId = intent.getStringExtra(MainActivity.EXTRA_MOVIE_ID)
        if (movieId == null) {

            finish()
        } else {


            DetailsOutput(movieId)

            detail_rate_button.setOnClickListener {
                val intent = Intent(this, RatingMovieActivity::class.java)
                intent.putExtra(EXTRA_MOVIE_ID_DETAILS,movieId)
                startActivityForResult(intent,
                    RATING_REQUEST
                )
            }
        }
    }

        private fun DetailsOutput(id:String) {
            MovieRepository.movieById(id,
                success = {
                    val movie = it
                    if (movie == null) {
                        Log.e("PassingError", "No ID")
                        finish()
                    } else {

                        txt_details_title.text = movie.title
                        txt_details_release.text = movie.release
                        it_details_rating_count.text = movie.reviews.count().toString()
                        it_details_rating.text = movie.reviewAverage().toString()
                        st_details_rating.rating = movie.reviewAverage().toFloat()
                        txt_details_director.text = movie.director.name
                        txt_details_actors.text = movie.actors.joinToString { it.name }
                        txt_details_genre.text = movie.genres.joinToString { it }
                        plot_details_text.text = movie.plot

                        Glide
                            .with(img_details_head)
                            .load(movie.backdropImagePath)
                            .into(img_details_head)

                        Glide
                            .with(img_detail_cv)
                            .load(movie.posterImagePath)
                            .into(img_detail_cv)

                    }
                },
                error = { Log.e("API CALL", it) })

            }



        }










