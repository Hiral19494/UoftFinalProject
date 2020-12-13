package com.example.uoftfinalproject.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.uoftfinalproject.R;
import com.example.uoftfinalproject.WebViewActivity;
import com.example.uoftfinalproject.adapter.SlidingImageAdapter;
import com.example.uoftfinalproject.model.ImageModel;
import com.example.uoftfinalproject.model.Product;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.uoftfinalproject.WebViewActivity.WEBDATA;

public class ProductDetailsFragment extends Fragment {

    private static final String PRODUCT = "product";
    @BindView(R.id.pager)
    ViewPager viewPagerProduct;
    Product product;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<ImageModel> imageModelArrayList;
    @BindView(R.id.txv_product_web)
    TextView txvWebsite;
    @BindView(R.id.txv_phone_no)
    TextView txvPhoneNo;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
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
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        product = (Product) getArguments().get(PRODUCT);

        txvProductName.setText(product.getName());
        txvPhoneNo.setText(product.getPhone());
        txvWebsite.setText(product.getWeb());
        txvProductDescription.setText("\u2022 " + product.getDescription());
        txvProductPrice.setText("CAD$ " + String.valueOf(product.getPrice()));
        txvProductWeight.setText(product.getWeight() );
        txvProductDimension.setText(product.getDimensions().getLength() +
                " * " + product.getDimensions().getWidth() +
                " * " + product.getDimensions().getHeight());

        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = imageList();
        imageSlide();

        return view;
    }
    @OnClick(R.id.txv_product_web)
    public void onClickWeb() {
        Log.i("Web","WebActivity call");
        Intent webdataIntent = new Intent(getActivity(), WebViewActivity.class);
        webdataIntent.putExtra(WEBDATA, product);
        startActivity(webdataIntent);
    }

    @OnClick(R.id.txv_phone_no)
    public void onClickPhone() {
        Log.i("Phone Dial", "Phone Dial Pad call");
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
        phoneIntent.setData(Uri.parse("tel:" + product.getPhone()));
        startActivity(phoneIntent);

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
        Log.i("list", String.valueOf(list));
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
