package com.meeweel.kanban_board.ui.screens.mainfragment

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.NewBoardAddBinding
import com.meeweel.kanban_board.databinding.NewBoardCreateBinding
import com.meeweel.kanban_board.databinding.NewBoardDialogBinding
import com.meeweel.kanban_board.ui.MAIN

class MainScreenFragment : BaseMainScreenFragment() {

    private var isClicked = false
    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.from_bottom_anim) }
    private val tpBottom: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.to_bottom_anim) }

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
            moveToSettings()
        }
    }

    private fun moveToSettings() {
        MAIN.navController.navigate(R.id.action_mainScreenFragment_to_copyOfCreateAccountFragment)
    }

    private fun onFabListener() {
        binding.fabMainScreen.setOnClickListener {
            mainFabClicked()
        }
        binding.fabAdd.setOnClickListener {
            alertDialogWithCustomStyle()
        }
        binding.fabHome.setOnClickListener {
            moveToSettings()
        }
    }

    private fun mainFabClicked() {
        setVisibility(isClicked)
        setAnimation(isClicked)
        setClickable(isClicked)
        isClicked = !isClicked
    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked) {
            binding.fabHome.visibility = View.VISIBLE
            binding.fabAdd.visibility = View.VISIBLE
        } else {
            binding.fabHome.visibility = View.GONE
            binding.fabAdd.visibility = View.GONE
        }
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked) {
            binding.fabHome.startAnimation(fromBottom)
            binding.fabAdd.startAnimation(fromBottom)
            binding.fabMainScreen.startAnimation(rotateOpen)
        } else {
            binding.fabHome.startAnimation(tpBottom)
            binding.fabAdd.startAnimation(tpBottom)
            binding.fabMainScreen.startAnimation(rotateClose)
        }
    }

    private fun setClickable(clicked: Boolean) {
            binding.fabHome.isClickable = !clicked
            binding.fabAdd.isClickable = !clicked
    }
}