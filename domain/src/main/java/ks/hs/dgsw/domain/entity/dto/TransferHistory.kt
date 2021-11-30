package ks.hs.dgsw.domain.entity.dto

import dagger.multibindings.IntoMap

data class TransferHistory(
    val idx: Int,
    val fromAccount: String,
    val toAccount: String,
    val createdAt: String,
    val money: Int,
    val accountIdx: Int,
)