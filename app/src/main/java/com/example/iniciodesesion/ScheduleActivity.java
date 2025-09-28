package com.example.iniciodesesion;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {

    private RecyclerView recyclerViewSchedule;
    private TextView tvCurrentDay;
    private List<ScheduleItem> scheduleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        initViews();
        setupSchedule();
    }

    private void initViews() {
        recyclerViewSchedule = findViewById(R.id.recyclerViewSchedule);
        tvCurrentDay = findViewById(R.id.tvCurrentDay);
    }

    private void setupSchedule() {
        scheduleList = new ArrayList<>();
        // Agregar horarios de ejemplo
        scheduleList.add(new ScheduleItem("08:00 - 10:00", "Matemáticas", "Aula 101"));
        scheduleList.add(new ScheduleItem("10:30 - 12:30", "Física", "Laboratorio A"));
        scheduleList.add(new ScheduleItem("14:00 - 16:00", "Programación", "Aula 205"));

        recyclerViewSchedule.setLayoutManager(new LinearLayoutManager(this));
        // Aquí necesitarás crear un ScheduleAdapter
        // ScheduleAdapter adapter = new ScheduleAdapter(scheduleList);
        // recyclerViewSchedule.setAdapter(adapter);

        tvCurrentDay.setText("Horario de Hoy - Lunes");
    }

    // Clase interna para representar un elemento del horario
    public static class ScheduleItem {
        private String time;
        private String subject;
        private String classroom;

        public ScheduleItem(String time, String subject, String classroom) {
            this.time = time;
            this.subject = subject;
            this.classroom = classroom;
        }

        // Getters
        public String getTime() { return time; }
        public String getSubject() { return subject; }
        public String getClassroom() { return classroom; }
    }
}
