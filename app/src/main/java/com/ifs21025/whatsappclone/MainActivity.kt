package com.ifs21025.whatsappclone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.ifs21025.whatsappclone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
    }

    private fun setupView() {

        binding.toolbar.overflowIcon =
            ContextCompat.getDrawable(this, R.drawable.ic_more_vert)

        loadFragment(FLAG_FRAGMENT_CHATS)
    }

    private fun setupAction() {
        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_chats -> {
                    loadFragment(FLAG_FRAGMENT_CHATS)
                    true
                }

                else -> true
            }
        }
    }

    private fun loadFragment(flag: String, message: String? = null) {
        val fragmentManager = supportFragmentManager
        val fragmentContainerId =
            binding.inContentMain.frameContainer.id

        when (flag) {
            FLAG_FRAGMENT_CHATS -> {
                val chatsFragment = ChatsFragment()

                fragmentManager
                    .beginTransaction()
                    .replace(
                        fragmentContainerId,
                        chatsFragment,
                        ChatsFragment::class.java.simpleName
                    )
                    .commit()
            }
        }
    }

    companion object {
        const val FLAG_FRAGMENT_CHATS = "fragment_chats"
    }
}
