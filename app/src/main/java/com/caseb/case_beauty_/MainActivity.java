//Pacote da Classe

package com.caseb.case_beauty_;
//Importes da Classe

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.caseb.case_beauty_Fargments.Ajuda;
import com.caseb.case_beauty_Fargments.AtenderSolicitacao;
import com.caseb.case_beauty_Fargments.CadProfFragment;
import com.caseb.case_beauty_Fargments.MainFragment;
import com.caseb.case_beauty_Fargments.Maps;
import com.caseb.case_beauty_Fargments.MuralFragment;
import com.caseb.case_beauty_Fargments.Perfil;
import com.caseb.case_beauty_Fargments.Sobre;

//Método Main, primeiro método executado
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Layout da Classe
        setContentView(R.layout.activity_main);
        //Chamando o toolbar, barra de cima "configurações"
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Chamando o drawer layout, menus
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //Menu Toggle, icone de  hamburger para chamar menu
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Começa com a drawer aberta
       // drawer.openDrawer(GravityCompat.START);
        //drawer.setActivated(false);
        /*Definindo qual fragment para ser exibido primeiro
        MainFragment mainFragmnet = new MainFragment();
        android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, mainFragmnet, null);
        fragmentTransaction.commit();*/

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new MuralFragment()).commit();


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
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        // Associate searchable configuration with the SearchView
        //SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
     /*   final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        //searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnSearchClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.i("CLICK", "Click");

            }
        });*/

        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

      /*  if (id == R.id.action_search) {
            Log.i("falacia", "falacia");
        } */
        //noinspection SimplifiableIfStatement

        if (id == R.id.action_search_barcode) {

            //abrir intent pra app de scanning
            Intent intent = new Intent(this,TabbedActivity.class);
            startActivity(intent);

            return true;
        }


        return super.onOptionsItemSelected(item);
    }

        //Chamadas para o menu
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager fm = getFragmentManager();
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_perfil) {
            fm.beginTransaction().replace(R.id.content_frame, new Perfil()).commit();
        } else if (id == R.id.nav_cadprof) {
            fm.beginTransaction().replace(R.id.content_frame, new CadProfFragment()).commit();
        } else if (id == R.id.nav_mural) {
            fm.beginTransaction().replace(R.id.content_frame, new MuralFragment()).commit();

        } else if (id == R.id.nav_ajuda) {
            fm.beginTransaction().replace(R.id.content_frame, new Ajuda()).commit();

        } else if (id == R.id.nav_sobre) {
            fm.beginTransaction().replace(R.id.content_frame, new Sobre()).commit();

        } else if (id == R.id.nav_atendersol) {
            Intent intent = new Intent(MainActivity.this, AtenderSolicitacaoSwipe.class);
            startActivity(intent);

        } else if (id == R.id.nav_site) {
            Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
            myWebLink.setData(Uri.parse("http://casebeautty.yolasite.com"));
            startActivity(myWebLink);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void abreNovo(View view){
        Intent intent = new Intent(this,NovoActivity.class);
        startActivity(intent);
    }
}

