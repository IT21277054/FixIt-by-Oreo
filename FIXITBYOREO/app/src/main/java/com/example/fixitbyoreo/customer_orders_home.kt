package com.example.fixitbyoreo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.fixitbyoreo.Adapters.page_adapter_customer_orders
import com.example.fixitbyoreo.Adapters.page_adapter_worker_orders
import com.google.android.material.tabs.TabLayout

class customer_orders_home : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter : page_adapter_customer_orders

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_orders_home)

        tabLayout = findViewById(R.id.tabLayoutCustomer)
        viewPager2 = findViewById(R.id.viewPagerCustomer)

        adapter = page_adapter_customer_orders(supportFragmentManager,lifecycle)

        tabLayout.addTab(tabLayout.newTab().setText("Pending"))
        tabLayout.addTab(tabLayout.newTab().setText("On Going"))
        tabLayout.addTab(tabLayout.newTab().setText("Completed"))

        viewPager2.adapter = adapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager2.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }
}