package com.example.ExamenCorto1
import android.app.AlertDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import java.util.Random
import kotlin.collections.ArrayList

/**
 * Andre marroquin 22266
 * Examen corto 1
 * Programacion de plataformas moviles
 * Se uso plantilla del lab2 por eso tiene el nombre de calculadora algunos archivos
 */
class MainActivity : AppCompatActivity() {
    private var gridSize = 3
    private lateinit var buttons: Array<Array<Button>>
    private var player1 = ArrayList<Int>()
    private var player2 = ArrayList<Int>()
    private var currentPlayer = 1
    private var playerNames = arrayOf("", "")

    /**
     * funcion que crea el tablero y lo inicializa
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        askForPlayerNames()
        chooseGridSize()
        randomizeFirstTurn()

    }

    /**
     * funcion que cambia ve de forma random quien es el turno al empezar el juego
     */
    private fun randomizeFirstTurn() {
        val randomIndex = Random().nextInt(playerNames.size)
        val startingPlayerName = playerNames[randomIndex]
        currentPlayer = randomIndex + 1
        updatePlayerNameText("$startingPlayerName")
    }

    /**
     * funcion que le da al usuario que tamanio de tablero quiere
     */
    private fun chooseGridSize() {
        val gridSizeOptions = arrayOf("3x3", "4x4", "5x5")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose Grid Size")
        builder.setItems(gridSizeOptions) { _, which ->
            val selectedSize = gridSizeOptions[which]
            when (selectedSize) {
                "3x3" -> setupGrid(3)
                "4x4" -> setupGrid(4)
                "5x5" -> setupGrid(5)
            }
        }
        builder.show()
    }

    /**
     * funcion que pregunta los 2 nombres de los jugadores
     */
    private fun askForPlayerNames() {
        val inputDialog = AlertDialog.Builder(this)
        val inputLayout = LinearLayout(this)
        inputLayout.orientation = LinearLayout.VERTICAL
        val input1 = EditText(this)
        input1.hint = "Player 1 Name"
        val input2 = EditText(this)
        input2.hint = "Player 2 Name"
        inputLayout.addView(input1)
        inputLayout.addView(input2)
        inputDialog.setTitle("Enter Player Names")
        inputDialog.setView(inputLayout)
        inputDialog.setPositiveButton("OK") { _, _ ->
            playerNames[0] = input1.text.toString()
            playerNames[1] = input2.text.toString()

            randomizeFirstTurn()
        }
        inputDialog.setCancelable(false)
        inputDialog.show()
    }


    /**
     * funcion que crea el tablero y lo inicializa dependiendo del tamanio dado
     */
    private fun setupGrid(size: Int) {
        buttons = Array(size) { row ->
            Array(size) { col ->
                val buttonId =
                    resources.getIdentifier("button${row * size + col + 1}", "id", packageName)
                findViewById<Button>(buttonId).apply {
                    setOnClickListener { buttonClick(this, row, col) }
                }
            }
        }
    }

    /**
     * funcion que se encarga de los botones y de los turnos
     */
    private fun buttonClick(buttonSelected: Button, row: Int, col: Int) {
        val cellID = row * gridSize + col + 1
        playGame(cellID, buttonSelected)
    }



    /**
     * funcion que se encarga de los botones y de los turnos del juego
     */
    private fun playGame(cellID: Int, buSelected: Button) {
        buSelected.isEnabled = false

        if (currentPlayer == 1) {
            buSelected.text = "✕"
            buSelected.setTextColor((Color.parseColor("#d9a5b3")))
            buSelected.setBackgroundColor(Color.parseColor("#8a307f"))
            player1.add(cellID)
            currentPlayer = 2
        } else {
            buSelected.text = "◯"
            buSelected.setTextColor(Color.parseColor("#8a307f"))
            buSelected.setBackgroundColor((Color.parseColor("#d9a5b3")))
            player2.add(cellID)
            currentPlayer = 1
        }

        val currentPlayerName = playerNames[currentPlayer - 1]
        updatePlayerNameText(currentPlayerName)
        Winner()
    }

    /**
     * funcion que actualiza el nombre del jugador y a quien le toca el turno
     */
    private fun updatePlayerNameText(name: String) {
        val playerNameTextView = findViewById<TextView>(R.id.playerNameTextView)
        playerNameTextView.text = "Turno de $name"
    }

    /**
     * funcion que se encarga de ver quien gano el juego
     */
    private fun Winner(): Int {
        var winner = -1

        for (i in 1..gridSize) {
            val player1Row = player1.filter { it <= gridSize * i && it > gridSize * (i - 1) }
            val player2Row = player2.filter { it <= gridSize * i && it > gridSize * (i - 1) }

            if (player1Row.size == gridSize) {
                winner = 1
                break
            }
            if (player2Row.size == gridSize) {
                winner = 2
                break
            }
        }

        for (i in 1..gridSize) {
            val player1Col = player1.filter { it % gridSize == (i % gridSize) || (it % gridSize == 0 && i == gridSize) }
            val player2Col = player2.filter { it % gridSize == (i % gridSize) || (it % gridSize == 0 && i == gridSize) }

            if (player1Col.size == gridSize) {
                winner = 1
                break
            }
            if (player2Col.size == gridSize) {
                winner = 2
                break
            }
        }
        val player1Diagonal1 = player1.filter { it % (gridSize + 1) == 1 }
        val player2Diagonal1 = player2.filter { it % (gridSize + 1) == 1 }
        val player1Diagonal2 = player1.filter { it % (gridSize - 1) == 0 && it <= gridSize * gridSize - gridSize + 1 }
        val player2Diagonal2 = player2.filter { it % (gridSize - 1) == 0 && it <= gridSize * gridSize - gridSize + 1 }

        if (player1Diagonal1.size == gridSize || player1Diagonal2.size == gridSize) {
            winner = 1
        }
        if (player2Diagonal1.size == gridSize || player2Diagonal2.size == gridSize) {
            winner = 2
        }
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("JUEGO TERMINADO")
        if (winner != -1) {
            val winnerName = if (winner == 1) playerNames[0] else playerNames[1]
            builder.setMessage("Ganaste $winnerName. Bien Jugado!")
            builder.setPositiveButton("OK") { dialog, which -> finish() }
            val dialog: AlertDialog = builder.create()
            dialog.show()
            return winner
        }
        return 0
    }

}
