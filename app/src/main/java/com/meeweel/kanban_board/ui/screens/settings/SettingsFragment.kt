package com.meeweel.kanban_board.ui.screens.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.FragmentSettingsBinding
import com.meeweel.kanban_board.domain.basemodels.states.SettingsState
import com.meeweel.kanban_board.ui.MAIN
import com.meeweel.kanban_board.ui.MainActivity
import com.meeweel.kanban_board.util.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding get() = _binding!!
    private val viewModel: SettingsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        onActionBarListener()
        listenerButtonLogOut()
        setThemeAny()
    }

    private fun initObserver() {
        val observer = Observer<SettingsState> { renderData(it) }
        viewModel.getData().observe(viewLifecycleOwner, observer)
        viewModel.getUserName()
    }

    private fun renderData(state: SettingsState) = when (state) {
        is SettingsState.UserName -> {
            binding.userName.text = state.userName
            binding.loadingLayout.visibility = View.GONE
        }
        is SettingsState.LoggedOut ->
            MAIN.navController.navigate(R.id.action_copyOfCreateAccountFragment_to_authorizationScreen)
        is SettingsState.AccountDeleted ->
            MAIN.navController.navigate(R.id.action_copyOfCreateAccountFragment_to_authorizationScreen)
        is SettingsState.Loading -> {
            binding.loadingLayout.visibility = View.VISIBLE
        }
        is SettingsState.Error -> { state.error.message?.toast(requireContext()) }
    }

    private fun setThemeAny() {
        (requireActivity() as? MainActivity)?.let { activity ->
            with(binding) {
                buttonThemeKanbanBoard.setOnClickListener {
                    activity.changeTheme(R.style.Theme_KanbanBoard)
                }
                buttonThemeAppCompatDialog.setOnClickListener {
                    activity.changeTheme(R.style.Theme_AppCompat_Dialog)
                }
                buttonThemeDesignLight.setOnClickListener {
                    activity.changeTheme(R.style.Theme_Design_Light)
                }
                buttonThemeMaterial3Light.setOnClickListener {
                    activity.changeTheme(R.style.Theme_Material3_Light)
                }
            }
        }
    }

    private fun listenerButtonLogOut() {
        binding.buttonLogOut.setOnClickListener {
            viewModel.logOut()
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
