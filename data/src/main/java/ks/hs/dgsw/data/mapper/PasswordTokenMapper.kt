package ks.hs.dgsw.data.mapper

import ks.hs.dgsw.data.entity.PasswordLoginTokenData
import ks.hs.dgsw.domain.entity.dto.PasswordLoginToken

fun PasswordLoginTokenData.toEntity() =
    PasswordLoginToken(
        this.token
    )

fun PasswordLoginToken.toData() =
    PasswordLoginTokenData(
        this.token
    )