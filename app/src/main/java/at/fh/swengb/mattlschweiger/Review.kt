package at.fh.swengb.mattlschweiger

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Review (val reviewValue:Double,
              val reviewText:String)