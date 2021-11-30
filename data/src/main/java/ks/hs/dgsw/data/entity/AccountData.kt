package ks.hs.dgsw.data.entity

data class AccountData(
    val idx: Int,
    val user: UserData?,
    val userId: String,
    val account: String,
    val name: String,
    val money: Int,
    val send: List<TransferHistoryData>?,
    val receive: List<TransferHistoryData>?
)