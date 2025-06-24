package com.part1.chapter7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.part1.chapter7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), WordAdapter.ItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var wordAdapter: WordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        // AddActivity 연결
        binding.addButton.setOnClickListener {
            Intent(this, AddActivity::class.java).let {
                startActivity(it)
            }
        }

    }



    private fun initRecyclerView() {
        // 더미 데이터 생성
//        val dummyList = mutableListOf<Word>(
//            Word("weather", "날씨", "명사"),
//            Word("honey", "꿀", "명사"),
//            Word("run", "실행하다", "동사"),
//        )

        // adaptor 에 clicklistener 를 지정해주어야 한다.
        wordAdapter = WordAdapter(mutableListOf(), this)

        // scope function 사용 ( 설정할 것이 많기 때문 )
        binding.wordRecyclerView.apply {
            adapter = wordAdapter // 리사이클러 뷰에 어댑터 연결

            // binding 에서 scope function 을 사용했으므로
            // context 는 this 로 지정하면 안 된다.
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,false)

            val dividerItemDecoration = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
            addItemDecoration(dividerItemDecoration)

        }

    }

    override fun onClick(word: Word) {
        Toast.makeText(this, "${word.text}가 클릭 됐습니다", Toast.LENGTH_SHORT).show()
    }

}