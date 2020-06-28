package com.mygate.licious.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.mygate.licious.AppController;
import com.mygate.licious.model.AppData;
import com.mygate.licious.model.DataToList;
import com.mygate.licious.model.ProcessedData;
import com.mygate.licious.model.ProductMaster;
import com.mygate.licious.model.ProductMerchantdising;
import com.mygate.licious.model.ProductPricing;
import com.mygate.licious.model.Products;
import com.mygate.licious.threading.IBusinessExecutor;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DataViewModel extends ViewModel {
    IBusinessExecutor businessExecutor;

    public DataViewModel(IBusinessExecutor businessExecutor) {
        this.businessExecutor=businessExecutor;
    }

    public MutableLiveData<ProcessedData> mutableAppLiveData=new MutableLiveData<>();
    public MutableLiveData<String> error=new MutableLiveData<>();

    public void loadJSONFromAsset() {
        businessExecutor.executeInBusinessThread(new Runnable() {
            @Override
            public void run() {
                String json = null;
                try {
                    InputStream is = AppController.getContext().getAssets().open("product_reorder.json");

                    int size = is.available();

                    byte[] buffer = new byte[size];

                    is.read(buffer);

                    is.close();

                    json = new String(buffer, "UTF-8");
                    transformJsonForAdapter(json);

                } catch (IOException ex) {
                    error.setValue(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
    }

    public LiveData<ProcessedData> getProcessedLiveData(){
        return mutableAppLiveData;
    }

    public LiveData<String> getError(){
        return error;
    }

    private void transformJsonForAdapter(String json) {
        AppData appData= new Gson().fromJson(json,AppData.class);
        List<Products> products=appData.getData().getProducts();
        List<DataToList> dataToLists=new ArrayList<>();
        for(Products p:products){
            ProductMaster productMaster=p.getProduct_master();
            ProductMerchantdising productMerchantdising=p.getProduct_merchantdising();
            ProductPricing productPricing=p.getProduct_pricing();
            String itemName=productMaster.getPr_name();
            String itemWeight=productMaster.getPr_weight();
            String imageUrl=productMerchantdising.getPr_image();
            String price=productPricing.getBase_price()+"";
            DataToList dataToList=new DataToList(imageUrl,itemName,price,itemWeight);
            dataToLists.add(dataToList);
        }

        ProcessedData processedData=new ProcessedData(dataToLists,
                            appData.getData().getTitle(),
                            appData.getData().getInfo_message(),
                            appData.getData().getInfo_badge(),
                            appData.getData().getFilters());

        mutableAppLiveData.postValue(processedData);
    }
}
