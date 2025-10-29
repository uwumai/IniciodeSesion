package com.example.iniciodesesion.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.iniciodesesion.R;
import com.example.iniciodesesion.data.repository.AuthRepository;
import com.example.iniciodesesion.ui.main.MainActivity;
import com.example.iniciodesesion.utils.ViewModelFactory;

public class RegisterActivity extends AppCompatActivity {
    private RegisterViewModel viewModel;
    private EditText etNombre, etEmail, etPassword, etConfirmPassword;
    private Button btnRegister;
    private TextView tvLogin;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
        initViewModel();
        setupObservers();
        setupListeners();
    }

    private void initViews() {
        etNombre = findViewById(R.id.etNombre);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin);
        progressBar = findViewById(R.id.progressBar);
    }

    private void initViewModel() {
        AuthRepository authRepository = new AuthRepository(this, getString(R.string.default_web_client_id));
        ViewModelFactory factory = new ViewModelFactory(authRepository);
        viewModel = new ViewModelProvider(this, factory).get(RegisterViewModel.class);
    }

    private void setupObservers() {
        viewModel.getRegisterSuccess().observe(this, success -> {
            if (success != null && success) {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        });

        viewModel.getErrorMessage().observe(this, error -> {
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_LONG).show();
            }
        });

        viewModel.getIsLoading().observe(this, isLoading -> {
            progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            btnRegister.setEnabled(!isLoading);
        });
    }

    private void setupListeners() {
        btnRegister.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            viewModel.registerWithEmail(nombre, email, password, confirmPassword);
        });

        tvLogin.setOnClickListener(v -> finish());
    }
}