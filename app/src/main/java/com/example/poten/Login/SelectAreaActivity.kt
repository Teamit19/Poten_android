package com.example.poten.Login

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.poten.R
import com.example.poten.databinding.ActivityLoginAreaBinding

class SelectAreaActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginAreaBinding
    var datas = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginAreaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializelist()
        initRecycler()

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, SelectKeywordActivity::class.java)
            startActivity(intent)
        }

    }

    private fun initRecycler() {
        var areaAdapter = AreaAdapter(this)
        areaAdapter.datas=datas
        areaAdapter.setItemClickListener(object : AreaAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
            }

        })
        binding.recyclerView.addItemDecoration(RecyclerViewDecoration(3))
        binding.recyclerView.adapter = areaAdapter
        binding.recyclerView.layoutManager= GridLayoutManager(applicationContext, 3)


    }

    fun initializelist() {
        with(datas) {
            add("서울특별시")
            add("부산광역시")
            add("인천광역시")
            add("대구광역시")
            add("광주광역시")
            add("대전광역시")
            add("울산광역시")
            add("세종시")
            add("경기도")
            add("강원도")
            add("충청남도")
            add("충청북도")
            add("경상북도")
            add("경상남도")
            add("전라북도")
            add("전라남도")
            add("제주도")
        }
    }

}