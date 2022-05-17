package com.meeweel.kanban_board.ui.screens.todoscreen

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.FragmentToDoBinding
import com.meeweel.kanban_board.domain.basemodels.BoardModel
import com.meeweel.kanban_board.ui.MAIN


class ToDoFragment : BaseToDoScreenFragment(), View.OnTouchListener {
    private var _binding: FragmentToDoBinding? = null
    override val binding: FragmentToDoBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentToDoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toDo.setOnTouchListener(this)
        fabToDo()
    }

    fun popupMenu(view: View) {
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.inflate(R.menu.popup_menu)
        popupMenu.setOnMenuItemClickListener { item: MenuItem? ->
            when (item!!.itemId) {
                R.id.moveTo -> {

                }
                R.id.changePriority -> {

                }
                R.id.delete -> {

                }
            }
            true
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            popupMenu.setForceShowIcon(true)
        }
        popupMenu.show()
    }

    private fun fabToDo() {
        binding.fabToDo.setOnClickListener {
            Toast.makeText(context, "FabToDo", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        when (view) {
            binding.toDo -> {
                when (motionEvent.action) {
                    MotionEvent.ACTION_MOVE -> {
                        MAIN.navController.navigate(R.id.action_toDoFragment_to_boardScreenFragment)
                    }
                }
            }
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    companion object {
        var board: BoardModel? = null
    }
}