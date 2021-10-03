package com.c0de_h0ng.kakaosearch.base

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.c0de_h0ng.kakaosearch.R

/**
 * Created by c0de_h0ng on 2021/10/02.
 */
abstract class BaseActivity<T : ViewDataBinding> constructor(@LayoutRes private val layoutResId: Int) : AppCompatActivity(), View.OnClickListener {

    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = window.decorView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        }
        binding = DataBindingUtil.setContentView(this, layoutResId)
        bindingProperty()
    }

    abstract fun bindingProperty()

    override fun onClick(v: View) {

    }

    open fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        currentFocus?.let {
            imm.hideSoftInputFromWindow(it.windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)
        }
    }

    fun setFont(font: Int, v: TextView) {
        v.typeface = ResourcesCompat.getFont(this, font)
        v.includeFontPadding = false
    }

}