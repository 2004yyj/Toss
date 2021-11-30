package ks.hs.dgsw.data.entity

import dagger.multibindings.IntoMap

data class TransferHistoryData(
    val idx: Int,
    val fromAccount: String,
    val toAccount: String,
    val createdAt: String,
    val money: Int,
    val accountIdx: Int,
)