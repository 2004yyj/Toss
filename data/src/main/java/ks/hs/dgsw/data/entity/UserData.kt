package ks.hs.dgsw.data.entity

import ks.hs.dgsw.domain.entity.dto.Account

data class UserData(
    val id: String,
    val nick: String,
    val phone: String,
    val birth: String,
    val account: List<Account>
)
