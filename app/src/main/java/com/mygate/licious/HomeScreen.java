package com.mygate.licious;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mygate.licious.ViewModel.DataViewModel;
import com.mygate.licious.ViewModel.DataViewModelFactory;
import com.mygate.licious.model.DataToList;
import com.mygate.licious.model.ProcessedData;
import com.mygate.licious.ui.adapter.GridAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.VERTICAL;


public class HomeScreen extends AppCompatActivity {

    DataViewModel dataViewModel;
    TextView title;
    TextView infoMessage;
    TextView infoBadge;
    TextView tvAllSlotFilter;
    TextView tvTodayfIlter;
    RecyclerView recyclerView;
    GridAdapter contactCustomAdapter;
    private List<DataToList> list = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title=findViewById(R.id.tvHeader);
        infoMessage=findViewById(R.id.tvFavTxt);
        infoBadge=findViewById(R.id.tvItemsCount);
        tvAllSlotFilter=findViewById(R.id.tvAllSlots);
        tvTodayfIlter=findViewById(R.id.tvToday);
        recyclerView=findViewById(R.id.rvItems);
        DataViewModelFactory dataViewModelFactory=DataViewModelFactory.getInstance();
        dataViewModel=new ViewModelProvider(this,dataViewModelFactory).get(DataViewModel.class);

        dataViewModel.getProcessedLiveData().removeObserver(processedDataObserver);
        dataViewModel.getProcessedLiveData().observe(this,processedDataObserver);
        dataViewModel.getError().observe(this,errorObserver);

        dataViewModel.loadJSONFromAsset();

    }

    private Observer<ProcessedData> processedDataObserver=new Observer<ProcessedData>() {
        @Override
        public void onChanged(ProcessedData processedData) {
            if(processedData==null)
                return;

            title.setText(processedData.getTitle());
            infoMessage.setText(processedData.getInfoMessage());
            infoBadge.setText("("+processedData.getInfoBadge()+")");
            tvAllSlotFilter.setText(processedData.getFilterList().get(0).getTitle());
            tvTodayfIlter.setText(processedData.getFilterList().get(1).getTitle());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(AppController.getContext(), 2,LinearLayoutManager.VERTICAL, false);
            GridAdapter gridAdapter=new GridAdapter(AppController.getContext(),processedData.getList());
            recyclerView.addItemDecoration(new SpacesItemDecoration(20));

            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(gridAdapter);
        }
    };

    private Observer<String> errorObserver=new Observer<String>() {
        @Override
        public void onChanged(String error) {
            Toast.makeText(HomeScreen.this,"Error Parsing Data",Toast.LENGTH_LONG).show();

        }
    };
}
