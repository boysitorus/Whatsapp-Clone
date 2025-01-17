package com.ifs21025.whatsappclone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChatsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChatsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var adapter: ChatAdapter
    private lateinit var recyclerView: RecyclerView
    private val userArrayList = ArrayList<User>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chats, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChatsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChatsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userArrayList.addAll(getAllData())

        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.chats_recycler_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = ChatAdapter(userArrayList)
        recyclerView.adapter = adapter
        
        val floatingButton = view.findViewById<FloatingActionButton>(R.id.floating_button)
        floatingButton.setOnClickListener{
            Toast.makeText(activity, "Floating Button Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getAllData() : ArrayList<User> {
        val dataUsername = resources.getStringArray(R.array.list_nama)
        val dataProfilePict = resources.obtainTypedArray(R.array.list_profile_picture)
        val dataLastChat = resources.getStringArray(R.array.list_last_chat)
        val dataLastTime = resources.getStringArray(R.array.list_last_time)

        val listUser = ArrayList<User>()

        for(i in dataUsername.indices) {
            val user = User(dataUsername[i], dataProfilePict.getResourceId(i, -1), dataLastChat[i], dataLastTime[i])

            listUser.add(user)
        }

        return listUser
    }

}