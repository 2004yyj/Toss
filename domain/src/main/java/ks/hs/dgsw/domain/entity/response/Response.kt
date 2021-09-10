package ks.hs.dgsw.domain.entity.response

data class Response<T>(
    val status: Int,
    val message: String,
    val data: T
)
