package ie.setu.utils
import java.lang.System.exit
import io.github.oshai.kotlinlogging.KotlinLogging


private val logger = KotlinLogging.logger {}

fun main() {
    runMenu()
}


fun MainMenu(): Int {
    print("""
         > ----------------------------------
         > |        NOTE KEEPER APP         |
         > ----------------------------------
         > | NOTE MENU                      |
         > |   1) Add a note                |
         > |   2) List all notes            |
         > |   3) Update a note             |
         > |   4) Delete a note             |
         > ----------------------------------
         > |   0) Exit                      |
         > ----------------------------------
         > ==>> """.trimMargin(">"))
    return  readIntNotNull()
}

fun readNextLine(prompt: String?):String {
print(prompt)
return readln()
}


fun readNextChar(prompt: String?): Char {
    do {
        try {
            print(prompt)
            return readln().first()
        } catch (e: NumberFormatException) {
            System.err.println("\tEnter a character please.")
        }
    } while (true)
}


fun runMenu() {
do{
    when(val option = MainMenu()) {
        1 -> addNotes()
        2 -> listNotes()
        3 -> updateNote()
        4 -> deleteNote()
        0 -> exitApp()
        else -> println("Invalid option entered: ${option}")
    }
}  while(true)
}

fun addNotes(){
    logger.info {"addNote() function invoked"}
}

fun listNotes(){
    logger.info {"listNotes() function invoked"}
}

fun updateNote(){
    logger.info{"updateNote() function invoked"}
}

fun deleteNote(){
    logger.info{"deleteNote() function invoked"}
}

fun exitApp(){
    println("Exiting...bye")
    exit(0)
}