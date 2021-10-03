package com.c0de_h0ng.kakaosearch.common.recyclerview

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by c0de_h0ng on 2021/10/03.
 */
class RecyclerItemListener constructor(private val recyclerView: RecyclerView, private val touchListener: RecyclerTouchListener) : GestureDetector.SimpleOnGestureListener(), RecyclerView.OnItemTouchListener {

    private val gestureDetector = GestureDetector(recyclerView.context, this)

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        e?.let { motionEvent ->
            val view: View? = recyclerView.findChildViewUnder(motionEvent.x, motionEvent.y)
            view?.let { touchListener.onClickItem(it, recyclerView.getChildAdapterPosition(it)) }
        }
        return true
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        val child = recyclerView.findChildViewUnder(e.x, e.y)
        return !isTouchView(e.rawX, e.rawY, child) && child != null && gestureDetector.onTouchEvent(e)
    }

    private fun isTouchView(x: Float, y: Float, child: View?): Boolean {
//        child?.let {
//            val deleteItemButton: ImageButton? = it.findViewById(R.id.btn_delete_item)
//            return if (deleteItemButton != null) {
//                isTouchArea(x, y, deleteItemButton)
//            } else {
//                false
//            }
//        } ?: run { return false}
        return false
    }

    private fun isTouchArea(x: Float, y: Float, view: View): Boolean {
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        val viewX = location[0]
        val viewY = location[1]
        return x > viewX && x < viewX + view.width && y > viewY && y < viewY + view.height
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}

}