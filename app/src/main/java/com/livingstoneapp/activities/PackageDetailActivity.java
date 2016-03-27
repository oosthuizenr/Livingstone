package com.livingstoneapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.livingstoneapp.R;
import com.livingstoneapp.fragments.PackageDetailFragment;

public class PackageDetailActivity extends AppCompatActivity {

    public static String ARG_PACKAGE_NAME = "PackageName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_detail);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Bundle arguments = new Bundle();
        arguments.putString(PackageDetailFragment.ARG_PACKAGE_NAME, getIntent().getStringExtra(ARG_PACKAGE_NAME));
        arguments.putBoolean(PackageDetailFragment.ARG_2_PANE, false);
        PackageDetailFragment fragment = new PackageDetailFragment();
        fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
