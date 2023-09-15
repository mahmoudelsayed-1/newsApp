package com.example.newsapp.ui.news

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.api.model.ApiConstant
import com.example.newsapp.api.model.ApiManager
import com.example.newsapp.api.model.sourcesResponse.Source
import com.example.newsapp.api.SourcesResponse
import com.example.newsapp.api.model.newsResponse.NewsResponse
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.ui.showDialog
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {

    lateinit var viewBinding : FragmentNewsBinding
    lateinit var viewModel:NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // declare view model
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)


    }

    var adapter = NewsAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentNewsBinding.inflate(inflater,container,false)
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSources()
        subscribeViewModel()
        viewBinding.recycler.adapter = adapter
    }

    private fun subscribeViewModel() {
        viewModel.messageDialogLiveData.observe(viewLifecycleOwner, Observer {message->
            showDialog(message = message,
                posActionName = "ok",
                posAction = { dialog: DialogInterface?, which: Int ->
                    dialog?.dismiss()
                })

        })

        viewModel.showLoading.observe(viewLifecycleOwner, Observer {show->
            if(show)
              viewBinding.progressBar.isVisible = true
            else
                viewBinding.progressBar.isVisible = false
        })

        viewModel.sourceLiveData.observe(viewLifecycleOwner, Observer {sourceList->

            showTabs(sourceList)
        })

        viewModel.newsLiveData.observe(viewLifecycleOwner, Observer {newsList->

            adapter.showNews(newsList)  // بقولوا لما ليست النيوز تجيلك حطها ف ال adapter

        })


    }


    private fun showTabs(sources: List<Source?>?) {

        if(sources==null) return

        sources.forEach{ source->
            val createTab = viewBinding.tabLayout.newTab()  // create tab
            createTab.text = source?.name  //put source name to each tab
            createTab.tag = source    // to get source id in tab selected
            viewBinding.tabLayout.addTab(createTab) //add new tab
        }
        viewBinding.tabLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as Source //to get source id
                   viewModel.getNews(source.id)
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as Source //to reselect tab
                    viewModel.getNews(source.id)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }
            }

        )
        viewBinding.tabLayout.getTabAt(0)?.select()

    }





}
