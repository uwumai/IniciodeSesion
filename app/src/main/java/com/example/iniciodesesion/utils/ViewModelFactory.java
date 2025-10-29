package com.example.iniciodesesion.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.iniciodesesion.data.repository.AuthRepository;
import com.example.iniciodesesion.ui.auth.LoginViewModel;
import com.example.iniciodesesion.ui.auth.RegisterViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final AuthRepository authRepository;

    public ViewModelFactory(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(authRepository);
        } else if (modelClass.isAssignableFrom(RegisterViewModel.class)) {
            return (T) new RegisterViewModel(authRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}