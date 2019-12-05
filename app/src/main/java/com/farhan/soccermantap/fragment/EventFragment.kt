package com.farhan.soccermantap.fragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.viewpager2.adapter.FragmentStateAdapter

import com.farhan.soccermantap.R
import com.farhan.soccermantap.fragment.match.NextMatchFragment
import com.farhan.soccermantap.fragment.match.PreviousMatchFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_event.*
import org.greenrobot.eventbus.EventBus

/**
 * A simple [Fragment] subclass.
 */
class EventFragment : Fragment() {
    private var bundle = Bundle()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_event, container, false)
        //viewpager
        bundle.putString("key", "4328")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinnerItems = resources.getStringArray(R.array.league_name)
        val spinnerAdapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinnerLeague.adapter = spinnerAdapter


        spinnerLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                when(position){
                    0 -> {
                        bundle.putString("key", "4328")
                    }
                    1 -> {
                        bundle.putString("key", "4331")
                    }
                    2 -> {
                        bundle.putString("key", "4332")
                    }
                    3 -> {
                        bundle.putString("key", "4334")
                    }
                    4 -> {
                        bundle.putString("key", "4335")
                    }
                }
                viewPager()
            }
        }
    }


    private fun viewPager(){
        viewpagerHome.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return when(position) {
                    0 -> {
                        PreviousMatchFragment.newInstance(bundle)
                    }
                    else -> {
                        NextMatchFragment.newInstance(bundle)
                    }
                }
            }

            override fun getItemCount(): Int {
                return 2
            }
        }

        TabLayoutMediator(tabsHome, viewpagerHome) { tab, position ->
            tab.text = when(position) {
                0 -> "Previous Match"
                else -> "Next Match"
            }
        }.attach()
    }
}
