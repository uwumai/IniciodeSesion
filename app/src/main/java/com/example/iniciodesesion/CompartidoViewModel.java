package com.example.iniciodesesion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CompartidoViewModel extends ViewModel {
    private final MutableLiveData<String> tareaTexto = new MutableLiveData<>();
    private final MutableLiveData<Integer> colorMateria = new MutableLiveData<>();

    public void setTarea(String texto, int color) {
        tareaTexto.setValue(texto);
        colorMateria.setValue(color);
    }

    public LiveData<String> getTareaTexto() {
        return tareaTexto;
    }

    public LiveData<Integer> getColorMateria() {
        return colorMateria;
    }
}