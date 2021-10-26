package ks.hs.dgsw.data.entity

data class UserData(
    val id: String,
    val nick: String,
    val phone: String,
    val birth: String,
    val name: String,
    val account: List<AccountData>?
)
