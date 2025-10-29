package com.example.iniciodesesion.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainViewModel extends AndroidViewModel {
    private final MutableLiveData<String> currentFragment = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        currentFragment.setValue("apuntes");
    }

    public LiveData<String> getCurrentFragment() {
        return currentFragment;
    }

    public void setCurrentFragment(String fragment) {
        currentFragment.setValue(fragment);
    }
}
