package com.example.first_coroutine

import android.graphics.DiscretePathEffect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        GlobalScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {
                var answer1: String? = null
                var answer2: String? = null
                val job1 = launch { answer1 = doNetworkCall1() }
                val job2 = launch { answer2 = doNetworkCall2() }

                job1.join()
                job2.join()

                Log.d(TAG, "Answer1 is $answer1")
                Log.d(TAG, "Answer2 is $answer2")
            }

            Log.d(TAG, "Request took $time")

        }
    }

    private suspend fun doNetworkCall1(): String {
        delay(3000)
        return "Answer from doNetworkCall 1"
    }

    private suspend fun doNetworkCall2(): String {
        delay(3000)
        return "Answer from doNetworkCall 2"
    }
}


