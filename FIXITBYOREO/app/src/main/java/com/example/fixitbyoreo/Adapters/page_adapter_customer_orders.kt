package com.example.fixitbyoreo.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fixitbyoreo.Fragments.*

class page_adapter_customer_orders(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                pending_customer_fragment()
            }

            1 -> {
                on_going_customer_fragment()
            }

            2 -> {
                completed_customer_fragment()
            }
            else -> createFragment(position)
        }
    }

}