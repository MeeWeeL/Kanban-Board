package com.meeweel.kanban_board.ui

import com.meeweel.kanban_board.ui.screens.boardscreen.BaseBoardScreenFragment

interface FragmentReplacer {
    fun replace(position: Int, newFragment: BaseBoardScreenFragment, isNotify: Boolean = true)
    fun replaceDef(position: Int, isNotify: Boolean = true) : BaseBoardScreenFragment
    fun replaceCurrentToDef()

    var lastReplacedPos: Int
}