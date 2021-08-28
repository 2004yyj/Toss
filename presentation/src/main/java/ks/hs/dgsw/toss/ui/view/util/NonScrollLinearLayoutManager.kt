package ks.hs.dgsw.toss.ui.view.util

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NonScrollLinearLayoutManager: LinearLayoutManager {

    constructor(
        context: Context,
        attributeSet: AttributeSet,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(
        context,
        attributeSet,
        defStyleAttr,
        defStyleRes
    )

    constructor(
        context: Context,
        orientation: Int,
        reverseLayout: Boolean
    ): super(
        context,
        orientation,
        reverseLayout

    )
    constructor(context: Context): super(context)

    override fun canScrollVertically(): Boolean {
        return false
    }

    override fun canScrollHorizontally(): Boolean {
        return false
    }
}