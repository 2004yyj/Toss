package ks.hs.dgsw.domain.entity.dto

data class Account(
    val idx: Int,
    val userId: String,
    val password: String,
    val account: String,
    val name: String,
    val money: Int
)