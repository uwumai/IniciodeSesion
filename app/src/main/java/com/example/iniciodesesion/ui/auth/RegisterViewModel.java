package com.example.iniciodesesion.ui.auth;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.iniciodesesion.data.repository.AuthRepository;

public class RegisterViewModel extends ViewModel {
    private final AuthRepository authRepository;
    private final MutableLiveData<Boolean> registerSuccess = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);

    public RegisterViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public LiveData<Boolean> getRegisterSuccess() {
        return registerSuccess;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void registerWithEmail(String nombre, String email, String password, String confirmPassword) {
        // Validar nombre
        if (nombre == null || nombre.trim().length() < 3) {
            errorMessage.setValue("El nombre debe tener al menos 3 caracteres");
            return;
        }

        // Validar email
        if (!isValidEmail(email)) {
            errorMessage.setValue("Email inválido");
            return;
        }

        // Validar contraseña
        if (password.length() < 8) {
            errorMessage.setValue("La contraseña debe tener al menos 8 caracteres");
            return;
        }

        if (!password.matches(".*[A-Z].*")) {
            errorMessage.setValue("La contraseña debe contener al menos una mayúscula");
            return;
        }

        if (!password.matches(".*[0-9].*")) {
            errorMessage.setValue("La contraseña debe contener al menos un número");
            return;
        }

        // Validar confirmación
        if (!password.equals(confirmPassword)) {
            errorMessage.setValue("Las contraseñas no coinciden");
            return;
        }

        isLoading.setValue(true);
        authRepository.registerWithEmail(email, password)
                .addOnCompleteListener(task -> {
                    isLoading.setValue(false);
                    if (task.isSuccessful()) {
                        registerSuccess.setValue(true);
                    } else {
                        String error = task.getException() != null ?
                                task.getException().getMessage() : "Error al registrar";
                        errorMessage.setValue(error);
                    }
                });
    }

    private boolean isValidEmail(String email) {
        return email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}