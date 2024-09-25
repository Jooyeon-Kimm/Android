package com.part1.chapter2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI 요소 가져오기
        val numberTextView = findViewById<TextView>(R.id.numberTextView)
        val resetButton = findViewById<Button>(R.id.resetButton)
        val plusButton = findViewById<Button>(R.id.plusButton)

        var number = 0


        // 동작에 대한 연산 처리
        // 클릭에 대한 처리
        resetButton.setOnClickListener{
            number = 0
            numberTextView.text = number.toString() // 숫자를 문자열로
            // 리셋 버튼의 클릭에 대한 리스닝을 하고 있는지 확인하기 위해 Log 찍기
            Log.d("onClick", "리셋된 숫자는 $number") // 디버깅용이므로 Log.d
        }

        plusButton.setOnClickListener{
            // + 버튼의 클릭에 대한 리스닝을 하고 있는지 확인하기 위해 Log 찍기
            number++
            numberTextView.text = number.toString() // 숫자를 문자열로
            Log.d("onClick", "+된 숫자는 $number") // 만약 에러용이면 Log.e
        }
    }


}