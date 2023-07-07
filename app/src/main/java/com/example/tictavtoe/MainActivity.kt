package com.example.tictavtoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.tictavtoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var PLAYER = true
    var TURN_COUNT = 0

    var boardStatus = Array(3) { IntArray(3) }

    private lateinit var binding: ActivityMainBinding
    lateinit var board: Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val resetBtn= binding.restBtn
        val displayTv= binding.displayTv

        board = arrayOf(
            arrayOf(binding.button, binding.button2, binding.button3),
            arrayOf(binding.button4, binding.button5, binding.button6),
            arrayOf(binding.button7, binding.button8, binding.button9)
        )

        for (i in board) {
            for (button in i) {
                button.setOnClickListener(this)
            }
        }

        initializeBoardStatus()

        resetBtn.setOnClickListener {
            PLAYER = true
            TURN_COUNT = 0
            initializeBoardStatus()
        }
    }

    private fun initializeBoardStatus() {
        for (i in 0..2) {
            for (j in 0..2) {
                boardStatus[i][j] = -1

            }
        }
        for(i:Array<Button > in board){
            for(button in i){
                button.isEnabled = true
                button.text = " "
            }
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button -> {
                updateValue(row = 0, col = 0, player = PLAYER)
            }
            R.id.button2 -> {
                updateValue(row = 0, col = 1, player = PLAYER)
            }
            R.id.button3 -> {
                updateValue(row = 0, col = 2, player = PLAYER)
            }
            R.id.button4 -> {
                updateValue(row = 1, col = 0, player = PLAYER)
            }
            R.id.button5 -> {
                updateValue(row = 1, col = 1, player = PLAYER)
            }
            R.id.button6 -> {
                updateValue(row = 1, col = 2, player = PLAYER)
            }
            R.id.button7 -> {
                updateValue(row = 2, col = 0, player = PLAYER)
            }
            R.id.button8 -> {
                updateValue(row = 2, col = 1, player = PLAYER)
            }
            R.id.button9 -> {
                updateValue(row = 2, col = 2, player = PLAYER)
            }
        }
        TURN_COUNT++
        PLAYER = !PLAYER
        if(PLAYER){
            updateDisplay("Player X turn")
        }else{
            updateDisplay("Player 0 turn")

        }
         if(TURN_COUNT==9){
             updateDisplay("Game Draw")
         }
        checkWinner()

    }

    private fun checkWinner() {
        //horizontal
         for(i in 0..2){
             if(boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] ==boardStatus[i][2] ) {
                 if (boardStatus[i][0] == 1) {
                     updateDisplay("Player X Winner")
                     break
                 } else if (boardStatus[i][0] == 0) {
                     updateDisplay("Player 0 Winner")
                     break
                 }
             }
         }
          // vertical Columns
        for(i in 0..2){
            if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] ==boardStatus[2][i] ) {
                if (boardStatus[0][i] == 1) {
                    updateDisplay("Player X Winner")
                    break
                } else if (boardStatus[0][i] == 0) {
                    updateDisplay("Player 0 Winner")
                    break
                }
            }
        }

        //1st diagonal
        if(boardStatus [0][0] == boardStatus[1][1] && boardStatus [0][0] == boardStatus[2][2]){
            if (boardStatus[0][0] == 1) {
                updateDisplay("Player X Winner")
            } else if (boardStatus[0][0] == 0) {
                updateDisplay("Player 0 Winner")
            }
        }




        //2st diagonal
        if(boardStatus [0][2] == boardStatus[1][1] && boardStatus [0][2] == boardStatus[2][0]){
            if (boardStatus[0][2] == 1) {
                updateDisplay("Player X Winner")
            } else if (boardStatus[0][2] == 0) {
                updateDisplay("Player 0 Winner")
            }
        }

    }

    private fun updateDisplay(text: String) {
     binding.displayTv.text = text
        if(text.contains("Winner")){
            disableButton()
        }
    }
    private fun disableButton(){
        for (i in board) {
            for (button in i) {
                button.isEnabled = false
            }
        }

    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text: String = if (player) "X" else "O"
        val value = if (player) 1 else 0

        board[row][col].apply {
            isEnabled = false
            setText(text)
        }
        boardStatus[row][col] = value


    }
}
