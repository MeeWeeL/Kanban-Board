package com.meeweel.data

import com.meeweel.core.basemodels.BoardModel
import com.meeweel.core.basemodels.Priority
import com.meeweel.core.basemodels.Status
import com.meeweel.core.basemodels.TaskModel
import com.meeweel.data.responsemodels.Response
import com.meeweel.data.responsemodels.TaskResponse
import com.meeweel.data.responsemodels.toBoardModel
import org.junit.Assert
import org.junit.Test

class ConverterUnitTest {

    @Test
    fun responseConverter_isResponseBoardToBoardModelCorrect() {
        val response = Response(
            1,
            "Title",
            "Host",
            listOf(TaskResponse(
                1,
                "Task Title",
                "Task Description",
                0,
                0
            ))
        )
        Assert.assertEquals(response.toBoardModel().javaClass, BoardModel::class.java)
    }

    @Test
    fun responseConverter_isResponseTaskToTaskModelCorrect() {
        val response = Response(
            1,
            "Title",
            "Host",
            listOf(TaskResponse(
                1,
                "Task Title",
                "Task Description",
                0,
                0
            ))
        )
        Assert.assertEquals(response.toBoardModel().toDoList[0].javaClass, TaskModel::class.java)
    }

    @Test
    fun responseConverter_isPriorityOfTaskCorrect() {
        val response = Response(
            1,
            "Title",
            "Host",
            listOf(TaskResponse(
                1,
                "Task Title",
                "Task Description",
                0,
                0
            ))
        )
        Assert.assertEquals(response.toBoardModel().toDoList[0].priority, Priority.NONE)
    }

    @Test
    fun responseConverter_isStatusOfTaskCorrect() {
        val response = Response(
            1,
            "Title",
            "Host",
            listOf(TaskResponse(
                1,
                "Task Title",
                "Task Description",
                0,
                0
            ))
        )
        Assert.assertEquals(response.toBoardModel().toDoList[0].status, Status.TO_DO)
    }
}