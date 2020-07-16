package com.example.grammar.TabLayoutPager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.grammar.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_tab_pager.*

class TabPager2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_pager)
        with(tab_layout) {
            this.addTab(tab_layout.newTab().setText("ONE"))
            this.addTab(tab_layout.newTab().setText("TWO"))
            this.addTab(tab_layout.newTab().setText("THREE"))

            val adapter = ThreePageAdapter(LayoutInflater.from(this@TabPager2Activity))
            view_pager.adapter = adapter
            view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))

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
        }
    }
}

class ThreePageAdapter(
    val layoutInflater : LayoutInflater
) : PagerAdapter(){

    //View를 그려줌
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        when(position){
            0->{
                //position이 1이면 fragment_one.xml을 return
                val view = layoutInflater.inflate(R.layout.fragment_one, container, false)
                container.addView(view)
                return view //`object`
            }
            1->{
                //position이 1이면 fragment_one.xml을 return
                val view = layoutInflater.inflate(R.layout.fragment_two, container, false)
                container.addView(view)
                return view //`object`
            }
            2->{
                //position이 1이면 fragment_one.xml을 return
                val view = layoutInflater.inflate(R.layout.fragment_three, container, false)
                container.addView(view)
                return view //`object`
            }
            else->{
                //position이 1이면 fragment_one.xml을 return
                val view = layoutInflater.inflate(R.layout.fragment_one, container, false)
                container.addView(view)
                return view //`object`
            }
        }
    }
    //Pager의 View가 파기되어야 할 경우
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        //View로 casting해주어야 할 것
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        //instantiateItem의 view와 `object` 비교
        //현재 보여지는 화면과 return 시킨 화면이 같은지 비교
        return view === `object` as View // === 주소값 비교
    }

    //Pager의 Count
    override fun getCount(): Int {
        return 3
    }
}