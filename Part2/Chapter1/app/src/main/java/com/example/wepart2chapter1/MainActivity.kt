package com.example.wepart2chapter1

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.wepart2chapter1.databinding.ActivityMainBinding
import com.example.wepart2chapter1.ui.theme.Wepart2chapter1Theme
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), OnTabLayoutNameChanged {

    private lateinit var binding : ActivityMainBinding

    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreference = getSharedPreferences(WebviewFragment.Companion.SHARED_PREFERENCE, Context.MODE_PRIVATE)
        val tab0 = sharedPreference?.getString("tab0_name", "월요 웹툰")
        val tab1 = sharedPreference?.getString("tab1_name", "화요 웹툰")
        val tab2 = sharedPreference?.getString("tab2_name", "수요 웹툰")
        binding.viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            run {
//                val textView = TextView(this)
//                textView.text = "position $position"
//                textView.gravity = Gravity.CENTER
//
//                tab.customView = textView
                tab.text = when(position) {
                    0 -> tab0
                    1 -> tab1
                    else -> tab2
                }
            }
        }.attach() // TabLayout과 ViewPager가 결합됨

//        binding.button1.setOnClickListener {
//            val transaction = supportFragmentManager.beginTransaction().apply {
//                replace(R.id.fragment_container, WebviewFragment())
//            }
//            transaction.commit()
//        }
//
//        binding.button2.setOnClickListener {
//            val transaction = supportFragmentManager.beginTransaction().apply {
//                replace(R.id.fragment_container, BFragment())
//            }
//            transaction.commit()
//        }
    }

    // {onBackPressed}
    override fun onBackPressed() {
//        val currentFragment = supportFragmentManager.fragments.first() // TODO 수정 필요함. fragment를 viewpager에서 가져와야 함
        val currentFragment = supportFragmentManager.fragments[binding.viewPager.currentItem]
        if(currentFragment is WebviewFragment){
            if(currentFragment.canGoBack()) {
                currentFragment.goBack()
            }else {
                super.onBackPressed() // 부모의 onBackPressed()를 호출
            }
        } else {
            super.onBackPressed()
        }
    }

    override fun nameChanged(position: Int, name: String) {
        val tab = binding.tabLayout.getTabAt(position)
        tab?.text = name
    }


}
