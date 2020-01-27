package app.quinnjn.todo.main

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.quinnjn.todo.IO
import app.quinnjn.todo.edit.ReadActivity

class TodoListsAdapter: RecyclerView.Adapter<TodoListsAdapter.ViewHolder>() {
    val data by lazy {
        val list = mutableListOf<String>()
        list.add("#Lists")
        list.addAll(IO.getListNames())
        list.add("#Templates")
        list.addAll(IO.getTemplateNames())

        list
    }
    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
       val textView: TextView

       init {
           textView = v.findViewById(android.R.id.text1)
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val description = data.get(position)
        val displayValue = description.replace("#", "")
        val textTypeface = if (isHeader(description)) {
            Typeface.BOLD
        } else {
            Typeface.NORMAL
        }

        with(holder.textView) {
            text = displayValue
            setTypeface(typeface, textTypeface)

            setOnClickListener {
                this.context.startActivity(
                    ReadActivity.newInstance(this.context, description)
                )
            }
        }
    }

    private fun isHeader(str: String): Boolean = str.contains("#")
    fun deletable(position: Int): Boolean = isHeader(data.get(position))

    fun delete(position: Int): Boolean {
        if (IO.deleteList(data.get(position))) {
            data.removeAt(position)
            notifyItemRemoved(position)
            return true
        }
        return false
    }

    companion object {
        const val TAG = "TodoListsAdapter"
    }
}