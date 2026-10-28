package gt.edu.umg.finanzaspersonales

import java.time.LocalDate
import java.time.YearMonth

fun main() {
    val repositorio = MovimientoRepositoryMemoria()
    val control = ControlFinanzas(repositorio)
    GeneradorIds.reiniciar(desde = 3)

    val categoriaIngreso = Categoria(
        id = 1,
        nombre = "Apoyo familiar",
        tipoMovimiento = TipoMovimiento.INGRESO
    )

    val categoriaEgreso = Categoria(
        id = 2,
        nombre = "Alimentación",
        tipoMovimiento = TipoMovimiento.EGRESO
    )

    control.registrarMovimiento(
        Movimiento(
            id = 1,
            descripcion = "Dinero para la universidad",
            monto = 500.0,
            tipoMovimiento = TipoMovimiento.INGRESO,
            categoria = categoriaIngreso,
            fecha = LocalDate.of(2026, 10, 27)
        )
    )

    control.registrarMovimiento(
        Movimiento(
            id = 2,
            descripcion = "Almuerzo",
            monto = 650.0,
            tipoMovimiento = TipoMovimiento.EGRESO,
            categoria = categoriaEgreso,
            fecha = LocalDate.of(2026, 10, 27)
        )
    )

    println("TOTAL DE INGRESOS: Q${control.calcularTotalIngresos()}")
    println("TOTAL DE EGRESOS: Q${control.calcularTotalEgresos()}")
    println("SALDO DISPONIBLE: Q${control.calcularSaldoDisponible()}")

    val periodo = YearMonth.of(2026, 10)
    val resumen = control.generarResumenMensual(periodo)

    println()
    println("RESUMEN DE OCTUBRE DE 2026")
    println("Ingresos del mes: Q${resumen.totalIngresos}")
    println("Egresos del mes: Q${resumen.totalEgresos}")
    println("Resultado del mes: Q${resumen.resultado}")
    println("¿Hubo superávit?: ${resumen.huboSuperavit}")
    println("¿Hubo déficit?: ${resumen.huboDeficit}")
    println("Porcentaje de egresos: ${resumen.porcentajeEgresos}%")

    val otroPeriodo = YearMonth.of(2026, 9)
    val resumenSeptiembre = control.generarResumenMensual(otroPeriodo)

    println()
    println("RESUMEN DE SEPTIEMBRE DE 2026")
    println("Ingresos del mes: Q${resumenSeptiembre.totalIngresos}")
    println("Egresos del mes: Q${resumenSeptiembre.totalEgresos}")
    println("Resultado del mes: Q${resumenSeptiembre.resultado}")

    println()
    println("LISTA DE INGRESOS")

    control.listarIngresos().forEach {
        println("${it.descripcion}: Q${it.monto}")
    }

    println()
    println("LISTA DE EGRESOS")

    control.listarEgresos().forEach {
        println("${it.descripcion}: Q${it.monto}")
    }

    println()
    println("PRUEBA DE REGISTRO AUTOMÁTICO")

    val nuevoMovimiento = control.registrarMovimiento(
        descripcion = "Pasaje a la universidad",
        monto = 30.0,
        tipoMovimiento = TipoMovimiento.EGRESO,
        categoria = categoriaEgreso,
        fecha = LocalDate.of(2026, 10, 27)
    )

    println("ID generado: ${nuevoMovimiento.id}")
    println("Descripción: ${nuevoMovimiento.descripcion}")
    println("Monto: Q${nuevoMovimiento.monto}")

    println()
    println("MOVIMIENTOS DE OCTUBRE DE 2026")

    val movimientosOctubre = control.listarMovimientosPorMes(
        YearMonth.of(2026, 10)
    )

    movimientosOctubre.forEach { movimiento ->
        println(
            "${movimiento.fecha} | ${movimiento.descripcion} | " +
                    "${movimiento.tipoMovimiento} | Q${movimiento.monto}"
        )
    }
}
