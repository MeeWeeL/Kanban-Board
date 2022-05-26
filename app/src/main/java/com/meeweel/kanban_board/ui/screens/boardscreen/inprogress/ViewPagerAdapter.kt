package com.meeweel.kanban_board.ui.screens.boardscreen.inprogress

import androidx.collection.ArrayMap
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.meeweel.kanban_board.ui.FragmentReplacer
import com.meeweel.kanban_board.ui.screens.boardscreen.BaseBoardScreenFragment
import com.meeweel.kanban_board.ui.screens.boardscreen.done.DoneFragment
import com.meeweel.kanban_board.ui.screens.boardscreen.todo.ToDoFragment

class ViewPagerAdapter(container: FragmentActivity) :
    FragmentStateAdapter(container), FragmentReplacer {
    override var lastReplacedPos = 0
    private val mapOfFragment = ArrayMap<Int, BaseBoardScreenFragment>()

    override fun replace(position: Int, newFragment: BaseBoardScreenFragment, isNotify: Boolean) {
        newFragment.setPageInfo(
            pagePos = position,
            fragmentReplacer = this
        )
        mapOfFragment[position] = newFragment
        if (isNotify)
            notifyItemChanged(position)
    }

    override fun replaceDef(position: Int, isNotify: Boolean): BaseBoardScreenFragment {
        val fragment = when (position) {
            0 -> ToDoFragment()
            1 -> InProgressFragment()
            2 -> DoneFragment()
            else -> throw IllegalStateException()
        }

        replace(position, fragment, isNotify)

        return fragment
    }

    override fun replaceCurrentToDef() {
        replaceDef(lastReplacedPos)
    }

    override fun containsItem(itemId: Long): Boolean {
        var isContains = false
        mapOfFragment.values.forEach {
            if (it.pageId == itemId) {
                isContains = true
                return@forEach
            }
        }
        return isContains
    }

    override fun getItemId(position: Int) =
        mapOfFragment[position]?.pageId ?: super.getItemId(position)

    override fun getItemCount() = PAGE_COUNT

    override fun createFragment(position: Int): Fragment {
        return mapOfFragment[position] ?: replaceDef(position, false)
    }

    companion object {
        private const val PAGE_COUNT = 3
    }
}
