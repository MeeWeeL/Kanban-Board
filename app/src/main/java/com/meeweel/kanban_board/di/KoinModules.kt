package com.meeweel.kanban_board.di

import androidx.room.Room
import com.meeweel.kanban_board.data.interactor.AuthorizationInteractor
import com.meeweel.kanban_board.data.interactor.AuthorizationInteractorImpl
import com.meeweel.kanban_board.data.interactor.Interactor
import com.meeweel.kanban_board.data.interactor.InteractorImpl
import com.meeweel.kanban_board.data.network.authorization.AuthorizationRepository
import com.meeweel.kanban_board.data.network.authorization.AuthorizationRepositoryImpl
import com.meeweel.kanban_board.data.network.authorization.AuthorizationService
import com.meeweel.kanban_board.data.network.boards.BoardsService
import com.meeweel.kanban_board.data.network.boards.RemoteRepository
import com.meeweel.kanban_board.data.network.boards.RemoteRepositoryImpl
import com.meeweel.kanban_board.data.room.LocalUserRepository
import com.meeweel.kanban_board.data.room.LocalUserRepositoryImpl
import com.meeweel.kanban_board.data.room.UserEntityDataBases
import com.meeweel.kanban_board.ui.screens.authorization.AuthorizationViewModel
import com.meeweel.kanban_board.ui.screens.boardscreen.TasksScreenFragmentViewModel
import com.meeweel.kanban_board.ui.screens.mainfragment.MainFragmentViewModel
import com.meeweel.kanban_board.ui.screens.settings.SettingsViewModel
import org.koin.dsl.module

const val DB_NAME = "UserRepo.db"

val application = module {

    // Local data
    single { Room.databaseBuilder(
        get(), UserEntityDataBases::class.java, DB_NAME
    ).allowMainThreadQueries().build().entityDao() }
    single<LocalUserRepository> { LocalUserRepositoryImpl(get()) }

    // Remote data
    single { BoardsService().getService() }
    single<RemoteRepository> { RemoteRepositoryImpl(get()) }

    // Authorization data
    single { AuthorizationService().getService() }
    single<AuthorizationRepository> { AuthorizationRepositoryImpl(get()) }

    // Interactor
    single<AuthorizationInteractor> { AuthorizationInteractorImpl(get(), get()) }
    factory<Interactor> { InteractorImpl(get(), get()) }
}

val authorizationScreen = module {
    factory { AuthorizationViewModel(get()) }
}

val boardsScreen = module {
    factory { MainFragmentViewModel(get()) }
}

val tasksScreen = module {
    factory { TasksScreenFragmentViewModel(get()) }
}

val settingsScreen = module {
    factory { SettingsViewModel(get()) }
}