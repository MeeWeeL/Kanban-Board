package com.meeweel.kanban_board.domain.basemodels

import io.reactivex.rxjava3.core.Single

class FakeRepository {
    private val repo = mutableListOf(
        BoardModel(0,"Board 1", "Bai777","KeyYk23",
            mutableListOf(
                TaskModel(0,"Task 1", "First task of project process", Status.DONE, Priority.NONE,"MeeWeeL"),
                TaskModel(1,"Task 2", "Second task of project process", Status.DONE, Priority.NONE, "Bai777"),
                TaskModel(2,"Task 3", "Third task of project process", Status.IN_PROGRESS, Priority.RED),
                TaskModel(3,"Task 4", "4 task of project process", Status.IN_PROGRESS, Priority.ORANGE),
                TaskModel(4,"Task 5", "5 task of project process", Status.TO_DO),
                TaskModel(5,"Task 6", "6 task of project process", Status.TO_DO),
                TaskModel(6,"Task 7", "7 task of project process", Status.TO_DO),
                TaskModel(7,"Task 8", "8 task of project process", Status.TO_DO)
            )
        ),
        BoardModel(1,"Board 2", "Bai777","KeyYk23",
            mutableListOf(
                TaskModel(0,"Task 1", "First task of project process", Status.DONE, Priority.NONE,"MeeWeeL"),
                TaskModel(1,"Task 2", "Second task of project process", Status.DONE, Priority.NONE, "Bai777"),
                TaskModel(2,"Task 3", "Third task of project process", Status.IN_PROGRESS, Priority.RED),
                TaskModel(3,"Task 4", "4 task of project process", Status.TO_DO, Priority.ORANGE),
            )
        )
    )

    fun getBoards() : Single<List<BoardModel>> = Single.just(repo)
}