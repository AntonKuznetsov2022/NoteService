import org.junit.Test

import org.junit.Assert.*

class NoteServiceTest {

    @Test(expected = NotFoundException::class)
    fun deleteNote_1() {
        NoteService.delete(200)
    }

    @Test(expected = NotFoundException::class)
    fun deleteNote_2() {
        NoteService.add(Note("Тест", "Тест"))
        NoteService.delete(1)
        NoteService.delete(1)
    }

    @Test
    fun deleteNote_3() {
        NoteService.add(Note("Тест", "Тест"))
        assertTrue(NoteService.delete(2))
    }

    @Test(expected = NotFoundException::class)
    fun editNote_1() {
        NoteService.edit(1000, Note("Тест 2", "Тест 2"))
    }

    @Test(expected = NotFoundException::class)
    fun editNote_2() {
        NoteService.add(Note("Тест", "Тест"))
        NoteService.delete(3)
        NoteService.edit(3, Note("Тест 2", "Тест 2"))
    }

    @Test
    fun editNote_3() {
        NoteService.add(Note("Тест", "Тест"))
        assertTrue(NoteService.edit(4, Note("Тест 2", "Тест 2")))
    }

    @Test(expected = NotFoundException::class)
    fun getElemNote_1() {
        NoteService.getElem(1000)
    }

    @Test(expected = NotFoundException::class)
    fun getElemNote_2() {
        NoteService.add(Note("Тест 3", "Тест 3"))
        NoteService.delete(5)
        NoteService.getElem(5)
    }

    @Test
    fun getElemNote_3() {
        NoteService.add(Note("Тест 4", "Тест 4"))
        NoteService.getElem(4)
    }

    @Test(expected = NotFoundException::class)
    fun cancelDelete_1() {
        NoteService.cancelDelete(50)
    }

    @Test(expected = NotFoundException::class)
    fun cancelDelete_2() {
        NoteService.add(Note("Тест 5", "Тест 5"))
        NoteService.cancelDelete(1)
    }

    @Test
    fun cancelDelete_3() {
        NoteService.add(Note("Тест 5", "Тест 5"))
        NoteService.delete(1)
        assertTrue(NoteService.cancelDelete(1))
    }
}
