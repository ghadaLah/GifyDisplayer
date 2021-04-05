package netgem.fr.gify.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import netgem.fr.gify.R
import netgem.fr.gify.ui.random.RandomGifFragment
import netgem.fr.gify.utils.replaceFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.supportFragmentManager.replaceFragment(RandomGifFragment())
    }
}