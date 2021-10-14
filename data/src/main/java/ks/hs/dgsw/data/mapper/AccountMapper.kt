package ks.hs.dgsw.data.mapper

import ks.hs.dgsw.data.entity.AccountData
import ks.hs.dgsw.domain.entity.dto.Account

fun AccountData.toEntity(): Account {
    return Account(
        this.idx,
        this.userId,
        this.password,
        this.account,
        this.name,
        this.money
    )
}

fun Account.toData(): AccountData {
    return AccountData(
        this.idx,
        this.userId,
        this.password,
        this.account,
        this.name,
        this.money
    )
}