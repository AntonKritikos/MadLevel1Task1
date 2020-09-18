package com.example.madlevel1task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.madlevel1task1.databinding.ActivityHigherLowerBinding

class HigherLowerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHigherLowerBinding
    private var currentThrow: Int = 1;
    private var lastThrow: Int = 1;
    private var diceBox = arrayOf(R.drawable.dice1, R.drawable.dice2,R.drawable.dice3,R.drawable.dice4,R.drawable.dice5, R.drawable.dice6);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_higher_lower)
        binding = ActivityHigherLowerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView();
    }

    private fun initView() {
        binding.button.setOnClickListener{ onClick(binding.button.text.toString()) }
        binding.button2.setOnClickListener{ onClick(binding.button2.text.toString()) }
        binding.button3.setOnClickListener{ onClick(binding.button3.text.toString()) }
        updateUI();
    }

    private fun updateUI() {
        binding.resultView.text = getString(R.string.last_throw,lastThrow)
        binding.imageView.setImageResource(diceBox[currentThrow-1])
    }

    private fun rollDice() {
        lastThrow = currentThrow;
        currentThrow = (1..6).random();
        updateUI();
    }

    private fun onAnswerCorrect() {
        Toast.makeText(this,getString(R.string.correct),Toast.LENGTH_LONG).show();
    }
    private fun onAnswerIncorrect() {
        Toast.makeText(this,getString(R.string.incorrect),Toast.LENGTH_LONG).show();
    }

    private fun onClick(guess: String) {

        rollDice();

        when (guess) {
            getString(R.string.higher) -> {
                if (currentThrow > lastThrow) onAnswerCorrect();
                else onAnswerIncorrect()
            }
            getString(R.string.lower) -> {
                if (currentThrow < lastThrow) onAnswerCorrect();
                else onAnswerIncorrect()
            }
            getString(R.string.equals) -> {
                if (currentThrow == lastThrow) onAnswerCorrect();
                else onAnswerIncorrect()
            }
        }
    }

}