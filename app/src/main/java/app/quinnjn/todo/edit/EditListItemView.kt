package app.quinnjn.todo.edit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import app.quinnjn.todo.R

class EditListItemView : LinearLayout {

    private val view: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.view_todo_list_item, null)
    }

    private val text: TextView by lazy {
        view.findViewById<TextView>(R.id.text)
    }

    private val checkbox: CheckBox by lazy {
        view.findViewById<CheckBox>(R.id.checkbox)
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        addView(view)
    }

    fun setText(t: String) {
      text.text = t
    }

    fun setDepth(depth: Int) {
        view.setPadding(depth * 20, view.top, view.right, view.bottom)
    }

    fun setChecked(checked: Boolean) {
        checkbox.isChecked = checked
    }

    fun getChecked() = checkbox.isChecked
    fun getNameText() = text.text.toString()
}
