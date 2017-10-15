package com.caseb.case_beauty_;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by evandro on 02/02/2015.
 */
public class SolicitacaoAdapter extends ArrayAdapter<Solicitacao> {
    private Context context;
    private ArrayList<Solicitacao> lista;

    public SolicitacaoAdapter(Context context, ArrayList<Solicitacao> lista) {
        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Solicitacao itemPosicao = this.lista.get(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.item_lista_sol, null);
        final View layout = convertView;

        TextView textView = (TextView) convertView.findViewById(R.id.textViewNomeCliente);
        textView.setText(itemPosicao.getNomeCliente());

        TextView textViewEmail = (TextView) convertView.findViewById(R.id.textViewServicos);
        textViewEmail.setText(itemPosicao.getServico());

        ImageButton btnVerLocalizacao = (ImageButton) convertView.findViewById(R.id.btnVerLocalizacao);
        btnVerLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double latitude = -16.014526;
                double longitude = -47.992855;
                Intent myWebLink = new Intent(Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse("google.navigation:q=" + latitude + "," + longitude));
                context.startActivity(myWebLink);
            }
        });

        return convertView;
    }

}
