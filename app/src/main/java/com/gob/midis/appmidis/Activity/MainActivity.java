package com.gob.midis.appmidis.Activity;

import android.annotation.TargetApi;

import android.content.Intent;
import android.os.Build;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.gob.midis.appmidis.Fragment.ExportDataFragment;
import com.gob.midis.appmidis.Fragment.HogarListFragment;
import com.gob.midis.appmidis.R;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navview);

        //setFragmentByDefault();

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                boolean fragmentTranaction = false;
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.menu_hogares:
                        fragment = new HogarListFragment();
                        fragmentTranaction = true;
                        break;
                    case R.id.menu_sincronizar_lote:
                        fragment = new ExportDataFragment();
                        fragmentTranaction = true;
                        break;
                    case R.id.menu_ayuda:
                        fragment = new ExportDataFragment();
                        fragmentTranaction = true;
                        break;
                    case R.id.menu_cerrar_sesion:
                        i = new Intent(MainActivity.this, LoginActivity.class);
                        i.setFlags(i.FLAG_ACTIVITY_NEW_TASK | i.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
                        break;
                }
                if (fragmentTranaction) {

                    changeFragment(fragment,item);

/*                    getSupportFragmentManager().
                            beginTransaction().
                            replace(R.id.content_frame, fragment)
                            .commit();
                    item.setChecked(true);
                    getSupportActionBar().setTitle(item.getTitle());
                    drawerLayout.closeDrawers();*/
                }
                return false;
            }
        });

    }

    private void setFragmentByDefault(){
        changeFragment(new HogarListFragment() ,navigationView.getMenu().getItem(0));
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void changeFragment(Fragment fragment,MenuItem item){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
        drawerLayout.closeDrawers();
    }
    private void changeFragment(Fragment fragment,String titulo){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
        getSupportActionBar().setTitle(titulo);
        drawerLayout.closeDrawers();
    }

/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
*/



}
