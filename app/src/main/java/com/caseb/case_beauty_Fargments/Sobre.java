package com.caseb.case_beauty_Fargments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.caseb.case_beauty_.MainActivity;
import com.caseb.case_beauty_.R;

public class Sobre extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sobre,
                container, false);
        Button btnVersao = (Button) view.findViewById(R.id.btnVersao);
        btnVersao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Versão 1.0", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnCurtirPagFace = (Button) view.findViewById(R.id.btnCurtirPagFace);
        btnCurtirPagFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse("https://www.facebook.com/appCASEservice"));
                startActivity(myWebLink);
            }
        });
        Button btnAvaliarGP = (Button) view.findViewById(R.id.btnAvaliarGP);
        btnAvaliarGP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse("https://play.google.com/store"));
                startActivity(myWebLink);
            }
        });

        Button btnContribuir = (Button) view.findViewById(R.id.btnContribuir);
        btnContribuir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse("http://www.arrekade.com.br/desenvolvimentocase"));
                startActivity(myWebLink);
            }
        });
        return view;
    }
}

