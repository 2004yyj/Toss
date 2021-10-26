package ks.hs.dgsw.data.mapper

import ks.hs.dgsw.data.entity.UserData
import ks.hs.dgsw.domain.entity.dto.Account
import ks.hs.dgsw.domain.entity.dto.User

fun UserData.toEntity(): User {
    return if (account == null) {
        User(
            id = this.id,
            nick = this.nick,
            phone = this.phone,
            birth = this.birth,
            name = this.name,
            account = ArrayList()
        )
    } else {
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
    }
}

fun User.toData(): UserData {
    return if (account == null) {
        UserData(
            id = this.id,
            nick = this.nick,
            phone = this.phone,
            birth = this.birth,
            name = this.name,
            account = ArrayList()
        )
    } else {
        UserData(
            id = this.id,
            nick = this.nick,
            phone = this.phone,
            birth = this.birth,
            name = this.name,
            account = this.account!!.map {
                it.toData()
            }
        )
    }
}
