package gt.edu.umg.finanzaspersonales

import java.time.LocalDate

data class Movimiento(
    val id: Int,
    val descripcion: String,
    val monto: Double,
    val tipoMovimiento: TipoMovimiento,
    val categoria: Categoria,
    val fecha: LocalDate
)
