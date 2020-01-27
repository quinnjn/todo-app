package app.quinnjn.todo.shared

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.quinnjn.todo.ListModel

class ListModelAdapter: RecyclerView.Adapter<ListModelAdapter.ViewHolder>() {
    class ViewHolder(val view: ListModelView): RecyclerView.ViewHolder(view)

    private val data = mutableListOf<ListModel>()

    fun addListModel(model: ListModel): Boolean = data.add(model)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ListModelView(parent.context)
        )

    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data.get(position)

        with(holder.view) {
            setText(item.description)
            setDepth(item.depth)
            setChecked(item.checked)
        }
    }
}
