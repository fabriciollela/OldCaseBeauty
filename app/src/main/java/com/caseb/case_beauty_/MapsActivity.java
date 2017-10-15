package com.caseb.case_beauty_;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.caseb.case_beauty_Fargments.Maps;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends AppCompatActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    //  ExpandableListAdapter listAdapter;
    ExpListViewAdapterWithCheckbox listAdapter;
    ExpandableListView expListView;
    ArrayList<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();


        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpListViewAdapterWithCheckbox(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                //   Toast.makeText(getApplicationContext(),
                //       listDataHeader.get(groupPosition) + " Aberto",
                //  Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                //  Toast.makeText(getApplicationContext(),
                //          listDataHeader.get(groupPosition) + " Fechado",
                //          Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });

       /* Button button = (Button) findViewById(R.id.button);
        final TextView textView = (TextView) findViewById(R.id.textView);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int count = 0;
                for (int mGroupPosition = 0; mGroupPosition < listAdapter.getGroupCount(); mGroupPosition++) {
                    count = count + listAdapter.getNumberOfCheckedItemsInGroup(mGroupPosition);
                }
                textView.setText("" + count);
            }
        });*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }


    public void onSearch(View view) {
        EditText location_tf = (EditText) findViewById(R.id.TFaddress);
        String location = location_tf.getText().toString();
        List<Address> addressList = null;
        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);


            } catch (IOException e) {
                e.printStackTrace();
            }

            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title("Estou Aqui"));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

        }
    }

    public void onZoom(View view) {
        if (view.getId() == R.id.Bzoomin) {
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        }
        if (view.getId() == R.id.Bzoomout) {
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
        }
    }

    public void changeType(View view) {
        if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL) {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        } else
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(-15.843937, -47.915775)).title("Estou Aqui"));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-15.843937, -47.915775)).zoom(12).build();
        mMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);


    }


    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Cabelo");
        listDataHeader.add("Manicure Pedicure");
        listDataHeader.add("Depilação");
        listDataHeader.add("Sobrancelha");
        listDataHeader.add("Estética");

        // Adding child data
        List<String> cabelo = new ArrayList<String>();
        cabelo.add("Escova");
        cabelo.add("Progressiva");
        cabelo.add("Corte Masculino");
        cabelo.add("Corte Feminino");
        cabelo.add("Tintura");
        cabelo.add("Luzes");
        cabelo.add("Relaxamento");
        cabelo.add("Hidratação");

        List<String> manicurePedicure = new ArrayList<String>();
        manicurePedicure.add("Mão");
        manicurePedicure.add("Mão Francesinha");
        manicurePedicure.add("Pé");
        manicurePedicure.add("Pé Francesinha");
        manicurePedicure.add("Manicure Homens");
        manicurePedicure.add("Pedicure Homens");

        List<String> depilacao = new ArrayList<String>();
        depilacao.add("Axilas");
        depilacao.add("Buço");
        depilacao.add("Contorno");
        depilacao.add("Meia Perna");
        depilacao.add("Perna inteira");
        depilacao.add("Barba");

        List<String> sobrancelha = new ArrayList<String>();
        sobrancelha.add("Sobrancelha");
        sobrancelha.add("Sobrancelha com Rena");

        List<String> estetica = new ArrayList<String>();
        estetica.add("Massagem");
        estetica.add("Limpeza de Pele");
        estetica.add("Hidratação Facial");
        estetica.add("Massagem Modeladora");
        estetica.add("Esfoliação Corporal");
        estetica.add("Banho de Lua");
        estetica.add("Maquiagem");

        listDataChild.put(listDataHeader.get(0), cabelo); // Header, Child data
        listDataChild.put(listDataHeader.get(1), manicurePedicure);
        listDataChild.put(listDataHeader.get(2), depilacao);
        listDataChild.put(listDataHeader.get(3), sobrancelha);
        listDataChild.put(listDataHeader.get(4), estetica);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
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
            case R.id.action_search_barcode:
                startActivity(new Intent(this, TabbedActivity.class));
                return true;

/*
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

    public void solicitar(View view) {
        //the first thing you need to to is to initialize the progressDialog Class like this

        final ProgressDialog progressBarDialog = new ProgressDialog(this);

//set the icon, title and progress style..

        progressBarDialog.setIcon(R.drawable.ic_aguarde_busca);

        progressBarDialog.setTitle("Procurando um prestador mais próximo...");

        progressBarDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);


        //setting the OK Button
        progressBarDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,
                                int whichButton) {
                Toast.makeText(getBaseContext(),
                        "Serviço solicitado!", Toast.LENGTH_SHORT).show();
            }
        });

        //set the Cancel button
        progressBarDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
              //  Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
            }
        });
//initialize the dialog..
        progressBarDialog.setProgress(0);

//setup a thread for long running processes
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i <= 15; i++) {
                    try {
                        Thread.sleep(1000);
                        progressBarDialog.incrementProgressBy((int) (5));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //dismiss the dialog
                progressBarDialog.dismiss();
            }
        });

//show the dialog
        progressBarDialog.show();
    }
    public void estimarValor(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Valor estimado dos serviços:");
        alertDialogBuilder.setMessage("R$ 85,00");

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
              //  Toast.makeText(MainActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
            }
        });


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
