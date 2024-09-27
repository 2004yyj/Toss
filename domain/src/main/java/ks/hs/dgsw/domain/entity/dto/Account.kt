package ks.hs.dgsw.domain.entity.dto

data class Account(
    val idx: Int,
    val user: User?,
    val userId: String?,
    val account: String,
    val accountType: String,
    val bank: String,
    val name: String,
    val money: Int,
    val send: List<TransferHistory>?,
    val receive: List<TransferHistory>?
) {
}