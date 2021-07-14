package com.example.ardianethread.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ardianethread.Activities.fragment_ariane
import com.example.ardianethread.Activities.fragment_theseus

class FragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when(position) {
            1->return(fragment_theseus.newInstance())

        }
        return fragment_ariane.newInstance()
    }
    }


