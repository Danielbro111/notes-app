package controllers

import models.Note
import org.jetbrains.annotations.TestOnly
import persistence.Serializer
import utils.isValidListIndex

class NoteAPI(serializerType: Serializer) {
    private var serializer: Serializer = serializerType
    private var notes = ArrayList<Note>()

    fun add(note: Note): Boolean {
        return notes.add(note)
    }

    fun listAllNotes(): String =
        if (notes.isEmpty()) "No notes stored"
        else notes.joinToString(separator = "\n") { note ->
            notes.indexOf(note).toString() + ":" + note.toString()
        }

    fun listArchivedNotes(): String =
        if (numberOfArchivedNotes() == 0) "No archived notes stored"
        else notes.filter { note -> note.isNoteArchived }
            .joinToString(separator = "\n") { note ->
                notes.indexOf(note).toString() + ":" + note.toString()
            }


    fun listActiveNotes(): String {
        return if (numberOfActiveNotes() == 0) {
            "No active notes stored"
        } else {
            var listOfActiveNotes = ""
            for (note in notes) {
                if (!note.isNoteArchived) {
                    listOfActiveNotes += "${notes.indexOf(note)}: $note \n"
                }
            }
            listOfActiveNotes
        }
    }


    fun listNotesBySelectedPriority(priority: Int): String {
        return if (notes.isEmpty()) {
            "No notes stored"
        } else {
            var listOfNotes = ""
            for (i in notes.indices) {
                if (notes[i].notePriority == priority) {
                    listOfNotes +=
                        """$i: ${notes[i]}
                        """.trimIndent()
                }
            }
            if (listOfNotes.equals("")) {
                "No notes with priority: $priority"
            } else {
                "${numberOfNotesByPriority(priority)} notes with priority $priority: $listOfNotes"
            }
        }
    }

    fun numberOfNotes(): Int {
        return notes.size
    }

    fun numberOfArchivedNotes(): Int = notes.count { note: Note -> note.isNoteArchived }

    fun numberOfActiveNotes(): Int = notes.count { note: Note -> !note.isNoteArchived }



    fun numberOfNotesByPriority(priority: Int): Int {
        return notes.count { note: Note -> note.notePriority == priority }

    }

    fun findNote(index: Int): Note? {
        return if (isValidListIndex(index, notes)) {
            notes[index]
        } else null
    }


    fun deleteNote(indexToDelete: Int): Note? {
        return if (isValidListIndex(indexToDelete, notes)) {
            notes.removeAt(indexToDelete)
        } else null
    }

    fun searchByTitle(searchString: String) =
        notes.filter { note -> note.noteTitle.contains(searchString, ignoreCase = true) }
            .joinToString(separator = "\n") { note ->
                notes.indexOf(note).toString() + ":" + note.toString()
            }


    @Throws(Exception::class)
    fun load() {
        notes = serializer.read() as ArrayList<Note>
    }

    @Throws(Exception::class)
    fun store() {
        serializer.write(notes)
    }


    fun isValidIndex(index: Int): Boolean {
        return isValidListIndex(index, notes);
    }


    fun updateNote(indexToUpdate: Int, note: Note?): Boolean {
        //find the note object by the index number
        val foundNote = findNote(indexToUpdate)

        //if the note exists, use the note details passed as parameters to update the found note in the ArrayList.
        if ((foundNote != null) && (note != null)) {
            foundNote.noteTitle = note.noteTitle
            foundNote.notePriority = note.notePriority
            foundNote.noteCategory = note.noteCategory
            return true
        }

        //if the note was not found, return false, indicating that the update was not successful
        return false
    }

    fun archiveNote(indexToArchive: Int): Boolean {
        if (isValidIndex(indexToArchive)) {
            val noteToArchive = notes[indexToArchive]
            if (!noteToArchive.isNoteArchived) {
                noteToArchive.isNoteArchived = true
                return true
            }
        }
        return false
    }
}




