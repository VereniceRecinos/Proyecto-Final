
package gt.edu.umg.finanzaspersonales

import java.time.YearMonth

class ControlFinanzas(
    private val movimientoRepository: MovimientoRepository
) {

    fun registrarMovimiento(movimiento: Movimiento): Movimiento {
        require(movimiento.descripcion.isNotBlank()) {
            "La descripción es obligatoria."
        }

        require(movimiento.monto > 0) {
            "El monto debe ser mayor que cero."
        }

        return movimientoRepository.guardar(movimiento)
    }

    fun registrarMovimiento(
        descripcion: String,
        monto: Double,
        tipoMovimiento: TipoMovimiento,
        categoria: Categoria,
        fecha: java.time.LocalDate
    ): Movimiento {

        val movimiento = Movimiento(
            id = GeneradorIds.generar(),
            descripcion = descripcion,
            monto = monto,
            tipoMovimiento = tipoMovimiento,
            categoria = categoria,
            fecha = fecha
        )

        return registrarMovimiento(movimiento)
    }

    fun listarMovimientos(): List<Movimiento> {
        return movimientoRepository.listarTodos()
            .sortedWith(
                compareByDescending<Movimiento> { it.fecha }
                    .thenByDescending { it.id }
            )
    }

    fun listarIngresos(): List<Movimiento> {
        return listarMovimientos()
            .filter { it.tipoMovimiento == TipoMovimiento.INGRESO }
    }

    fun listarEgresos(): List<Movimiento> {
        return listarMovimientos()
            .filter { it.tipoMovimiento == TipoMovimiento.EGRESO }
    }

    fun listarMovimientosPorMes(
        periodo: java.time.YearMonth
    ): List<Movimiento> {
        return listarMovimientos()
            .filter {
                java.time.YearMonth.from(it.fecha) == periodo
            }
    }

    fun buscarMovimiento(id: Int): Movimiento? {
        return movimientoRepository.buscarPorId(id)
    }

    fun actualizarMovimiento(movimiento: Movimiento): Boolean {
        require(movimiento.descripcion.isNotBlank()) {
            "La descripción es obligatoria."
        }

        require(movimiento.monto > 0) {
            "El monto debe ser mayor que cero."
        }

        return movimientoRepository.actualizar(movimiento)
    }

    fun eliminarMovimiento(id: Int): Boolean {
        return movimientoRepository.eliminar(id)
    }

    fun calcularTotalIngresos(): Double {
        return movimientoRepository.listarTodos()
            .filter { it.tipoMovimiento == TipoMovimiento.INGRESO }
            .sumOf { it.monto }
    }

    fun calcularTotalEgresos(): Double {
        return movimientoRepository.listarTodos()
            .filter { it.tipoMovimiento == TipoMovimiento.EGRESO }
            .sumOf { it.monto }
    }

    fun calcularSaldoDisponible(): Double {
        val saldo = calcularTotalIngresos() - calcularTotalEgresos()
        return maxOf(0.0, saldo)
    }

    fun generarResumenMensual(periodo: YearMonth): ResumenMensual {
        val movimientosDelMes = movimientoRepository.listarTodos()
            .filter {
                YearMonth.from(it.fecha) == periodo
            }

        val ingresos = movimientosDelMes
            .filter { it.tipoMovimiento == TipoMovimiento.INGRESO }
            .sumOf { it.monto }

        val egresos = movimientosDelMes
            .filter { it.tipoMovimiento == TipoMovimiento.EGRESO }
            .sumOf { it.monto }

        return ResumenMensual(
            periodo = periodo,
            totalIngresos = ingresos,
            totalEgresos = egresos
        )
    }
}
