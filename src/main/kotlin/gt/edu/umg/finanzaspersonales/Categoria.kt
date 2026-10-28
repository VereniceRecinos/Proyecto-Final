package gt.edu.umg.finanzaspersonales

data class Categoria(
    val id: Int,
    val nombre: String,
    val tipoMovimiento: TipoMovimiento
)
