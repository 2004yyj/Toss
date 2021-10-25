package ks.hs.dgsw.data.mapper

import ks.hs.dgsw.data.entity.AccountData
import ks.hs.dgsw.domain.entity.dto.Account

fun AccountData.toEntity(): Account {
    return Account(
        this.idx,
        this.user.toEntity(),
        this.userId,
        this.password,
        this.account,
        this.name,
        this.money,
        this.send,
        this.receive
    )
}

fun Account.toData(): AccountData {
    return AccountData(
        this.idx,
        this.user.toData(),
        this.userId,
        this.password,
        this.account,
        this.name,
        this.money,
        this.send,
        this.receive
    )
}