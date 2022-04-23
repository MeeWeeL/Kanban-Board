package com.meeweel.kanban_board.domain.basemodels

class FakeRepository {
    private val repo = mutableListOf(
        BoardModel(0,"Board 1", "Bai777","KeyYk23",
            mutableListOf(
                TaskModel(0,"Task 1", "First task of project process", Status.DONE, Priority.NONE,"MeeWeeL"),
                TaskModel(0,"Task 2", "Second task of project process", Status.DONE, Priority.NONE, "Bai777"),
                TaskModel(0,"Task 3", "Third task of project process", Status.DONE, Priority.RED),
                TaskModel(0,"Task 4", "4 task of project process", Status.DONE, Priority.ORANGE),
                TaskModel(0,"Task 5", "5 task of project process", Status.DONE),
                TaskModel(0,"Task 6", "6 task of project process", Status.DONE),
                TaskModel(0,"Task 7", "7 task of project process", Status.DONE),
                TaskModel(0,"Task 8", "8 task of project process", Status.DONE)
            )
        ),
        BoardModel(0,"Board 2", "Bai777","KeyYk23",
            mutableListOf(
                TaskModel(0,"Task 1", "First task of project process", Status.DONE, Priority.NONE,"MeeWeeL"),
                TaskModel(0,"Task 2", "Second task of project process", Status.DONE, Priority.NONE, "Bai777"),
                TaskModel(0,"Task 3", "Third task of project process", Status.DONE, Priority.RED),
                TaskModel(0,"Task 4", "4 task of project process", Status.DONE, Priority.ORANGE),
            )
        )
    )

    fun getBoards() : MutableList<BoardModel> = repo
}