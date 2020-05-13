package com.example.wafil.Wafil.basBangunan.ui.Pesanan;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PesananViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PesananViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Pesanan Anda:");
    }

    public LiveData<String> getText() {
        return mText;
    }
}