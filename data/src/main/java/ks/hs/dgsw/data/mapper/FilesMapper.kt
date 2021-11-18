package ks.hs.dgsw.data.mapper

import ks.hs.dgsw.data.entity.FilesData
import ks.hs.dgsw.domain.entity.dto.Files

fun FilesData.toEntity(): Files {
    return Files(this.files)
}

fun Files.toData(): FilesData {
    return FilesData(this.files)
}