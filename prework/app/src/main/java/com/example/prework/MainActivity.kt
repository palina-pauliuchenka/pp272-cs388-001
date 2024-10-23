package com.example.prework

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import android.graphics.Color
import android.view.MotionEvent
import android.widget.ImageView
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.prework.ui.theme.PreworkTheme

class MainActivity : ComponentActivity() {

    private val imgList = listOf(R.drawable.onix, R.drawable.onix2, R.drawable.onix3, R.drawable.onix4, R.drawable.onix5)
    private var imgIdx = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        val helloButton = findViewById<Button>(R.id.helloButton)
        helloButton.setOnClickListener {
            Log.v("hi", "button clicked")
            Toast.makeText(this, "Woof woof 🐶", Toast.LENGTH_SHORT).show()
        }

        val imgV: ImageView = findViewById(R.id.imageOfOnix)
        imgV.setOnClickListener {
            imgIdx++
            if (imgIdx >= imgList.count())
                imgIdx = 0
            imgV.setImageResource(imgList[imgIdx])
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PreworkTheme {
        Greeting("Android")
    }
}