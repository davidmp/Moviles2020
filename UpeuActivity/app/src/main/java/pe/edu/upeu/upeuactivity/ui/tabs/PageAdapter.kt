package pe.edu.upeu.upeuactivity.ui.tabs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PageAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when(position){
            0->{return OneFragment()}
            1->{return ThowFragment()}
            2->{return TreeFragment()}
            else -> { return OneFragment()}
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0->{return  "Message"}
            1->{return  "Status"}
            2->{return  "Calls"}
        }
        return super.getPageTitle(position)
    }

    override fun getCount(): Int {
        return 3
    }



}