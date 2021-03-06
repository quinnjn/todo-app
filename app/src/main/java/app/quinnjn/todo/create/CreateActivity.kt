package app.quinnjn.todo.create

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import app.quinnjn.todo.IO
import app.quinnjn.todo.R
import app.quinnjn.todo.edit.EditListItemView
import kotlinx.android.synthetic.main.activity_create.*
import kotlinx.android.synthetic.main.content_create.*

class CreateActivity: AppCompatActivity() {
    val controller = CreateListController()
    val templateNames by lazy {
        IO.getTemplateNames()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        fab.setOnClickListener {
            val name = name.text.toString()
            val contents = edit_text_content.text.toString()
            val type = if (radio_list.isChecked) {
                IO.Companion.LIST_TYPES.LIST
            } else {
                IO.Companion.LIST_TYPES.TEMPLATE

            }

            if (!radio_list.isChecked && !radio_template.isChecked) {
                showError("Select a list kind")
                return@setOnClickListener
            }

            if (name.isEmpty()) {
                showError("Enter a name for the list")
                return@setOnClickListener
            }

            if (contents.isEmpty()) {
                showError("Enter list contents")
                return@setOnClickListener
            }

            val models = mutableListOf<String>()

            templates.forEach {
                val view = it as EditListItemView

                if (view.getChecked()) {
                    models.add(view.getNameText())
                }
            }

            val error = controller.save(
                name,
                type,
                models,
                contents
            )

            error?.let {
                showError(error)
                return@setOnClickListener
            }

            finish()
        }

        templateNames.forEach {
            val view = EditListItemView(this)

            view.setChecked(false)
            view.setDepth(0)
            view.setText(it)

            templates.addView(view)
        }
    }

    private fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, CreateActivity::class.java)
        }
    }
}
