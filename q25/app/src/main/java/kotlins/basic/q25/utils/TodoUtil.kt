package kotlins.basic.q25.utils

import io.realm.Realm
import kotlins.basic.q25.models.entity.Todo
import java.util.*

object TodoUtil {
    fun add(title: String, content: String) {
        val todo = Todo()
        todo.todoId = createNewId()
        todo.title = title
        todo.contents = content
        todo.created = Date()
        todo.modified = Date()
        todo.limitDate = DateUtil.getDaysAfter(7)
        todo.deleteFlg = false

        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()

        realm.copyToRealm(todo)

        realm.commitTransaction()
    }

    fun update(todo: Todo, title: String, content: String) {

        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()

        todo.title = title
        todo.contents = content
        todo.modified = Date()
        todo.limitDate = DateUtil.getDaysAfter(7)

        realm.copyToRealmOrUpdate(todo)

        realm.commitTransaction()
    }

    fun getTodo(todoId: Int): Todo {
        val realm = Realm.getDefaultInstance()
        val result = realm.where(Todo::class.java).equalTo("todoId", todoId)

        return result.findFirst()!!
    }

    private fun createNewId(): Int {
        val realm = Realm.getDefaultInstance()
        return if (realm.where(Todo::class.java).count() == 0L) 1
        else realm.where(Todo::class.java).max("todoId")?.toInt()?.plus(1) ?: 1
    }
}