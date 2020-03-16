package com.app.twojapogoda.ui.search

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.twojapogoda.MainActivity
import com.app.twojapogoda.MainActivityViewModel

import com.app.twojapogoda.R
import com.app.twojapogoda.adapters.SearchAdapter
import kotlinx.android.synthetic.main.main_activity.*

@Suppress("DEPRECATION")
class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        mainActivityViewModel = ViewModelProviders.of(activity!!).get(MainActivityViewModel::class.java)

        mainActivityViewModel.getCitiesYouWant("")
        val adapter = SearchAdapter(::onRecViewItemClick)
        val recyclerView = view!!.findViewById<RecyclerView>(R.id.searchRecyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager=LinearLayoutManager(context)

        mainActivityViewModel.listOfCitiesYouWant.observe(viewLifecycleOwner, Observer {
            adapter.newList(ArrayList(it))
        })

    }

    private fun onRecViewItemClick(c: String){
        activity!!.toolbar.collapseActionView()
        mainActivityViewModel.setAndInsertCity(c)
        activity!!.viewPager.currentItem=1
    }



}
