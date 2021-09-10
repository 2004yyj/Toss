package ks.hs.dgsw.domain.entity.response

data class TokenResponse(
    val status: Int,
    val message: String,
    val token: String?
)
