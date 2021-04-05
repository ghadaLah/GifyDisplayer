package netgem.fr.gify.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.view.*
import netgem.fr.gify.R

fun FragmentManager.replaceFragment(fragment: Fragment) =
    this.beginTransaction()
        .replace(R.id.frameContainer, fragment)
        .addToBackStack(fragment.tag)
        .commit()