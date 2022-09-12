package com.example.poten.Utils.SecondFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.poten.databinding.FragmentSecondBinding

class SecondFragment  : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSecondBinding.inflate(
            inflater,
            container,
            false
        ).apply {

        }.root
    }
}