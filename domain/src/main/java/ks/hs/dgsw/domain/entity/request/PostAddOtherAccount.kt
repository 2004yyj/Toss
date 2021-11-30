package ks.hs.dgsw.domain.entity.request

import ks.hs.dgsw.domain.entity.dto.Account

data class PostAddOtherAccount(
    val account: List<Account>
)
