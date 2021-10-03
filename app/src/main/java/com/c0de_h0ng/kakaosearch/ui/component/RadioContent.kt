package com.c0de_h0ng.kakaosearch.ui.component

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RadioGroup
import android.widget.TextView
import com.c0de_h0ng.kakaosearch.R

/**
 * Created by c0de_h0ng on 2021/10/03.
 */
class RadioContent @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : RadioGroup(context, attrs), RadioGroup.OnCheckedChangeListener {

    private val radioGroup: RadioGroup

    private val tvLeftContent: TextView
    private val tvRightContent: TextView

    private var checkedChangeListener: OnCheckedChangeListener? = null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.radio_content, this, false)
        addView(view)
        radioGroup = findViewById(R.id.radio_group)
        tvLeftContent = findViewById(R.id.tv_left_content)
        tvRightContent = findViewById(R.id.tv_right_content)
        radioGroup.setOnCheckedChangeListener(this)
        getAttrs(attrs)
    }

    private fun getAttrs(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RadioContent)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
        val leftContent = typedArray.getString(R.styleable.RadioContent_radioLeftContent)
        val rightContent = typedArray.getString(R.styleable.RadioContent_radioRightContent)

        tvLeftContent.text = leftContent
        tvRightContent.text = rightContent
        typedArray.recycle()
    }

    fun setOnCheckedChangedListener(checkedChangeListener: OnCheckedChangeListener) {
        this.checkedChangeListener = checkedChangeListener
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        checkedChangeListener?.onCheckedChanged(group, checkedId)
    }

}