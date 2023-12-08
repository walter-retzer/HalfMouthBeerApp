package app.halfmouth.android.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone


fun String.formattedDate(): String {
    var string = ""
    try {
        val dateFormat: DateFormat = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss'Z'",
            Locale("pt", "BR")
        )
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date = dateFormat.parse(this)
        val dateTimeFormat = SimpleDateFormat("dd/MM/yyyy - HH:mm:ss", Locale("pt", "BR"))
        string = dateTimeFormat.format(date)
    } catch (e: Exception) {
        println("Erro ao Converter String: $e")
    }
    return string
}

fun String.formattedAsPhone(): String {
    return when (length) {
        11 -> "(" + substring(0, 2) + ") " +
                substring(2, 3) + " " +
                substring(3, 7) + "-" +
                substring(7, length)
        10 -> "(" + substring(0, 2) + ") " +
                substring(2, 6) + "-" +
                substring(6, length)

        14 -> substring(0, 3) + " (" + substring(3, 5) + ") " +
                substring(5, 7) +
                substring(7, 10) + "-" +
                substring(10, length)

        else -> this
    }
}