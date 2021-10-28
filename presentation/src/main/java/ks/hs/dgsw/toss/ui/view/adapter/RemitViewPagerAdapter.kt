package ks.hs.dgsw.toss.ui.view.adapter

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ks.hs.dgsw.toss.R
import ks.hs.dgsw.toss.ui.view.fragment.SelectSuggestFragment

class RemitViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SelectSuggestFragment.newInstance()
            1 -> NavHostFragment.create(R.navigation.navigation_send_money_with_account)
            else -> throw IndexOutOfBoundsException()
        }
    }
}