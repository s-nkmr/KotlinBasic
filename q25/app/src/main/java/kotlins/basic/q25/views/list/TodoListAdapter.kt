package kotlins.basic.q25.views.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlins.basic.q25.R
import kotlins.basic.q25.models.entity.Todo
import kotlins.basic.q25.utils.DateUtil

class TodoListAdapter(
    var todoList: List<Todo>
) : RecyclerView.Adapter<TodoListViewHolder>() {
    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        holder.title.text = todoList[position].title
        holder.limit.text = DateUtil.toString(todoList[position].limitDate, DateUtil.DateFormat.HyphenYearToDay)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        return TodoListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.todo_cell, parent, false))
    }
}