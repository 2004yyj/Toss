package ks.hs.dgsw.toss.ui.view.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ks.hs.dgsw.toss.ui.view.fragment.SelectAccountFragment
import ks.hs.dgsw.toss.ui.view.fragment.SelectSuggestFragment

class RemitViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SelectSuggestFragment.newInstance()
            1 -> SelectAccountFragment.newInstance()
            2 -> SelectSuggestFragment.newInstance()
            else -> throw IndexOutOfBoundsException()
        }
    }
}