package com.alierdemalkoc.news.view.fragment

import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.navigation.Navigation
import com.alierdemalkoc.news.adapter.NewsCustomAdapter
import com.alierdemalkoc.news.databinding.FragmentNewsBinding
import com.alierdemalkoc.news.model.New
import com.alierdemalkoc.news.service.NewsService

class NewsFragment : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    lateinit var listView: ListView
    val newsService = NewsService()
    lateinit var list: List<New>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        listView = binding.newsListView
        list = newsService.news()
        val customAdapter = NewsCustomAdapter(requireActivity(), list)
        listView.adapter = customAdapter
        Log.d("list", list.toString())
        customAdapter.notifyDataSetChanged()



        listView.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val item = newsService.news()[i]
            val action = NewsFragmentDirections.newsToDetail(item.href)
            Navigation.findNavController(view).navigate(action)
        })
    }
}