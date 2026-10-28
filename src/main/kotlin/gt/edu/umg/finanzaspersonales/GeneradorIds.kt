package gt.edu.umg.finanzaspersonales

object GeneradorIds {

    private var siguienteId = 1

    fun generar(): Int {
        val idGenerado = siguienteId
        siguienteId++
        return idGenerado
    }

    fun reiniciar(desde: Int = 1) {
        require(desde > 0) {
            "El ID inicial debe ser mayor que cero."
        }

        siguienteId = desde
    }
}
