package com.part1.chapter7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.chip.Chip
import com.part1.chapter7.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        binding.addButton.setOnClickListener {
            add()
        }
    }

    private fun initViews() {
        // 8품사
        val types = listOf<String>(
            "명사", "동사", "대명사", "형용사", "부사", "감탄사", "전치사", "접속사"
        )

        binding.typeChipGroup.apply {
            // type 의 모든 데이터를 chip 으로 변경
            types.forEach { text ->
                addView(createChip(text))
            }
        }
    }

    private fun createChip(text: String) : Chip {
        return Chip(this).apply {
            setText(text)

            // chip 을 그룹으로 설정한 것은
            // 그룹 내의 값 중 하나만 선택하기 위해서
            isCheckable = true
            isClickable = true
        }
    }

    // 추가 버튼 클릭 시
    private fun add() {
        val text = binding.textInputEditText.text.toString()
        val mean = binding.meaningTextInputEditText.text.toString()
        val type = findViewById<Chip>(binding.typeChipGroup.checkedChipId).text.toString()
        val word = Word(text, mean, type)

        Thread {
            AppDatabase.getInstance(this)?.wordDao()?.insert(word)
            runOnUiThread {
                Toast.makeText(this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show()
            }
            finish()
        }.start()

    }
}