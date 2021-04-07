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
    val title: String,
    @SerializedName("embed_url")
    val embed_url: String,
    @SerializedName("images")
    val images: ImageModel
)

data class GifListResponse(
    val data: List<GifModel>,
    val pagination: PaginationModel
)

data class ImageModel(
    val fixed_height: FixedHeight
)

data class FixedHeight (
    val url: String,
    val width:String,
    val height: String
)