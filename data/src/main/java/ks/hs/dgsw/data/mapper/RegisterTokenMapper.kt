package ks.hs.dgsw.data.mapper

import ks.hs.dgsw.data.entity.RegisterTokenData
import ks.hs.dgsw.domain.entity.dto.LoginToken
import ks.hs.dgsw.domain.entity.dto.RegisterToken

fun RegisterTokenData.toEntity() =
    RegisterToken(this.token)

fun RegisterToken.toData() =
    RegisterTokenData(this.token)