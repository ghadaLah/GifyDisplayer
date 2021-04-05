package netgem.fr.gify.ui.random

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.random_fragment.*
import netgem.fr.gify.GifyDisplayerApp
import netgem.fr.gify.R
import netgem.fr.gify.di.AppComponent
import netgem.fr.gify.ui.search.SearchGifListFragment
import netgem.fr.gify.utils.SEARCH_KEY
import netgem.fr.gify.utils.replaceFragment
import javax.inject.Inject


class RandomGifFragment: Fragment() {

    private val appComponent: AppComponent by lazy { GifyDisplayerApp.appComponent }
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: RandomGifViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RandomGifViewModel::class.java)
        return inflater.inflate(R.layout.random_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getGifObject()
        searchButton.setOnClickListener {
            val searchKey = searchField.editText?.text
            if(!searchKey.isNullOrEmpty())  {
                val searchFragment = SearchGifListFragment()
                val bundle = Bundle()
                bundle.putString(SEARCH_KEY, searchKey.toString())
                searchFragment.arguments = bundle
                fragmentManager?.replaceFragment(searchFragment)
            }
        }
        observableListener()
    }

    fun observableListener() {
        viewModel.gifObject.observe(viewLifecycleOwner, Observer {
            showGif(it.url)
            gifTitle.text = it.title
        })
        viewModel.gifError.observe(viewLifecycleOwner, Observer {
            val snackbar =
                Snackbar.make(view!!, it, Snackbar.LENGTH_INDEFINITE)
            snackbar.setAction("retry") {
                viewModel.getGifObject()
            }
            snackbar.show()
        })
    }

    fun showGif(url: String) {
        context?.let { context ->
            Glide.with(context)
                .load(url)
                .centerCrop()
                .dontAnimate()
                .error(R.drawable.ic_placeholder)
                .into(gifImageView)
        }
    }
}