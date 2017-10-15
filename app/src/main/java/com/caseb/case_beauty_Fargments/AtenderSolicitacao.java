package com.caseb.case_beauty_Fargments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.caseb.case_beauty_.R;

import java.util.ArrayList;
import java.util.List;

public class AtenderSolicitacao extends Fragment {
    //ListView listView;
    String[] itens = {"| Cliente | Seriço | Localização |",  "| ---------- | ---------- | ------------------- |", "| ---------- | ---------- | ------------------- |", "| ---------- | ---------- | ------------------- |", "| ---------- | ---------- | ------------------- |", "| ---------- | ---------- | ------------------- |"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_atender_solicitacao,
                container, false);


        ListView listView = (ListView) view.findViewById(R.id.listViewSolicitacao);
        ArrayAdapter<String> array = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_checked, itens);
        listView.setAdapter(array);

        Button btnEfetuarAtendimento = (Button) view.findViewById(R.id.btnEfetuarAtendimento);
        btnEfetuarAtendimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Resposta enviada, encaminhe-se até a localização do cliente", Toast.LENGTH_SHORT).show();
            }
        });
        Button btnRecusarAtendimento = (Button) view.findViewById(R.id.btnRecusarAtendimento);
        btnRecusarAtendimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Solicitação recusada", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}

