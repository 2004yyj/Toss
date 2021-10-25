package ks.hs.dgsw.data.mapper

import ks.hs.dgsw.data.entity.BaseAccountData
import ks.hs.dgsw.domain.entity.dto.BaseAccount

fun BaseAccountData.toEntity(): BaseAccount {
    return BaseAccount(
        this.accounts.map {
            it.toEntity()
        }
    )
}

fun BaseAccount.toEntity(): BaseAccountData {
    return BaseAccountData(
        this.accounts.map {
            it.toData()
        }
    )
}