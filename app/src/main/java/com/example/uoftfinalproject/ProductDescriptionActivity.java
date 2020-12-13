package com.example.uoftfinalproject;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;

import com.example.uoftfinalproject.fragment.MapFragment;
import com.example.uoftfinalproject.fragment.ProductDetailsFragment;
import com.example.uoftfinalproject.model.Product;

import androidx.appcompat.app.ActionBar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDescriptionActivity extends BaseActivity {


    public static final String PRODUCT = "product";
    @BindView(R.id.view_pager)
    ViewPager viewPagerProduct;
    ProductPageAdapter productPageAdapter;

    Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);
        Log.i("ProductDescription","Product");
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("ProductDescription");
        product = getIntent().getParcelableExtra(PRODUCT);
        productPageAdapter = new ProductPageAdapter(getSupportFragmentManager());
        viewPagerProduct.setAdapter(productPageAdapter);
        Log.d("name", product.getName());
        ActivityCompat.requestPermissions(ProductDescriptionActivity.this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                1);
    }

    public class ProductPageAdapter extends FragmentStatePagerAdapter {

        public ProductPageAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
           switch (position) {
                case 0:
                    Log.i("ProductDetalis","Details Fragment Call");
                    return ProductDetailsFragment.newInstance(product);
               case 1:
                   Log.i("ProductDetails","Map Fragment call");
                    return new MapFragment().newInstance(product);


            }
            return  null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {


            if (position == 0) {
                return "Details";
            } else {
                return "Map";
            }
        }
    }
}
