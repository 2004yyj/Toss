package ks.hs.dgsw.domain.entity.dto

data class PostAccountResponse(
    val account: String,
    val limit: Int,
    val type: String
)