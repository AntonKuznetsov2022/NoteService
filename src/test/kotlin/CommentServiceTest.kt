import org.junit.Test

import org.junit.Assert.*

class CommentServiceTest {
    @Test(expected = NotFoundException::class)
    fun deleteComment_1() {
        CommentService.delete(200)
    }
    @Test(expected = NotFoundException::class)
    fun deleteComment_2() {
        NoteService.add(Note("Тест", "Тест"))
        CommentService.add(Comment(1,"Тест"))
        CommentService.delete(1)
        CommentService.delete(1)
    }
    @Test
    fun deleteComment_3() {
        NoteService.add(Note("Тест", "Тест"))
        CommentService.add(Comment(2,"Тест"))
        assertTrue(CommentService.delete(2))
    }

    @Test(expected = NotFoundException::class)
    fun editComment_1() {
        CommentService.edit(200, Comment(2, "Тест 2"))
    }
    @Test(expected = NotFoundException::class)
    fun editComment_2() {
        NoteService.add(Note("Тест", "Тест"))
        CommentService.add(Comment(1,"Тест"))
        CommentService.delete(3)
        CommentService.edit(3, Comment(2, "Тест 2"))
    }
    @Test
    fun editComment_3() {
        NoteService.add(Note("Тест 4", "Тест 4"))
        CommentService.add(Comment(1,"Тест 4"))
        assertTrue(CommentService.edit(4, Comment(2, "Тест 2")))
    }

    @Test(expected = NotFoundException::class)
    fun getElemComment_1() {
        CommentService.getElem(200)
    }
    @Test(expected = NotFoundException::class)
    fun getElemComment_2() {
        NoteService.add(Note("Тест", "Тест"))
        CommentService.add(Comment(1,"Тест"))
        CommentService.delete(3)
        CommentService.getElem(3)
    }
    @Test
    fun getElemComment_3() {
        NoteService.add(Note("Тест", "Тест"))
        CommentService.add(Comment(1,"Тест"))
        CommentService.getElem(4)
    }

    @Test(expected = NotFoundException::class)
    fun cancelDelete_1() {
        CommentService.cancelDelete(200)
    }
    @Test(expected = NotFoundException::class)
    fun cancelDelete_2() {
        NoteService.add(Note("Тест", "Тест"))
        CommentService.add(Comment(1,"Тест"))
        CommentService.cancelDelete(1)
    }
    @Test
    fun cancelDelete_3() {
        NoteService.add(Note("Тест", "Тест"))
        CommentService.add(Comment(1,"Тест"))
        CommentService.delete(1)
        CommentService.cancelDelete(1)
    }
}