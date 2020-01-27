package app.quinnjn.todo.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.quinnjn.todo.R

class TodoListsView: Fragment() {
    companion object {
        private val TAG = "TodoListViewFragment"
    }

    private lateinit var recyclerView: RecyclerView
    private val adapter by lazy {
        TodoListsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_todo_list_view, container, false)
            .apply { tag = TAG }

        with(root) {
            recyclerView = findViewById(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
            ItemTouchHelper(SwipeLeftToDeleteCallback(adapter)).attachToRecyclerView(recyclerView)
        }

        return root
    }
}