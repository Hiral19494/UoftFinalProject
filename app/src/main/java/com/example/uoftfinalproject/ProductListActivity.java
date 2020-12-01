package com.example.uoftfinalproject;

import android.os.Bundle;

import com.example.uoftfinalproject.adapter.ProductDetailsAdapter;
import com.example.uoftfinalproject.data.APIClient;
import com.example.uoftfinalproject.design.MyDividerItemDecoration;
import com.example.uoftfinalproject.model.Product;

import java.util.ArrayList;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListActivity extends AppCompatActivity {
    @BindView(R.id.rsv_products)
    RecyclerView rsvProduct;
    private ArrayList<Product> productList = new ArrayList<>();
    private ProductDetailsAdapter productDetailsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Product List");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rsvProduct.setLayoutManager(mLayoutManager);
        rsvProduct.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));

        apicallproduct();
        productDetailsAdapter = new ProductDetailsAdapter(productList, getApplicationContext());
        rsvProduct.setAdapter(productDetailsAdapter);
    }

    private void apicallproduct() {
        Call<ArrayList<Product>> productListCall = APIClient.getApiInstance().productDetails();

        productListCall.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                if (response.isSuccessful()){
                    productList.addAll(response.body());
                    productDetailsAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {

            }
        });


    }
}

