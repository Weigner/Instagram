package com.example.instagram.util

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.instagram.databinding.DialogCustomBinding

class CustomDialog(context: Context) : Dialog(context) {

    private lateinit var binding: DialogCustomBinding

    private lateinit var textButtons: Array<TextView>

    private var titleId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun addButton(vararg texts: Int, listener: View.OnClickListener) {
        textButtons = Array(texts.size) {
            TextView(context)
        }

        texts.forEachIndexed { index, textId ->
            textButtons[index].id = textId
            textButtons[index].setText(textId)
            textButtons[index].setOnClickListener {
                listener.onClick(it)
                dismiss()
            }
        }
    }

    override fun setTitle(titleId: Int) {
        this.titleId = titleId

    }

    override fun show() {
        super.show()
        titleId?.let {
            binding.tvDialogTitle.setText(it)
        }

        for (textView in textButtons) {
            val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(30, 50, 30, 50)
            binding.dialogContainer.addView(textView, layoutParams)
        }
    }

}