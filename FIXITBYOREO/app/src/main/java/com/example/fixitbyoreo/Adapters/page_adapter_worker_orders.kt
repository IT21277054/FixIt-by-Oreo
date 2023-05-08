package com.example.fixitbyoreo.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fixitbyoreo.Fragments.completed_worker_fragment
import com.example.fixitbyoreo.Fragments.on_going_worker_fragment
import com.example.fixitbyoreo.Fragments.requests_worker_fragment

class page_adapter_worker_orders(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    //when sliding it will switch to the correct fragment
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                requests_worker_fragment()
            }

            1 -> {
                on_going_worker_fragment()
            }

            2 -> {
                completed_worker_fragment()
            }
            else -> createFragment(position)
        }
    }

}