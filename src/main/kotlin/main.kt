import java.lang.RuntimeException

fun main() {
    println(NoteService.add(Note("Заголовок", "Текст")))
    println(NoteService.add(Note("Тест 1", "Тест 2")))
    println(NoteService.edit(1, Note("Заголовок 2", "Текст 2")))
    println(CommentService.add(Comment(2, "Привет")))
    println(NoteService.delete(2))
    println(NoteService.cancelDelete(2))
    println(CommentService.delete(1))
}

data class Note(
    val title: String,
    val text: String,
    var comment_privacy: Boolean = true,
    var deleteNote: Boolean? = null
)

data class Comment(
    val id_note: Int,
    val message: String,
    var deleteComment: Boolean? = null
)

interface CRUDService<T, V> {
    fun add(elem: T): T
    fun delete(id: V): Boolean
    fun edit(id: V, elem: T): Boolean
    fun getElem(id: V): T
    fun cancelDelete(id: V): Boolean
}

class NotFoundException(msg: String) : RuntimeException(msg)
object NoteService : CRUDService<Note, Int> {
    private var notes = mutableListOf<Note>()
    override fun add(elem: Note): Note {
        notes += elem
        return notes.last()
    }

    override fun delete(id: Int): Boolean {
        if (notes.getOrNull(id - 1) != null) {
            if (notes[id - 1].deleteNote != true) {
                notes[id - 1].deleteNote = true
                return true
            } else throw NotFoundException("Заметка с id равным $id была удалена")
        }
        throw NotFoundException("Заметка с id равным $id не найдена")
    }

    override fun edit(id: Int, elem: Note): Boolean {
        if (notes.getOrNull(id - 1) != null) {
            if (notes[id - 1].deleteNote != true) {
                notes[id - 1] = elem
                return true
            } else throw NotFoundException("Заметка с id равным $id была удалена")
        }
        throw NotFoundException("Заметка с id равным $id не найдена")
    }

    override fun getElem(id: Int): Note {
        if (notes.getOrNull(id - 1) != null) {
            if (notes[id - 1].deleteNote != true) {
                return notes[id - 1]
            } else throw NotFoundException("Заметка с id равным $id была удалена")
        }
        throw NotFoundException("Заметка с id равным $id не найдена")
    }

    override fun cancelDelete(id: Int): Boolean {
        if (notes.getOrNull(id - 1) != null) {
            if (notes[id - 1].deleteNote == true) {
                notes[id - 1].deleteNote = null
                return true
            } else throw NotFoundException("Заметка с id равным $id не была удалена")
        }
        throw NotFoundException("Заметка с id равным $id не найдена")
    }
}

object CommentService : CRUDService<Comment, Int> {
    private var comments = mutableListOf<Comment>()
    private fun checkDeleteNote (id: Int){
        if (comments.getOrNull(id - 1) != null) {
            NoteService.getElem(comments[id - 1].id_note)
        }
    }

    override fun add(elem: Comment): Comment {
        checkDeleteNote(elem.id_note)

        comments += elem
        return comments.last()
    }

    override fun delete(id: Int): Boolean {
        checkDeleteNote(id)

        if (comments.getOrNull(id - 1) != null) {
            if (comments[id - 1].deleteComment != true) {
                comments[id - 1].deleteComment = true
                return true
            } else throw NotFoundException("Комментарий с id равным $id был удален")
        }
        throw NotFoundException("Комментарий с id равным $id не найден")
    }

    override fun edit(id: Int, elem: Comment): Boolean {
        checkDeleteNote(id)

        if (comments.getOrNull(id - 1) != null) {
            if (comments[id - 1].deleteComment != true) {
                comments[id - 1] = elem.copy(id_note = comments[id-1].id_note)
                return true
            } else throw NotFoundException("Комментарий с id равным $id был удален")
        }
        throw NotFoundException("Комментарий с id равным $id не найден")
    }

    override fun getElem(id: Int): Comment {
        checkDeleteNote(id)

        if (comments.getOrNull(id - 1) != null) {
            if (comments[id - 1].deleteComment != true) {
                return comments[id - 1]
            } else throw NotFoundException("Комментарий с id равным $id был удален")
        }
        throw NotFoundException("Комментарий с id равным $id не найден")
    }

    override fun cancelDelete(id: Int): Boolean {
        checkDeleteNote(id)

        if (comments.getOrNull(id - 1) != null) {
            if (comments[id - 1].deleteComment == true) {
                comments[id - 1].deleteComment = null
                return true
            } else throw NotFoundException("Комментарий с id равным $id не был удален")
        }
        throw NotFoundException("Комментарий с id равным $id не найден")
    }
}
