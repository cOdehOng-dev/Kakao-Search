package com.c0de_h0ng.kakaosearch.ui.component

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.c0de_h0ng.kakaosearch.R

/**
 * Created by c0de_h0ng on 2021/10/03.
 */
class IntegrateSearchContent @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    private val tvTitle: TextView
    private val _contentRecyclerView: RecyclerView
    private val _moreButton: Button

    //private var recyclerTouchListener: RecyclerTouchListener? = null

    val contentRecyclerView: RecyclerView
        get() = _contentRecyclerView

    val moreButton: Button
        get() = _moreButton

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.integrate_search_content, this, false)
        addView(view)
        tvTitle = findViewById(R.id.tv_title)
        _contentRecyclerView = findViewById(R.id.integrate_search_list_view)
        _moreButton = findViewById(R.id.more_button)
        getAttrs(attrs)
    }

    private fun getAttrs(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.IntegrateSearchContent)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
        val title = typedArray.getString(R.styleable.IntegrateSearchContent_integrateSearchTitle)
        val buttonText = typedArray.getString(R.styleable.IntegrateSearchContent_integrateSearchButtonText)
        val buttonTag = typedArray.getString(R.styleable.IntegrateSearchContent_integrateSearchButtonTag)
        tvTitle.text = title
        _moreButton.tag = buttonTag
        _moreButton.text = buttonText
        typedArray.recycle()
    }

    fun setIntegrateSearchButtonTag(buttonTag: Int) {
        _moreButton.tag = buttonTag
    }

//    fun setIntegrateContentRecyclerViewAdapter(adapter: BaseListAdapter<*, *>) {
//        this._contentRecyclerView.adapter = adapter
//    }
//
//    fun setIntegrateContentRecyclerTouchListener(recyclerTouchListener: RecyclerTouchListener) {
//        this.recyclerTouchListener = recyclerTouchListener
//    }


}