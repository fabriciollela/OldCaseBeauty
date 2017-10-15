package com.caseb.case_beauty_;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by evandro on 02/02/2015.
 */
public class HistoricoAdapter extends ArrayAdapter<Historico> {
    private Context context;
    private ArrayList<Historico> lista;

    public HistoricoAdapter(Context context, ArrayList<Historico> lista) {
        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Historico itemPosicao = this.lista.get(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.item_lista_his, null);
        final View layout = convertView;

        TextView textView = (TextView) convertView.findViewById(R.id.textViewPrestador);
        textView.setText(itemPosicao.getPrestador());

        TextView textViewServicos = (TextView) convertView.findViewById(R.id.textViewServicos);
        textViewServicos.setText(itemPosicao.getServico());


        TextView textViewData = (TextView) convertView.findViewById(R.id.textViewData);
        textViewData.setText(itemPosicao.getData());

        TextView textViewStatus = (TextView) convertView.findViewById(R.id.textViewStatus);
        textViewStatus.setText(itemPosicao.getStatus());

        ImageButton btnVerLocalizacao = (ImageButton) convertView.findViewById(R.id.btnVerLocalizacao);
        btnVerLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] { "appcaseservice@gmail.com" });
                context.startActivity(intent);
            }
        });

        return convertView;
    }

}
