package gt.edu.umg.finanzaspersonales

interface MovimientoRepository {
    fun guardar(movimiento: Movimiento): Movimiento

    fun listarTodos(): List<Movimiento>

    fun buscarPorId(id: Int): Movimiento?

    fun actualizar(movimiento: Movimiento): Boolean

    fun eliminar(id: Int): Boolean
}
