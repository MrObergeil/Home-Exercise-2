package at.fh.swengb.mattlschweiger

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class MovieDetail(id:String,
                  title:String,
                  posterImagePath: String,
                  backdropImagePath: String,
                  release: String,
                  reviews: MutableList<Review>,
                  val plot:String,
                  val genres:List<String>,
                  val director: Person,
                  val actors: List<Person>):
    Movie(id,title,posterImagePath,backdropImagePath,release,reviews)