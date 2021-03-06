package com.business.warthon.login.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.business.warthon.R;
import com.business.warthon.login.contracts.MenuPrincipalContract;
import com.business.warthon.utiles.GeneralFragment;
import com.business.warthon.utiles.LogWarthon;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        GeneralFragment.ContentActivity, MenuPrincipalContract.View {

    LogWarthon log = LogWarthon.newIntance(MainActivity.class.getSimpleName());

    private Toolbar toolbar;
    private NavigationView navigationView;

    private MenuPrincipalContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.toolbar = findViewById(R.id.toolbar);
        this.toolbar.setTitle("");
        setSupportActionBar(this.toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        presenter = this.instanciarPresenter();

        GeneralFragment gf = this.crearFragment(GeneralFragment.newBulder(MenuPrincipalFragment.class).setCheckedItem(R.id.nav_inicio));
        this.cambiarFragment(gf);
    }

    private MenuPrincipalContract.Presenter instanciarPresenter() {
        return MenuPrincipalContract.newPresenter().setContext(this).setView(this);
    }

    @Override
    public GeneralFragment crearFragment(GeneralFragment.GeneralFragmentBuild gfb) {
        return gfb.setToolbar(this.toolbar)
            .setNavigationView(this.navigationView)
            .create();
    }

    @Override
    public void cambiarFragment(GeneralFragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {
            GeneralFragment gf = this.crearFragment(GeneralFragment.newBulder(MenuPrincipalFragment.class)
                .setCheckedItem(R.id.nav_inicio));
            this.cambiarFragment(gf);
        } else if (id == R.id.nav_chat) {

        } else if (id == R.id.nav_llamadas) {

        } else if (id == R.id.nav_citas) {

        } else if (id == R.id.nav_salir) {
            presenter.cerrarSesion();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        if (fragmentList != null) {
            for (Fragment fragment : fragmentList) {
                if (fragment instanceof MenuPrincipalFragment) {
                    finish();
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void respuestaCerrarSession() {
        log.info("se cerro la aplicacion");
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void errorRespuesta(String mensaje) {

    }
}
