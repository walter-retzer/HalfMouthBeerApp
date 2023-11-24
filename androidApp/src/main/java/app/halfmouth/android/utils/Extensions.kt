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
