//Codigo de Ricardo Alecio Garcia
import javax.swing.JOptionPane
import java.awt.Color
import javax.swing.UIManager

fun cifrarRail(texto: String, rieles: Int): String {
    if (rieles < 2) return texto
    val lineas = Array(rieles) { StringBuilder() }
    var nivel = 0
    var bajando = true
    texto.forEach {
        lineas[nivel].append(it)
        if (bajando) {
            if (++nivel == rieles - 1) bajando = false
        } else {
            if (--nivel == 0) bajando = true
        }
    }
    return lineas.joinToString("")
}
fun descifrarRail(textoCifrado: String, rieles: Int): String {
    if (rieles < 2) return textoCifrado
    val posiciones = textoCifrado.indices.map { 0 }.toMutableList()
    var nivel = 0
    var bajando = true

    posiciones.indices.forEach {
        posiciones[it] = nivel
        if (bajando) {
            if (++nivel == rieles - 1) bajando = false
        } else {
            if (--nivel == 0) bajando = true
        }
    }
    val lineas = Array(rieles) { StringBuilder() }
    var index = 0
    for (i in 0 until rieles) {
        posiciones.forEachIndexed { _, pos ->
            if (pos == i) lineas[i].append(textoCifrado[index++])
        }
    }
    val textoDescifrado = StringBuilder()
    posiciones.forEach { pos ->
        textoDescifrado.append(lineas[pos].first())
        lineas[pos].deleteCharAt(0)
    }
    return textoDescifrado.toString()
}
fun main() {
    UIManager.put("OptionPane.background", Color(30, 30, 30))
    UIManager.put("Panel.background", Color(30, 30, 30))
    UIManager.put("OptionPane.messageForeground", Color(200, 200, 200))

    while (true) {
        val opcion = JOptionPane.showOptionDialog(null, "Elige una opción:", "Cifrado Rail Fence",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
            arrayOf("Cifrar", "Descifrar", "Salir"), "Cifrar")

        if (opcion == 2 || opcion == JOptionPane.CLOSED_OPTION) break

        val texto = JOptionPane.showInputDialog("Escribe tu mensaje:")?.takeIf { it.isNotBlank() } ?: continue
        val rieles = JOptionPane.showInputDialog("Número de rieles:")?.toIntOrNull() ?: continue

        val resultado = if (opcion == 0) cifrarRail(texto, rieles) else descifrarRail(texto, rieles)
        JOptionPane.showMessageDialog(null, "Resultado: $resultado")
    }
}
