package ks.hs.dgsw.domain.entity.request

data class SendMoney(
    val bank: Int,
    val receiveAccountId: String,
    val sendAccountPw: String,
    val sendAccountId: String,
    val money: Int
)