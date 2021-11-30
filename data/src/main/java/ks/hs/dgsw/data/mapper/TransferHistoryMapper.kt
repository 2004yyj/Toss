package ks.hs.dgsw.data.mapper

import ks.hs.dgsw.data.entity.TransferHistoryData
import ks.hs.dgsw.domain.entity.dto.TransferHistory

fun TransferHistoryData.toEntity() =
    TransferHistory(
        this.idx,
        this.fromAccount,
        this.toAccount,
        this.createdAt,
        this.money,
        this.accountIdx
    )

fun TransferHistory.toData() =
    TransferHistoryData(
        this.idx,
        this.fromAccount,
        this.toAccount,
        this.createdAt,
        this.money,
        this.accountIdx
    )