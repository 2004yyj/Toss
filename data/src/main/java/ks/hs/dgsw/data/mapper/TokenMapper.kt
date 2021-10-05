package ks.hs.dgsw.data.mapper

import ks.hs.dgsw.data.entity.TokenData
import ks.hs.dgsw.domain.entity.dto.Token

fun TokenData.toEntity() =
    Token(
        this.simpleId,
        this.token
    )

fun Token.toData() =
    Token(
        this.simpleId,
        this.token
    )