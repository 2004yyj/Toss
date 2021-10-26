package ks.hs.dgsw.data.mapper

import ks.hs.dgsw.data.entity.UserData
import ks.hs.dgsw.domain.entity.dto.User

fun UserData.toEntity() =
    User(
        id = this.id,
        nick = this.nick,
        phone = this.phone,
        birth = this.birth,
        name = this.name,
        account = this.account.map {
            it.toEntity()
        }
    )

fun User.toData() =
    UserData(
        id = this.id,
        nick = this.nick,
        phone = this.phone,
        birth = this.birth,
        name = this.name,
        account = this.account.map {
            it.toData()
        }
    )