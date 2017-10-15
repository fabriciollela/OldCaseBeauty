package com.caseb.case_beauty_Fargments;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.caseb.case_beauty_.R;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    private static final String URL = "http://timespender.com/";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main,
                container, false);

        List<String> listItems = new ArrayList<String>();

        listItems.add("---- Cabelo ----");
        listItems.add("Escova");
        listItems.add("Progressiva");
        listItems.add("Corte Masculino");
        listItems.add("Corte Feminino");
        listItems.add("Tintura");
        listItems.add("Luzes");
        listItems.add("Relaxamento");
        listItems.add("Hidratação");
        listItems.add("---- Manicure e Pedicure ----");
        listItems.add("Mão");
        listItems.add("Mão Francesinha");
        listItems.add("Pé");
        listItems.add("Pé Francesinha");
        listItems.add("Manicure Homens");
        listItems.add("Pedicure Homens");
        listItems.add("---- Depilação ----");
        listItems.add("Axilas");
        listItems.add("Buço");
        listItems.add("Contorno");
        listItems.add("½ Perna");
        listItems.add("Perna inteira");
        listItems.add("Barba");
        listItems.add("---- Sobrancelha ----");
        listItems.add("Sobrancelha");
        listItems.add("Sobrancelha com Rena");
        listItems.add("---- Estética ----");
        listItems.add("Massagem");
        listItems.add("Limpeza de Pele");
        listItems.add("Hidratação Facial");
        listItems.add("Massagem Modeladora");
        listItems.add("Esfoliação Corporal");
        listItems.add("Banho de Lua");

        final CharSequence[] list = listItems.toArray(new CharSequence[listItems.size()]);


        View openDialog = (View) view.findViewById(R.id.openDialog);
        openDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Intialize  readable sequence of char values
                final CharSequence[] dialogList = list;
                final AlertDialog.Builder builderDialog = new AlertDialog.Builder(getActivity());
                builderDialog.setTitle("Select Item");
                int count = dialogList.length;
                boolean[] is_checked = new boolean[count];

                // Creating multiple selection by using setMutliChoiceItem method
                builderDialog.setMultiChoiceItems(dialogList, is_checked,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton, boolean isChecked) {
                            }
                        });

                builderDialog.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                ListView list = ((AlertDialog) dialog).getListView();
                                // make selected item in the comma seprated string
                                StringBuilder stringBuilder = new StringBuilder();
                                for (int i = 0; i < list.getCount(); i++) {
                                    boolean checked = list.isItemChecked(i);

                                    if (checked) {
                                        if (stringBuilder.length() > 0) stringBuilder.append(",");
                                        stringBuilder.append(list.getItemAtPosition(i));


                                    }
                                }

                        /*Check string builder is empty or not. If string builder is not empty.
                          It will display on the screen.
                         */
                                if (stringBuilder.toString().trim().equals("")) {

                                    ((TextView) view.findViewById(R.id.text)).setText("Selecionar Serviços");
                                    stringBuilder.setLength(0);

                                } else {

                                    ((TextView) view.findViewById(R.id.text)).setText(stringBuilder);
                                }
                            }
                        });

                builderDialog.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ((TextView) view.findViewById(R.id.text)).setText("Selecionar Serviços");
                            }
                        });
                AlertDialog alert = builderDialog.create();
                alert.show();

            }
        });

        Button btnEnviaSolicita = (Button) view.findViewById(R.id.btnEnviaSolicita);
        btnEnviaSolicita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Serviço solicitado, aguarde a resposta do prestador de serviços mais próximo. ", Toast.LENGTH_LONG).show();
            }
        });

        //Mapa

        Button botaoPesquisa = (Button) view.findViewById(R.id.Btype);
        botaoPesquisa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                irParaSite();
            }
        });

        return view;
    }

    private void irParaSite() {
        Uri uriVideo = Uri.parse(URL);
        Intent intent = new Intent(Intent.ACTION_VIEW, uriVideo);
        startActivity(intent);

    }
}

