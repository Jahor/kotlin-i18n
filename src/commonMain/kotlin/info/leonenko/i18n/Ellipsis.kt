package info.leonenko.i18n

interface EllipsisLanguage {
    val formatter: EllipsisFormatter
    val wordFormatter: EllipsisFormatter
}

data class EllipsisFormatter(val final: String, val initial: String, val medial: String) {
    companion object {
        val Default = EllipsisFormatter("{0}…", "…{0}", "{0}…{1}")
        val WordDefault = EllipsisFormatter("{0} …", "… {0}", "{0} … {1}")
    }

    fun formatFinal(line: String): String = final.replace("{0}", line)
    fun formatInitial(line: String): String = initial.replace("{0}", line)
    fun formatMedial(start: String, end: String): String = medial.replace("{0}", start).replace("{1}", end)
}
