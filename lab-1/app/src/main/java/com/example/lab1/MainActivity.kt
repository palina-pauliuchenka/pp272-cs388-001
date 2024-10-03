package com.example.lab1

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams

class MainActivity : AppCompatActivity() {

    var counter = 0
    var goalCounter = 0
    private val goals = listOf(5, 10, 20, 30, 40)
    private lateinit var goalTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val upgradeButton = findViewById<Button>(R.id.upgradeBtn)

        val textView = findViewById<TextView>(R.id.textView)
        goalTextView = findViewById(R.id.goalTextView)

        button.setOnClickListener {
            Toast.makeText(it.context, "Clicked Button!", Toast.LENGTH_SHORT).show()
            counter++
            textView.text = counter.toString()
        }

        button.setOnClickListener {
            // Toast.makeText(it.context, "Clicked Button!", Toast.LENGTH_SHORT).show()
            counter++
            textView.text = counter.toString()

            checkGoals(counter)

            if (counter >= 10) {

                // Show upgrade button and set onClickListener
                upgradeButton.visibility = View.VISIBLE
                upgradeButton.setOnClickListener {

                    // Update original button to add 2 instead of `
                    button.setOnClickListener {
                        counter += 2
                        textView.text = counter.toString()
                        checkGoals(counter)
                    }

                    // Hide upgrade button again
                    upgradeButton.visibility = View.INVISIBLE

                    // Change button background to two_paws
                    button.setBackgroundResource(R.drawable.two_paws)

                    // Update button size
                    val newSizeDp = 75
                    val density = resources.displayMetrics.density
                    val newSizePx = (newSizeDp * density).toInt() // convert dp to px

                    val layoutParams = button.layoutParams as LayoutParams
                    layoutParams.width = newSizePx
                    layoutParams.height = newSizePx
                    button.layoutParams = layoutParams
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun checkGoals(currentCount: Int) {
        if (goals.contains(currentCount) && currentCount > goalCounter) {
            goalCounter = currentCount
            Toast.makeText(this, "Goal Reached: $goalCounter taps!", Toast.LENGTH_SHORT).show()
            updateGoalTextView()
        }
    }

    private fun updateGoalTextView() {
        val reachedGoals = goals.filter { it <= goalCounter }
        val goalText = reachedGoals.joinToString("\n") { "$it x🦴" }
        goalTextView.text = "Goal counter:\n$goalText"

        if (goalCounter >= goals.last()) {
            Toast.makeText(this, "All goals completed!", Toast.LENGTH_SHORT).show()
        }
    }
}