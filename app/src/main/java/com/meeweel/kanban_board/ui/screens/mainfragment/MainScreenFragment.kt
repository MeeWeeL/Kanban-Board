package com.meeweel.kanban_board.ui.screens.mainfragment

import android.app.AlertDialog
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
        val builder =
            AlertDialog.Builder(ContextThemeWrapper(requireContext(), R.style.AlertDialogCustom))
        with(builder) {
            setTitle("Work with board")
            setNeutralButton(
                "Create new board",
                DialogInterface.OnClickListener(function = createNewBoard)
            )
            setNegativeButton(
                "Add board",
                DialogInterface.OnClickListener(function = addBoard)
            )
            show()
        }
    }

    private val createNewBoard = { _: DialogInterface, _: Int ->
        enterBoarName()
    }

    private fun enterBoarName() {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.alert_dialog_with_edittext_and_plus, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.enterBoardNameEditText)
        val button = dialogLayout.findViewById<AppCompatImageButton>(R.id.enterBoardNameButton)
        buttonHandlerEnterBoardNameButton(button, editText)
        builder.setView(dialogLayout)
        builder.show()
    }

    //обработчик нажатия на кнопку "+" в алерт диалоге enterBoarName
    private fun buttonHandlerEnterBoardNameButton(
        button: AppCompatImageButton,
        editText: EditText
    ) {
        button.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "You create a new board " + editText.text.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    private val addBoard = { _: DialogInterface, _: Int ->
        enterBoardKey()
    }

    private fun enterBoardKey() {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val dialogLayout =
            inflater.inflate(R.layout.alert_dialog_with_edittext_and_search_button, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.enterBoardKeyEditText)
        val button = dialogLayout.findViewById<AppCompatImageButton>(R.id.enterBoardKeyButton)
        buttonHandlerEnterBoardKeyButton(button, editText)
        builder.setView(dialogLayout)
        builder.show()
    }

    //обработчик нажатия на кнопку "+" в алерт диалоге enterBoardKeyButton
    private fun buttonHandlerEnterBoardKeyButton(
        button: AppCompatImageButton,
        editText: EditText
    ) {
        button.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "You enter a new key " + editText.text.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
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