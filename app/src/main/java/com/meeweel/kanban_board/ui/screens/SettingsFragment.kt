package com.meeweel.kanban_board.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.data.room.LocalUserRepository
import com.meeweel.kanban_board.data.room.LocalUserRepositoryImpl
import com.meeweel.kanban_board.databinding.ActivityMainBinding
import com.meeweel.kanban_board.databinding.FragmentSettingsBinding
import com.meeweel.kanban_board.ui.MAIN

class SettingsFragment(private val localRepo: LocalUserRepository = LocalUserRepositoryImpl()) :
    Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onActionBarListener()
        listenerButtonLogOut()
        setThemeAny()
    }

    private fun setThemeAny() {
        with(binding) {
            buttonThemeKanbanBoard.setOnClickListener {
                requireActivity().setTheme(R.style.Theme_KanbanBoard)
            }
            buttonThemeAppCompatDialog.setOnClickListener {
                requireActivity().setTheme(R.style.Theme_AppCompat_Dialog)
            }
            buttonThemeDesignLight.setOnClickListener {
                requireActivity().setTheme(R.style.Theme_Design_Light)
            }
            buttonThemeMaterial3Light.setOnClickListener {
                requireActivity().setTheme(R.style.Theme_Material3_Light)
            }
        }
    }

    private fun listenerButtonLogOut() {
        binding.buttonLogOut.setOnClickListener {
            localRepo.logOut()
            MAIN.navController.navigate(R.id.action_copyOfCreateAccountFragment_to_authorizationScreen)
        }
    }

    private fun onActionBarListener() {
        binding.leftTopAppBarCopyOfCreateAccount.setNavigationOnClickListener {
            MAIN.navController.navigate(R.id.action_copyOfCreateAccountFragment_to_mainScreenFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
