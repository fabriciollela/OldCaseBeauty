package com.caseb.case_beauty_;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.caseb.case_beauty_Fargments.AtenderSolicitacao;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

/**
 * Created by fabri on 28/05/2016.
 */

public class AtenderSolicitacaoSwipe extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {


    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listViewSolicitacao;

    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atender_swipe);


        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_atender_swipe);

        swipeRefreshLayout.setOnRefreshListener(this);

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        atualizaPag();
                                    }
                                }
        );
      /*  String[] itens = {"| Cliente | Seriço | Localização |",  "| ---------- | ---------- | ------------------- |", "| ---------- | ---------- | ------------------- |", "| ---------- | ---------- | ------------------- |", "| ---------- | ---------- | ------------------- |", "| ---------- | ---------- | ------------------- |"};






            ListView listView = (ListView) findViewById(R.id.listViewSolicitacao);
            ArrayAdapter<String> array = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, itens);
            listView.setAdapter(array);*/


        this.listViewSolicitacao = (ListView) findViewById(R.id.listViewSolicitacao);
        this.listViewSolicitacao.setAdapter(new SolicitacaoAdapter(this,new Solicitacao().getLista()));

            Button btnEfetuarAtendimento = (Button) findViewById(R.id.btnEfetuarAtendimento);
            btnEfetuarAtendimento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Resposta enviada, encaminhe-se até a localização do cliente", Toast.LENGTH_SHORT).show();
                }
            });

    }

    /**
     * This method is called when swipe refresh is pulled down
     */
    @Override
    public void onRefresh() {
        atualizaPag();
    }

    /**
     * Fetching movies json by making http call
     */
    private void atualizaPag() {

        // showing refresh animation before making http call
        swipeRefreshLayout.setRefreshing(true);

        swipeRefreshLayout.setRefreshing(false);

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


    public void verDetalhes(View view){
        //Lista de itens
        ArrayList<String> itens = new ArrayList<String>();
        itens.add("         Nome: Janaina de Almeida Alves");
        itens.add("        Login: janaina.al");
        itens.add("        Email: janaina.alves@hotmail.com");
        itens.add("Telefone Fixo: (61)3473-3266\n");
        itens.add("      Celular: (61)983154678");
        itens.add("       Cidade: Brasilia");
        itens.add("       Bairro: Recanto das Emas");
        itens.add("     Serviços: Luzes, Corte Feminino, Hidratação");

        //adapter utilizando um layout customizado (TextView)
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.item_alerta, itens);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Detalhes do serviço");
        //define o diálogo como uma lista, passa o adapter.
        builder.setSingleChoiceItems(adapter, 0, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
              //  Toast.makeText(MainActivity.this, "posição selecionada=" + arg1, Toast.LENGTH_SHORT).show();
                alerta.dismiss();
            }
        });


        alerta = builder.create();
        alerta.show();
    }
    public void recusaAtendimento(View view) {
        Toast.makeText(this,"Atendimento Recusado", Toast.LENGTH_LONG).show();
    }
}
