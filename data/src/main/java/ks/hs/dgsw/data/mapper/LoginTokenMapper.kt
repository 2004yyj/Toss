package ks.hs.dgsw.data.mapper

import ks.hs.dgsw.data.entity.LoginTokenData
import ks.hs.dgsw.domain.entity.dto.LoginToken

fun LoginTokenData.toEntity() =
    LoginToken(
        this.simpleId,
        this.token
    )

fun LoginToken.toData() =
    LoginTokenData(
        this.simpleId,
        this.token
    )