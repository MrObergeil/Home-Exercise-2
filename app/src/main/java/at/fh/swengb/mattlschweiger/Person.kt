package at.fh.swengb.mattlschweiger

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Person(val name: String,
             val profileImagePath: String) {

}