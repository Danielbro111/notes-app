package models

data class Note(
    var noteTitle: String,
    var notePriority: Int,
    var noteCategory: String,
    var isNoteArchived:Boolean,
    var noteContents: String,
    var noteStatus: String,
    var noteID: Double)



