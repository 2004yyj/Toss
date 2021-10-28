package ks.hs.dgsw.domain.entity.request

data class GetMoney(
    val receiveAccountId: String,
    val sendAccountId: String,
    val money: Int
)
