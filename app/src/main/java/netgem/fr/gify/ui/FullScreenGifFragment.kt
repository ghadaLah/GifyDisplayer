package netgem.fr.gify.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.full_screen_fragment.*
import netgem.fr.gify.R
import netgem.fr.gify.utils.FULL_SCREEN_URL

class FullScreenGifFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.full_screen_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val url = arguments?.getString(FULL_SCREEN_URL)
        Glide.with(this)
            .load(url)
            .error(R.drawable.ic_placeholder)
            .into(fullGif)
    }
}