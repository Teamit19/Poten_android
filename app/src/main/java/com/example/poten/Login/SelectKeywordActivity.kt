package com.example.poten.Login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.poten.databinding.ActivityLoginAreaBinding
import com.example.poten.databinding.ActivityLoginKeywordBinding

class SelectKeywordActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginKeywordBinding
    var datas = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginKeywordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializelist()
        initRecycler()

    }

    private fun initRecycler() {
        var areaAdapter = AreaAdapter(this)
        areaAdapter.datas=datas
        binding.recyclerView.addItemDecoration(RecyclerViewDecoration(4))
        binding.recyclerView.adapter = areaAdapter
        binding.recyclerView.layoutManager= GridLayoutManager(applicationContext, 4)

    }

    fun initializelist() {
        with(datas) {
            add("IT")
            add("요리")
            add("크리에이터")
            add("연기")
            add("독서")
            add("스포츠")
            add("프로그래밍")
            add("디자인")
            add("발표")
            add("베이킹 수업")
            add("자연과학")
            add("봉사활동")
            add("연출")
            add("글쓰기")
            add("언어")
            add("댄스")
            add("사진")
            add("전시회")
            add("달리기")
            add("요가")
            add("기획")
            add("역사")
            add("스피치")
            add("영어회화")
            add("수영")
            add("클라이밍")
            add("힙합")
            add("보드게임")
            add("민속놀이")
            add("영화")
            add("수학")
            add("밴드")
            add("자유토론")
            add("악기")
            add("광고")



        }
    }

}