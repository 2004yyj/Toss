package ks.hs.dgsw.domain.entity.dto

data class Account(
    val idx: Int,
    val user: User,
    val userId: String,
    val account: String,
    val name: String,
    val money: Int,
    val send: List<String?>,
    val receive: List<String?>
)