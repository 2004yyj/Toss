package ks.hs.dgsw.domain.entity

data class Account(
    val bankName: String,
    val accountNumber: String,
    val name: String,
    val balance: Int
)