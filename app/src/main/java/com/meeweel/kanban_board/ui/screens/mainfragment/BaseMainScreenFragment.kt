package com.meeweel.kanban_board.ui.screens.mainfragment

import android.annotation.TargetApi
import android.app.Dialog
import android.content.ClipData
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.ClipboardManager
import android.text.SpannableStringBuilder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.*
import com.meeweel.core.basemodels.BoardModel
import com.meeweel.core.basemodels.Status
import com.meeweel.core.basemodels.states.BoardsAppState
import com.meeweel.kanban_board.util.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class BaseMainScreenFragment : Fragment() {

    private var taskPopupListener: PopupMenu.OnMenuItemClickListener? = null
    private var _binding: FragmentMainScreenBinding? = null
    internal open val binding: FragmentMainScreenBinding get() = _binding!!
    internal val viewModel: MainFragmentViewModel by viewModel()
    private val adapter = MainScreenFragmentRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        workLivedata()
        burgerClick()
        binding.refreshLayout.setOnRefreshListener {
            viewModel.getBoards()
        }
    }

    private fun burgerClick() {
        adapter.setBurgerClickListener(object : OnBurgerClickListener {
            override fun onBurgerClick(view: View, board: BoardModel) {
                setBoardPopupListener(board)
                val popupMenu = PopupMenu(
                    requireContext(),
                    view,
                    Gravity.CENTER
                )
                popupMenu.inflate(R.menu.popup_menu_board)
                popupMenu.setForceShowIcon(true)
                popupMenu.setOnMenuItemClickListener(taskPopupListener)
                popupMenu.show()
            }
        })
    }

    private fun setBoardPopupListener(board: BoardModel) {
        taskPopupListener = PopupMenu.OnMenuItemClickListener {
            when (it.itemId) {
                R.id.edit -> showEditBottomSheet(board)
                R.id.delete -> viewModel.removeBoard(board.id)
                R.id.details -> showBottomSheet(board)
                R.id.get_key -> viewModel.createBoardKey(board.id)
            }
            true
        }
    }

    private fun workLivedata() {
        binding.mainScreenFragmentRecyclerView.adapter = adapter
        val keyObserver = Observer<String> { a -> renderKeyData(a) }
        val dataObserver = Observer<BoardsAppState> { a -> renderData(a) }
        viewModel.getData().observe(viewLifecycleOwner, dataObserver)
        viewModel.getKeyData().observe(viewLifecycleOwner, keyObserver)
        viewModel.getBoards()
    }

    private fun renderKeyData(key: String) {
        if (key == ERROR || key == NOT_HOST) key.toast(requireContext())
        else showKey(key)
    }

    private fun showKey(key: String) {
        val dialog = Dialog(requireContext())
        val dialogBind = ShowKeyDialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBind.root)
        dialogBind.key.text = key
        dialogBind.copy.setOnClickListener {
            copyText(key)
        }
        dialog.show()
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private fun copyText(copiedText: String) {
        val sdk = Build.VERSION.SDK_INT
        if (sdk < Build.VERSION_CODES.HONEYCOMB) {
            val clipboard =
                requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.text = copiedText
            COPIED.toast(requireContext())
        } else {
            val clipboard =
                requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
            val clip = ClipData.newPlainText("TAG", copiedText)
            clipboard.setPrimaryClip(clip)
            COPIED.toast(requireContext())
        }
    }

    private fun renderData(data: BoardsAppState) = when (data) {
        is BoardsAppState.Success -> {
            binding.refreshLayout.isRefreshing = false
            val dataList = data.data
            binding.loadingLayoutMainScreen.visibility = View.GONE
            adapter.setData(dataList)
        }
        is BoardsAppState.Loading -> binding.loadingLayoutMainScreen.visibility = View.VISIBLE
        is BoardsAppState.Error -> {
            binding.refreshLayout.isRefreshing = false
            binding.loadingLayoutMainScreen.visibility = View.GONE
        }
    }

    private fun showBottomSheet(board: BoardModel) {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val bottomSheetBinding = BottomSheetBoardBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(bottomSheetBinding.root)
        var toDoCounter = 0
        var inProgressCounter = 0
        var doneCounter = 0
        for (item in board.toDoList) {
            when (item.status) {
                Status.TO_DO -> toDoCounter++
                Status.IN_PROGRESS -> inProgressCounter++
                else -> doneCounter++
            }
        }
        bottomSheetBinding.apply {
            boardTitle.text = board.name
            boardHost.text = board.host
            boardTodoCounter.text = toDoCounter.toString()
            boardInProgressCounter.text = inProgressCounter.toString()
            boardDoneCounter.text = doneCounter.toString()
        }
        bottomSheetDialog.show()
    }

    private fun showEditBottomSheet(board: BoardModel) {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val bottomSheetBinding = BottomSheetEditBoardBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(bottomSheetBinding.root)
        bottomSheetBinding.apply {
            boardTitle.text = SpannableStringBuilder(board.name)
            boardBtn.setOnClickListener {
                viewModel.updateBoard(board.also { board -> board.name = boardTitle.text.toString() })
                bottomSheetDialog.dismiss()
            }
        }
        bottomSheetDialog.show()
    }

    interface OnBurgerClickListener {
        fun onBurgerClick(view: View, board: BoardModel)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        adapter.removeListeners()
    }

    companion object {
        const val ERROR = "Error"
        const val NOT_HOST = "You are not host"
        const val COPIED = "Copied to clipboard"
    }
}