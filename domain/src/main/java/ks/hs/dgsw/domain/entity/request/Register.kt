package ks.hs.dgsw.domain.entity.request

data class Register(
    val id: String,
    val pw: String,
    val nick: String,
    val name: String,
    val phone: String,
    val birth: String
)
