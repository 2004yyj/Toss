package ks.hs.dgsw.data.mapper

import ks.hs.dgsw.data.entity.AccountData
import ks.hs.dgsw.domain.entity.dto.Account

fun AccountData.toEntity(): Account {
    return Account(
        this.idx,
        this.user?.toEntity(),
        this.account,
        this.accountType,
        this.bank,
        this.name,
        this.money,
        this.send?.map {
            it.toEntity()
        },
        this.receive?.map {
            it.toEntity()
        }
    )
}

fun Account.toData(): AccountData {
    return AccountData(
        this.idx,
        this.user?.toData(),
        this.account,
        this.accountType,
        this.bank,
        this.name,
        this.money,
        this.send?.map {
            it.toData()
        },
        this.receive?.map {
            it.toData()
        }
    )
}