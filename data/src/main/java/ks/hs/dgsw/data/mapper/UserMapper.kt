package ks.hs.dgsw.data.mapper

import ks.hs.dgsw.data.entity.UserData
import ks.hs.dgsw.domain.entity.dto.User

fun UserData.toEntity() =
    User(
        id = this.id,
        pw = this.pw,
        nick = this.nick,
        phone = this.phone,
        birth = this.birth,
        account = this.account
    )

fun User.toData() =
    UserData(
        id = this.id,
        pw = this.pw,
        nick = this.nick,
        phone = this.phone,
        birth = this.birth,
        account = this.account
    )