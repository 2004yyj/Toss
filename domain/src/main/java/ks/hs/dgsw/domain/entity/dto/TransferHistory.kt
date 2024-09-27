package ks.hs.dgsw.domain.entity.dto

import android.os.Parcel
import android.os.Parcelable

data class TransferHistory(
    val idx: Int,
    val fromAccount: String,
    val toAccount: String,
    val createdAt: String,
    val money: Int,
    val accountIdx: Int,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idx)
        parcel.writeString(fromAccount)
        parcel.writeString(toAccount)
        parcel.writeString(createdAt)
        parcel.writeInt(money)
        parcel.writeInt(accountIdx)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TransferHistory> {
        override fun createFromParcel(parcel: Parcel): TransferHistory {
            return TransferHistory(parcel)
        }

        override fun newArray(size: Int): Array<TransferHistory?> {
            return arrayOfNulls(size)
        }
    }

}

class TransferHistoryComparatorDescending: Comparator<TransferHistory> {
    override fun compare(o1: TransferHistory, o2: TransferHistory): Int {
        if (o1.idx < o2.idx) return 1
        if (o1.idx > o2.idx) return -1
        return 0
    }
}