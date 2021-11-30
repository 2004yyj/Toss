package ks.hs.dgsw.data.entity

data class AccountData(
    val idx: Int,
    val user: UserData?,
    val account: String,
    val accountType: String,
    val bank: String,
    val name: String,
    val money: Int,
    val send: List<TransferHistoryData>?,
    val receive: List<TransferHistoryData>?
)