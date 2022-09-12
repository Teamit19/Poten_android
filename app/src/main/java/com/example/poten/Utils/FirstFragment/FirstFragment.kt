package com.example.poten.Utils.FirstFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.poten.databinding.FragmentFirstBinding
import com.google.android.material.tabs.TabLayoutMediator

class FirstFragment  : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentFirstBinding.inflate(
            inflater,
            container,
            false
        ).apply {

        }.root
    }
}