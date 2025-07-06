package com.sample.credentialmanager

import com.sample.credentialmanager.ui.feature.login.LoginScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {
    viewModel { LoginScreenViewModel() }
}
