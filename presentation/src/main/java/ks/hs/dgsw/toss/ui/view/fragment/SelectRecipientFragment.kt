package ks.hs.dgsw.toss.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.databinding.FragmentSelectRecipientBinding
import ks.hs.dgsw.toss.ui.view.activity.RemitActivity
import ks.hs.dgsw.toss.ui.view.adapter.RemitViewPagerAdapter

/**
 * 송금 대상을 선택하는 프래그먼트입니다.
 * 최근에 송금한 사람의 계좌 리스트(추천),
 * 또는 계좌, 토스 계정 이라는 탭이 존재합니다.
 */

@AndroidEntryPoint
class SelectRecipientFragment : Fragment() {

    private lateinit var binding: FragmentSelectRecipientBinding
    private lateinit var toolbar: Toolbar
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSelectRecipientBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        if (requireActivity() is RemitActivity) {
            with(requireActivity() as RemitActivity) {
                setSupportActionBar(toolbar)
                supportActionBar?.title = ""
                supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
        } else {
            toolbar.visibility = View.GONE
        }

        val tabArray = resources.getStringArray(R.array.tab_array)
        val viewPagerAdapter = RemitViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabArray[position]
        }.attach()

    }

    private fun init() {
        toolbar = binding.toolbarSelect
        tabLayout = binding.tabLayoutSelect
        viewPager = binding.viewPagerSelect
    }

}