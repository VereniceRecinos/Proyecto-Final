package gt.edu.umg.finanzaspersonales

data class Usuario(
    val id: Int,
    val nombres: String,
    val apellidos: String = ""
)
