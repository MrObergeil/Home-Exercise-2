package at.fh.swengb.mattlschweiger

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
open class Movie (val id: String,
             val title: String,
             val posterImagePath: String,
             val backdropImagePath: String,
             val release: String,
             val reviews: MutableList<Review>
            ){

    fun reviewAverage(): Double{
        var average = Math.round((reviews.map { it.reviewValue }.average())*100)/100.0

        if (average.isNaN()){
            return 0.0
        }
        return  average
    }
}