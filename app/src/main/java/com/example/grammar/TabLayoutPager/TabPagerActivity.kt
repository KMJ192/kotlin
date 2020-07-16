package com.example.grammar.TabLayoutPager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.grammar.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_tab_pager.*

class TabPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_pager)

        tab_layout.addTab(tab_layout.newTab().setText("ONE"))
        tab_layout.addTab(tab_layout.newTab().setText("TWO"))
        tab_layout.addTab(tab_layout.newTab().setText("THREE"))

        val pagerAdapter = FragmentPagerAdapter(supportFragmentManager, 3)
        view_pager.adapter = pagerAdapter

        //tab을 누르면 화면을 전환할 수 있도록 하는 addOnTabSelectedListener
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            //Tab이 다시 선택되었을 경우
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            //Tab이 선택되지 않았을 경우
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            //Tab이 선택되었을 경우
            override fun onTabSelected(tab: TabLayout.Tab?) {
                //Tab을 누르면 화면이 전환 됨
                view_pager.currentItem = tab!!.position //currentItem에는 null을 할당할 수 없음
            }
        })
        //화면을 넘길 시 tab의 select가 달라짐
        //pager가 이동했을 때 tab도 이동 시키는 Code
        view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
    }
}

open class FragmentPagerAdapter(
    fragmentManager: FragmentManager, //Pager는 fragment로 구성됨
    val tabCount : Int
) : FragmentStatePagerAdapter(fragmentManager){

    //item의 하나를 return
    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> {
                return Fragment1()
            }
            1 ->{
                return Fragment2()
            }
            2 ->{
                return Fragment3()
            }
            else -> return Fragment1()
        }
    }

    //item 전체를 return
    override fun getCount(): Int {
        return tabCount
    }

}