package com.example.uoftfinalproject.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.uoftfinalproject.R;
import com.example.uoftfinalproject.adapter.SlidingImageAdapter;
import com.example.uoftfinalproject.model.ImageModel;
import com.example.uoftfinalproject.model.Product;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailsFragment extends Fragment {

    private static final String PRODUCT = "product";
    @BindView(R.id.pager)
    ViewPager viewPagerProduct;
    Product product;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<ImageModel> imageModelArrayList;
    public EditText edtNumber;
    public EditText edtMsg;

    @BindView(R.id.txv_product_weight)
    TextView txvProductWeight;
    @BindView(R.id.txv_product_dimension)
    TextView txvProductDimension;
    @BindView(R.id.txv_product_price)
    TextView txvProductPrice;
    @BindView(R.id.txv_product_name)
    TextView txvProductName;
    @BindView(R.id.txv_product_desscription)
    TextView txvProductDescription;
    @BindView(R.id.indicator)
    CirclePageIndicator indicatorCircle;
    public static Fragment newInstance(Product product) {
        ProductDetailsFragment productDetailsFragment = new ProductDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(PRODUCT, product);
        productDetailsFragment.setArguments(args);
        return productDetailsFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        View view = inflater.inflate(R.layout.fragment_product_details, parent, false);

        ButterKnife.bind(this, view);
        //((AppCompatActivity)getActivity()).setSupportActionBar((Toolbar) view.findViewById(R.id.toolbar));
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        product = (Product) getArguments().get(PRODUCT);
        getActivity().setTitle("Product Description");

        txvProductName.setText(product.getName());
        // txvPhone.setText(product.getPhone());
        //txvWeb.setText(product.getWeb());
        txvProductDescription.setText("\u2022 " + product.getDescription());
        txvProductPrice.setText("CAD$ " + String.valueOf(product.getPrice()));
        txvProductWeight.setText(product.getWeight() );


        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = imageList();
        imageSlide();


        return view;
    }
    private ArrayList<ImageModel> imageList() {
        ArrayList<ImageModel> list = new ArrayList<>();
        if (product.getImages() != null) {
            for (int i = 0; i < product.getImages().size(); i++) {
                ImageModel imageModel = new ImageModel();
                imageModel.setImage_drawable((product.getImages().get(i)));
                list.add(imageModel);
            }
        }
        Log.d("list", String.valueOf(list));
        return list;
    }

    private void imageSlide() {

        viewPagerProduct.setAdapter(new SlidingImageAdapter(getActivity(), imageModelArrayList));


        indicatorCircle.setViewPager(viewPagerProduct);
        final float density = getResources().getDisplayMetrics().density;

        //Set circle indicator radius
        indicatorCircle.setRadius(5 * density);

        NUM_PAGES = imageModelArrayList.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                viewPagerProduct.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        indicatorCircle.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
        // Pager listener over indicator
    }

}
