package ks.hs.dgsw.data.entity

data class AccountData(
    val idx: Int,
    val userId: String,
    val password: String,
    val account: String,
    val name: String,
    val money: Int
)