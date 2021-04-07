package netgem.fr.gify.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.search_list_fragment.*
import netgem.fr.gify.GifyDisplayerApp
import netgem.fr.gify.R
import netgem.fr.gify.ui.FullScreenGifFragment
import netgem.fr.gify.utils.FULL_SCREEN_URL
import netgem.fr.gify.utils.SEARCH_KEY
import netgem.fr.gify.utils.replaceFragment
import javax.inject.Inject

class SearchGifListFragment: Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: SearchGifListViewModel
    lateinit var adapter: GifAdapter
    private var searchKey: String? = null

    private val appComponent by lazy { GifyDisplayerApp.appComponent }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        appComponent.inject(this)
        searchKey = arguments?.getString(SEARCH_KEY)
        return inflater.inflate(R.layout.search_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchGifListViewModel::class.java)
        setupAdapter()
        searchKey?.let {searchKey ->
            viewModel.getGifList(searchKey)
        }

        setObservableListeners()
    }

    fun setupAdapter() {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        gifList.layoutManager = layoutManager
        adapter = GifAdapter(this) {redirectToFullScreen(it)}
        gifList.adapter = adapter
    }

    fun setObservableListeners() {
        viewModel.gifs.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        viewModel.state.observe(viewLifecycleOwner, Observer {
            when(it) {
                StateViewModel.Loading -> progressBar.visibility = View.VISIBLE
                StateViewModel.Success ->  progressBar.visibility = View.GONE
                StateViewModel.Error -> {
                    progressBar.visibility = View.GONE
                    val snackbar =
                        Snackbar.make(this.view!!, "an error has occured", Snackbar.LENGTH_INDEFINITE)
                    snackbar.setAction("retry") {
                        searchKey?.let {
                            viewModel.getGifList(it)
                        }
                    }
                    snackbar.show()
                }
            }
        })
    }

    private fun redirectToFullScreen(url: String) {
        val fullScreenGifFragment = FullScreenGifFragment()
        val bundle = Bundle()
        bundle.putString(FULL_SCREEN_URL, url)
        fullScreenGifFragment.arguments = bundle
        fragmentManager?.replaceFragment(fullScreenGifFragment)
    }
}