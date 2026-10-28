package gt.edu.umg.finanzaspersonales

import java.time.YearMonth

data class ResumenMensual(
    val periodo: YearMonth,
    val totalIngresos: Double,
    val totalEgresos: Double
) {

    val resultado: Double
        get() = totalIngresos - totalEgresos

    val huboSuperavit: Boolean
        get() = resultado > 0

    val huboDeficit: Boolean
        get() = resultado < 0

    val ingresosIgualesAEgresos: Boolean
        get() = resultado == 0.0

    val porcentajeEgresos: Double
        get() = if (totalIngresos > 0) {
            (totalEgresos / totalIngresos) * 100
        } else {
            0.0
        }
}
