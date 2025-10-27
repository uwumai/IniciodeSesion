package com.example.iniciodesesion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class menu extends AppCompatActivity {

    private LinearLayout btnNotes, btnSchedule, btnTasks;
    private Button btnLogout;
    private FirebaseAuth mAuth;
    private TextView tvSaludoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        mAuth = FirebaseAuth.getInstance();
        checkUserAuthentication();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;




        });

        initViews();
        setupClickListeners();

        // Mostrar fragmento por defecto
        if (savedInstanceState == null) {
            loadFragment(new GeneralFragment());
        }

        // Mostrar saludo personalizado
        mostrarSaludo();
    }

    private void checkUserAuthentication() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void initViews() {
        btnNotes = findViewById(R.id.btn_notes);
        btnSchedule = findViewById(R.id.btn_schedule);
        btnTasks = findViewById(R.id.btn_tasks);
        btnLogout = findViewById(R.id.btnLogout);
        tvSaludoUsuario = findViewById(R.id.tvSaludoUsuario);
    }

    private void setupClickListeners() {
        btnNotes.setOnClickListener(v -> loadFragment(new ApuntesFragment()));
        btnSchedule.setOnClickListener(v -> loadFragment(new HorarioFragment()));
        btnTasks.setOnClickListener(v -> loadFragment(new TareasFragment()));

        btnLogout.setOnClickListener(v -> {
            mAuth.signOut();
            Intent intent = new Intent(menu.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    // 💬 Mostrar saludo según tipo de cuenta
    @SuppressLint("SetTextI18n")
    private void mostrarSaludo() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) return;

        String nombreUsuario = null;

        // 1️⃣ Si inició con Google, obtener displayName
        for (UserInfo userInfo : currentUser.getProviderData()) {
            if (userInfo.getProviderId().equals("google.com")) {
                nombreUsuario = userInfo.getDisplayName();
                break;
            }
        }

        // 2️⃣ Si no es cuenta Google, intenta obtener nombre guardado (si lo guardaste en tu BD)
        if (nombreUsuario == null || nombreUsuario.isEmpty()) {
            // Si lo guardas en Realtime Database o Firestore, aquí podrías cargarlo.
            // Por ahora usamos el correo como respaldo:
            String email = currentUser.getEmail();
            if (email != null && email.contains("@")) {
                nombreUsuario = email.substring(0, email.indexOf("@"));
            } else {
                nombreUsuario = "usuario";
            }
        }

        // 3️⃣ Mostrar el saludo final
        tvSaludoUsuario.setText("¡Hola, " + nombreUsuario + "!");
    }
}