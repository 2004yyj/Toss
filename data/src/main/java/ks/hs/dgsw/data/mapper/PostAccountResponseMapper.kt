package ks.hs.dgsw.data.mapper

import ks.hs.dgsw.data.entity.PostAccountResponseData
import ks.hs.dgsw.domain.entity.dto.PostAccountResponse

fun PostAccountResponseData.toEntity(): PostAccountResponse {
    return PostAccountResponse(this.account, this.limit, this.type)
}

fun PostAccountResponse.toData(): PostAccountResponseData {
    return PostAccountResponseData(this.account, this.limit, this.type)
}