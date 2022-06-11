package com.meeweel.kanban_board.ui.screens.mainfragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.FragmentMainScreenBinding
import com.meeweel.kanban_board.databinding.NewBoardAddBinding
import com.meeweel.kanban_board.databinding.NewBoardCreateBinding
import com.meeweel.kanban_board.databinding.NewBoardDialogBinding
import com.meeweel.kanban_board.ui.MAIN

class MainScreenFragment : BaseMainScreenFragment() {

    private var _binding: FragmentMainScreenBinding? = null
    override val binding: FragmentMainScreenBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFabListener()
        onActionBarListener()
        workWhitItemMenuInToolbar()
    }

    private fun workWhitItemMenuInToolbar() {
        binding.leftTopAppBarMainScreenFragment.inflateMenu(R.menu.menu_main_screen_add)
        binding.leftTopAppBarMainScreenFragment.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_add -> {
                    alertDialogWithCustomStyle()
                    true
                }
                else -> false
            }
        }
    }

    private fun alertDialogWithCustomStyle() {
        val dialog = Dialog(requireContext())
        val dialogBind = NewBoardDialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBind.root)
        dialogBind.add.setOnClickListener {
            addDialog()
            dialog.dismiss()
        }
        dialogBind.create.setOnClickListener {
            createDialog()
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun createDialog() {
        val dialog = Dialog(requireContext())
        val dialogBind = NewBoardCreateBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBind.root)
        dialogBind.newTitleLayout.setEndIconOnClickListener {
            viewModel.createBoard(dialogBind.newTitle.text.toString())
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun addDialog() {
        val dialog = Dialog(requireContext())
        val dialogBind = NewBoardAddBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBind.root)
        dialogBind.addBoardLayout.setEndIconOnClickListener {
            viewModel.addBoardById(dialogBind.addBoardKey.text.toString())
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun onActionBarListener() {
        binding.leftTopAppBarMainScreenFragment.setNavigationOnClickListener {
            MAIN.navController.navigate(R.id.action_mainScreenFragment_to_copyOfCreateAccountFragment)
        }
    }

    private fun onFabListener() {
        binding.fabMainScreen.setOnClickListener {
            MAIN.navController.navigate(R.id.action_mainScreenFragment_to_taskScreenFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}