package com.meeweel.core

import com.meeweel.core.basemodels.Priority
import com.meeweel.core.basemodels.Status
import com.meeweel.core.basemodels.TaskModel
import org.junit.Assert
import org.junit.Test

class ModelsTest {

    // Priority

    @Test
    fun priorityModel_isNoneIntComponentCorrect() {
        Assert.assertEquals(Priority.NONE.int, 0)
    }

    @Test
    fun priorityModel_isGreenIntComponentCorrect() {
        Assert.assertEquals(Priority.GREEN.int, 1)
    }

    @Test
    fun priorityModel_isYellowIntComponentCorrect() {
        Assert.assertEquals(Priority.YELLOW.int, 2)
    }

    @Test
    fun priorityModel_isOrangeIntComponentCorrect() {
        Assert.assertEquals(Priority.ORANGE.int, 3)
    }

    @Test
    fun priorityModel_isRedIntComponentCorrect() {
        Assert.assertEquals(Priority.RED.int, 4)
    }

    @Test
    fun priorityModel_isNoneStringComponentCorrect() {
        Assert.assertEquals(Priority.NONE.text, "")
    }

    @Test
    fun priorityModel_isGreenStringComponentCorrect() {
        Assert.assertEquals(Priority.GREEN.text, "Не к спеху")
    }

    @Test
    fun priorityModel_isYellowStringComponentCorrect() {
        Assert.assertEquals(Priority.YELLOW.text, "Скоро")
    }

    @Test
    fun priorityModel_isOrangeStringComponentCorrect() {
        Assert.assertEquals(Priority.ORANGE.text, "Пора")
    }

    @Test
    fun priorityModel_isRedStringComponentCorrect() {
        Assert.assertEquals(Priority.RED.text, "Срочно!")
    }

    // Status

    @Test
    fun statusModel_isToDoIntComponentCorrect() {
        Assert.assertEquals(Status.TO_DO.int, 0)
    }

    @Test
    fun statusModel_isInProgressIntComponentCorrect() {
        Assert.assertEquals(Status.IN_PROGRESS.int, 1)
    }

    @Test
    fun statusModel_isDoneIntComponentCorrect() {
        Assert.assertEquals(Status.DONE.int, 2)
    }

    // Task Model

    @Test
    fun taskModel_isDefaultStatusComponentCorrect() {
        val task = TaskModel(1)
        Assert.assertEquals(task.status, Status.TO_DO)
    }

    @Test
    fun taskModel_isDefaultPriorityComponentCorrect() {
        val task = TaskModel(1)
        Assert.assertEquals(task.priority, Priority.NONE)
    }

    @Test
    fun taskModel_isDefaultDescriptionComponentCorrect() {
        val task = TaskModel(1)
        Assert.assertEquals(task.description, "")
    }

    @Test
    fun taskModel_isDefaultPerformerComponentCorrect() {
        val task = TaskModel(1)
        Assert.assertEquals(task.performer, "None")
    }

    @Test
    fun taskModel_isDefaultNameComponentCorrect() {
        val task = TaskModel(1)
        Assert.assertEquals(task.name, "Task")
    }

}