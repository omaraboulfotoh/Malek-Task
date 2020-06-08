package com.example.movieappkotlin.model

import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "movie_table")
@Parcelize
data class Movie(
    @PrimaryKey
    @field:Json (name = "title") var title: String ="",

    @Nullable
    @field:Json (name = "poster_path")
    var posterPath: String? = "",

    @field:Json (name = "release_date")
    var releaseDate: String? = "",
    @field:Json (name = "vote_count")
    var voteCount: Int? = null,

    @field:Json (name = "id")
    var id: Int? = null,

    @field:Json (name = "video")
    var video: Boolean? = null,

    @field:Json (name = "vote_average")
    var voteAverage: Float? = null,

    @field:Json (name = "popularity")
    var popularity: Float? = null,

    @field:Json (name = "original_language")
    var originalLanguage: String? = null,

    @field:Json (name = "original_title")
    var originalTitle: String? = null,

    @field:Json (name = "genre_ids")
    var genreIds: List<Int>? = null,

    @field:Json (name = "backdrop_path")
    var backdropPath: String? = null,

    @field:Json (name = "adult")
    var adult: Boolean? = null,

    @field:Json (name = "overview")
    var overview: String? = null
): Parcelable