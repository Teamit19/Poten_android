package com.example.poten.Notice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.poten.R
import com.example.poten.databinding.ActivityLoginBinding
import com.example.poten.databinding.ActivityNoticeBinding

class NoticeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoticeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)
        binding= ActivityNoticeBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}