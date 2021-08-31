package ks.hs.dgsw.toss.ui.view.decoration

import android.app.Activity
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.graphics.Rect
import android.util.TypedValue

class GridLayoutSpacingDecoration(activity: Activity, spanCount: Int): RecyclerView.ItemDecoration() {

    private var spanCount = 0
    private var spacing = 0
    private var outerMargin = 0

    init {
        this.spanCount = spanCount
        spacing = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            12f,
            activity.resources.displayMetrics
        ).toInt()
        outerMargin =
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                50f,
                activity.resources.displayMetrics
            ).toInt()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val maxCount = parent.adapter!!.itemCount
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount
        val row = position / spanCount
        val lastRow = (maxCount - 1) / spanCount
        outRect.left = column * spacing / spanCount
        outRect.right = spacing - (column + 1) * spacing / spanCount
        outRect.top = spacing
        if (row == lastRow) {
            outRect.bottom = outerMargin
        }
    }
}