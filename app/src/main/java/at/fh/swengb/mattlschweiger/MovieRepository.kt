package at.fh.swengb.mattlschweiger

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MovieRepository {


    fun moviesList(success: (movieList: List<Movie>) -> Unit, error: (errorMessage: String) -> Unit) {
        MovieAPI.retrofitService.movies().enqueue(object:
            Callback<List<Movie>> {
            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                error("The call failed")
            }

            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    success(responseBody)
                } else {
                    error("Something went wrong")
                }
            }

        })
    }

    fun movieById(id:String, success: (movie: MovieDetail) -> Unit, error: (errorMessage: String) -> Unit){
        MovieAPI.retrofitService.movieById(id).enqueue(object: Callback<MovieDetail> {
            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                error("The call failed")
            }

            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    success(responseBody)
                } else {
                    error("Something went wrong")
                }
            }

        })
    }


    fun rateMovie(movieId: String, review: Review, success: (movie: Unit) -> Unit, error: (errorMessage: String) -> Unit) {
        MovieAPI.retrofitService.rateMovie(movieId ,review).enqueue(object: Callback<Unit>{
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                error("The call failed")
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    success(responseBody)
                } else {
                    error("Something went wrong")
                }
            }

        })
    }
}