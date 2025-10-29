package com.example.iniciodesesion.ui.auth;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.iniciodesesion.data.repository.AuthRepository;

public class LoginViewModel extends ViewModel {
    private final AuthRepository authRepository;
    private final MutableLiveData<Boolean> loginSuccess = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);

    public LoginViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public LiveData<Boolean> getLoginSuccess() {
        return loginSuccess;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void loginWithEmail(String email, String password) {
        if (!isValidEmail(email)) {
            errorMessage.setValue("Email inválido");
            return;
        }

        if (password.length() < 6) {
            errorMessage.setValue("La contraseña debe tener al menos 6 caracteres");
            return;
        }

        isLoading.setValue(true);
        authRepository.loginWithEmail(email, password)
                .addOnCompleteListener(task -> {
                    isLoading.setValue(false);
                    if (task.isSuccessful()) {
                        loginSuccess.setValue(true);
                    } else {
                        String error = task.getException() != null ?
                                task.getException().getMessage() : "Error desconocido";
                        errorMessage.setValue(error);
                    }
                });
    }

    public void loginWithGoogle(String idToken) {
        isLoading.setValue(true);
        authRepository.firebaseAuthWithGoogle(idToken)
                .addOnCompleteListener(task -> {
                    isLoading.setValue(false);
                    if (task.isSuccessful()) {
                        loginSuccess.setValue(true);
                    } else {
                        String error = task.getException() != null ?
                                task.getException().getMessage() : "Error con Google Sign-In";
                        errorMessage.setValue(error);
                    }
                });
    }

    private boolean isValidEmail(String email) {
        return email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
