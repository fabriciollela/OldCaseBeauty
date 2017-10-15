package com.caseb.case_beauty_;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EsqueceSenha extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esquece_senha);


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_novo, menu);
        // Action View
        //MenuItem searchItem = menu.findItem(R.id.action_search);
        //SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        // Configure the search info and add any event listeners
        //return super.onCreateOptionsMenu(menu);
        return true;

    }

    // Determines if Action bar item was selected. If true then do corresponding action.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //handle presses on the action bar items
        switch (item.getItemId()) {

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            /*
            case R.id.action_search_barcode:
                startActivity(new Intent(this, TabbedActivity.class));
                return true;

            case R.id.action_add_current_contact:
                startActivity(new Intent(this, ContactsView.class));
                return true;
            //TODO change icon

            case R.id.action_my_contacts:
                startActivity(new Intent(this, ContactsView.class));
                return true;



            case R.id.action_settings:
                startActivity(new Intent(this, MobileAds.Settings.class));
                return true;
            */
        }
        return super.onOptionsItemSelected(item);
    }
}
