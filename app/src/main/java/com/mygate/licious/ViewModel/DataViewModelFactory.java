package com.mygate.licious.ViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mygate.licious.threading.BusinessExecutor;
import com.mygate.licious.threading.IBusinessExecutor;

public class DataViewModelFactory implements ViewModelProvider.Factory {
    private static DataViewModelFactory dataViewModelFactory = new DataViewModelFactory();
    private IBusinessExecutor businessExecutor;

    private DataViewModelFactory(){
        this.businessExecutor= BusinessExecutor.getInstance();
    }

    public static DataViewModelFactory getInstance() {
        return dataViewModelFactory;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DataViewModel.class)) {
            return (T) new DataViewModel(businessExecutor);
        }
        throw new IllegalArgumentException("Unknown model class " + modelClass);
    }
}
