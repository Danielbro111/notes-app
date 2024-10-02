package ie.setu.utils

fun readIntNotNull() = readlnOrNull()?.toIntOrNull() ?: -1

fun rreadNextInt(prompt: String?): Int {
    do{
        try{
            print(prompt)
            return readln().toInt()
        } catch (e: NumberFormatException) {
            System.err.println("\tEnter a number please.")
        }
    } while(true)
}