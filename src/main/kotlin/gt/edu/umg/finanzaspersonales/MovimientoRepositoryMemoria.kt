package gt.edu.umg.finanzaspersonales

class MovimientoRepositoryMemoria : MovimientoRepository {

    private val movimientos = mutableListOf<Movimiento>()

    override fun guardar(movimiento: Movimiento): Movimiento {
        movimientos.add(movimiento)
        return movimiento
    }

    override fun listarTodos(): List<Movimiento> {
        return movimientos.toList()
    }

    override fun buscarPorId(id: Int): Movimiento? {
        return movimientos.find { it.id == id }
    }

    override fun actualizar(movimiento: Movimiento): Boolean {
        val indice = movimientos.indexOfFirst { it.id == movimiento.id }

        if (indice == -1) {
            return false
        }

        movimientos[indice] = movimiento
        return true
    }

    override fun eliminar(id: Int): Boolean {
        return movimientos.removeIf { it.id == id }
    }
}
