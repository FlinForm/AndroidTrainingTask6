package com.epam.androidlab.blabla;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.epam.androidlab.blabla.fragments.FirstFragment;
import com.epam.androidlab.blabla.fragments.SecondFragment;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView headerImageView;
    private Toolbar toolbar;
    private FragmentTransaction fragmentTransaction;
    private FrameLayout frameLayout;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View headerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        headerLayout = navigationView.getHeaderView(0);
        headerImageView = (ImageView) headerLayout.findViewById(R.id.headerImage);
        Picasso.with(headerLayout.getContext())
                .load("https://habrastorage.org/getpro/habr/post_images/3a0/671/22f/3a067122fe0ad29b5f22b0c35f1f3331.png")
                .into(headerImageView);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        frameLayout.removeAllViews();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        int id = item.getItemId();
        switch (id) {
            case R.id.custom_view:
                fragmentTransaction.add(R.id.frame_layout, new FirstFragment());
                break;
            case R.id.second_fragment:
                fragmentTransaction.add(R.id.frame_layout, new SecondFragment());
                break;
        }
        fragmentTransaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
