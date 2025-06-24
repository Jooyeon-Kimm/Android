package com.part1.chapter5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.part1.chapter5.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val firstNumberText = StringBuilder("")
    private val secondNumberText = StringBuilder("")
    private val operatorText = StringBuilder("")
    private val decimalFormat = DecimalFormat("#,###") // #.## 소수점까지

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    // xml 에서 접근할 수 있어야 하므로, public 으로 지정
    // 매개변수를 View 로 받으므로, 반드시 매개변수 쓰기
    fun numberClicked(view : View) {
        // Log.d("numberClicked", "one")
        // button 에서 누른 숫자 추출
        // 모든 view 가 text 라는 attribute 를 가지고 있지 않다.
        // view 가 버튼이라면, text를 string 으로 변환
        // view 가 Null 이 아니면, text 값을 받아 string 형으로 변환
        // 버튼이 아니면 빈문자열 ""
        val numberString = (view as? Button)?.text?.toString() ?: ""

        // 첫 번째 숫자일지, 두 번째 숫자일지 확인
        // 첫 번째 숫자와 연산자가 없으면, 첫 번째 숫자
        val numberText = if(operatorText.isEmpty()) firstNumberText else secondNumberText

        numberText.append(numberString)

        // ui 업데이트
        updateEquationTextView()
    }

    fun clearClicked(view : View) {
        firstNumberText.clear()
        secondNumberText.clear()
        operatorText.clear()
        updateEquationTextView() // ui 업데이트
        binding.resultTextView.text =""
    }

    fun equalClicked(view : View) {
        if(firstNumberText.isEmpty() || secondNumberText.isEmpty() || operatorText.isEmpty()) {
            Toast.makeText(this, "올바르지 않은 수식 입니다.", Toast.LENGTH_SHORT).show()
            return
        }
        val firstNumber = firstNumberText.toString().toBigDecimal()
        val secondNumber = secondNumberText.toString().toBigDecimal()

        val result = when(operatorText.toString()){
            "+" -> decimalFormat.format(firstNumber + secondNumber)
            "-" -> decimalFormat.format(firstNumber - secondNumber)
            else -> "잘못된 수식 입니다."
        }
        binding.resultTextView.text = result

        updateEquationTextView()
    }

    fun operatorClicked(view : View) {
        val operatorString = (view as? Button)?.text?.toString() ?: ""

        // 첫 번째 숫자를 입력받지 못하면,
        if(firstNumberText.isEmpty()) {
            Toast.makeText(this, "먼저 숫자를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        // 두 번째 숫자까지 입력받았으면,
        if(secondNumberText.isNotEmpty()){
            Toast.makeText(this, "1개의 연산자에 대해서만 연산이 가능합니다.", Toast.LENGTH_SHORT).show()
            return
        }

        operatorText.append(operatorString)
        updateEquationTextView() // ui 업데이트
    }

    // ui 업데이트 함수
    private fun updateEquationTextView() {
        val firstFormattedNumber = if(firstNumberText.isNotEmpty()) decimalFormat.format(firstNumberText.toString().toBigDecimal()) else ""
        val secondFormattedNumber = if(secondNumberText.isNotEmpty()) decimalFormat.format(secondNumberText.toString().toBigDecimal()) else ""


        binding.equationTextView.text = "$firstFormattedNumber $operatorText $secondFormattedNumber"
    }

}