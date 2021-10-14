package ks.hs.dgsw.data.mapper

import ks.hs.dgsw.data.entity.AccountNumberData
import ks.hs.dgsw.domain.entity.dto.AccountNumber

fun AccountNumberData.toEntity(): AccountNumber {
    return AccountNumber(this.account)
}

fun AccountNumber.toData(): AccountNumberData {
    return AccountNumberData(this.account)
}