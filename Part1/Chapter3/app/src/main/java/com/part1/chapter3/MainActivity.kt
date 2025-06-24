package com.part1.chapter3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.part1.chapter3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 바인딩 변수 선언
    private lateinit var binding : ActivityMainBinding
    var inputNumber : Int = 0  // 클래스 멤버 변수
    var cmToM = true  // 클래스 멤버 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding 변수에 instance 할당
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 뷰 참조 변수들 선언
        val outputTextView = binding.outputTextView
        val outputUnitTextView = binding.outputUnitTextView
        val inputEditText = binding.inputEditText
        val inputUnitTextView = binding.inputUnitTextView
        val swapImageButton = binding.swapImageButton

        // 문자열이 변경되면 알려주는 리스너
        inputEditText.addTextChangedListener { text ->
            inputNumber = if(text.isNullOrEmpty()) {
                0
            } else {
                text.toString().toInt()
            }

            // cm -> m
            if(cmToM) {
                outputTextView.text = inputNumber.times(0.01).toString()
            }else{
                outputTextView.text = inputNumber.times(100).toString()
            }
        }

        // 단위 변환 버튼 클릭 시 처리
        swapImageButton.setOnClickListener {
            cmToM = cmToM.not() // !cmToM

            if (cmToM) {
                inputUnitTextView.text = "cm"
                outputUnitTextView.text = "m"
                outputTextView.text = inputNumber.times(0.01).toString()
            } else {
                inputUnitTextView.text = "m"
                outputUnitTextView.text = "cm"
                outputTextView.text = inputNumber.times(100).toString()
            }
        }
    }

    // UI 상태 저장
    override fun onSaveInstanceState(outState: Bundle) {
        // outState 에 값 저장
        outState.putBoolean("cmToM", cmToM)
        super.onSaveInstanceState(outState)
    }

    // UI 상태 복원
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        cmToM = savedInstanceState.getBoolean("cmToM")

        // m/cm 글자 변경
        binding.inputUnitTextView.text = if(cmToM) "cm" else "m"
        binding.outputUnitTextView.text = if(cmToM) "m" else "cm"

        super.onRestoreInstanceState(savedInstanceState)
    }
}
