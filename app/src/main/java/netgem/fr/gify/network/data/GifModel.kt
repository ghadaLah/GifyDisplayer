package netgem.fr.gify.network.data

import com.google.gson.annotations.SerializedName

data class GifResponse(
    val data: GifModel
)

data class GifModel(
    @SerializedName("type")
    val type: String = "gif",
    @SerializedName("id")
    val id: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("title")
    val title: String
)

data class GifListResponse(
    val data: List<GifModel>
)