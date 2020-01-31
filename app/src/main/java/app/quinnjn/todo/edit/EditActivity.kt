package app.quinnjn.todo.edit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.quinnjn.todo.*
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity: AppCompatActivity() {
    val controller = EditListController()
    val editFile by lazy {
        IO.listNameFile(
            intent.extras!!.getString("listName")!!
        )
    }
    val rawYaml by lazy {
        controller.load(editFile)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        edit_text.setText(rawYaml)

        fab.setOnClickListener {
            save()
        }
    }

    private fun save() {
        val errors = controller.save(editFile, edit_text.text.toString())

        errors?.let {
            showError(it)
            return
        }

        finish()
    }

    private fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance(context: Context, listName: String): Intent {
            val editIntent = Intent(context, EditActivity::class.java)
            editIntent.putExtra("listName", listName)

            return editIntent
        }
    }
}