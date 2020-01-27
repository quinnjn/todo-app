package app.quinnjn.todo.edit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEachIndexed
import androidx.recyclerview.widget.LinearLayoutManager
import app.quinnjn.todo.*
import app.quinnjn.todo.read.ReadListController
import app.quinnjn.todo.shared.ListModelView
import app.quinnjn.todo.shared.ListModelAdapter
import kotlinx.android.synthetic.main.activity_todo_list.*

class ReadActivity: AppCompatActivity() {
    val controller = ReadListController()
    val listName by lazy {
        intent.extras!!.getString("listName")!!
    }
    val editFile by lazy {
        IO.listNameFile(
            listName
        )
    }
    val yaml by lazy {
        controller.load(editFile)
    }

    val adapter by lazy { ListModelAdapter() }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_edit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_edit ->  {
                startActivityForResult(EditActivity.newInstance(this, listName), CODE_EDIT_LIST);
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CODE_EDIT_LIST) {
            finish()
            startActivity(intent)
        }
    }

    override fun onPause() {
        todo_list.forEachIndexed { i, view ->
            val todoItem = view as ListModelView
            yaml.get(i).checked = todoItem.getChecked()
        }
        controller.save(editFile, yaml)

        super.onPause()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)
        setupViews()
        loadList(yaml)
    }

    private fun setupViews() {
        todo_list.layoutManager = LinearLayoutManager(this)
        todo_list.adapter = adapter
    }

    private fun loadList(list: List<ListModel>) {
        list.forEach {
            adapter.addListModel(it)
        }
    }

    companion object {
        private val CODE_EDIT_LIST = 100
        fun newInstance(context: Context, listName: String): Intent {
            val editIntent = Intent(context, ReadActivity::class.java)
            editIntent.putExtra("listName", listName)

            return editIntent
        }
    }
}