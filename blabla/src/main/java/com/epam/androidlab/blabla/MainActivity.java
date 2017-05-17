package com.epam.androidlab.blabla;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.epam.androidlab.blabla.fragments.FirstFragment;
import com.epam.androidlab.blabla.fragments.SecondFragment;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private View headerLayout;
    private ImageView headerImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewElements();
        downloadImage();
    }

    // Initialization view elements
    private void initViewElements() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        headerLayout = navigationView.getHeaderView(0);
        headerImageView = (ImageView) headerLayout.findViewById(R.id.headerImage);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    // Downloads image from internet
    private void downloadImage() {
        String IMAGE_URL = "http://www.howtablet.ru/wp-content/uploads/2016/04/%D0%9E%D0%B1%D0%BD%D0%BE%D0%B2%D0%BB%D0%B5%D0%BD%D0%B8%D0%B5-Android-6.0.1-Marshmallow.jpg";
        Picasso.with(headerLayout.getContext())
                .load(IMAGE_URL)
                .into(headerImageView);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.custom_view:
                fragmentTransaction.replace(R.id.frame_layout, new FirstFragment());
                break;
            case R.id.second_fragment:
                fragmentTransaction.replace(R.id.frame_layout, new SecondFragment());
                break;
            default:
                fragmentTransaction.add(R.id.frame_layout, new FirstFragment());
                break;
        }
        fragmentTransaction.commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
