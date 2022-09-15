package com.example.poten.Login

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewDecoration(var count:Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom=20
        outRect.left=10
        outRect.right=0


        if (parent.getChildLayoutPosition(view) %count==0) {
            outRect.left = 0;
        }

    }

}