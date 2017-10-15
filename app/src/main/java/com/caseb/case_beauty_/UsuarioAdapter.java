package com.caseb.case_beauty_;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by evandro on 02/02/2015.
 */
public class UsuarioAdapter extends ArrayAdapter<Usuario> {
    private Context context;
    private ArrayList<Usuario> lista;

    public UsuarioAdapter(Context context, ArrayList<Usuario> lista) {
        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Usuario itemPosicao = this.lista.get(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.item_lista, null);
        final View layout = convertView;

        TextView textViewNome = (TextView) convertView.findViewById(R.id.textViewNome);
        textViewNome.setText(itemPosicao.getNome());

        TextView textViewLogin = (TextView) convertView.findViewById(R.id.textViewLogin);
        textViewLogin.setText(itemPosicao.getLogin());

        TextView textViewEmail = (TextView) convertView.findViewById(R.id.textViewEmail);
        textViewEmail.setText(itemPosicao.getEmail());

        TextView textViewTelFixo = (TextView) convertView.findViewById(R.id.textViewTelFixo);
        textViewTelFixo.setText(itemPosicao.getTelefone_fixo());

        TextView textViewTelCel = (TextView) convertView.findViewById(R.id.textViewTelCel);
        textViewTelCel.setText(itemPosicao.getCelular());

        TextView textViewSenha = (TextView) convertView.findViewById(R.id.textViewSenha);
        textViewSenha.setText(itemPosicao.getSenha());


        Button button = (Button) convertView.findViewById(R.id.buttonEditar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Registrar.class);
                intent.putExtra("nome", itemPosicao.getNome());
                intent.putExtra("login", itemPosicao.getLogin());
                intent.putExtra("email", itemPosicao.getEmail());
                intent.putExtra("telefone_fixo", itemPosicao.getTelefone_fixo());
                intent.putExtra("celular", itemPosicao.getCelular());
                intent.putExtra("senha", itemPosicao.getSenha());
                intent.putExtra("id", itemPosicao.getId());
                context.startActivity(intent);
            }
        });
        Button buttonEditar = (Button) convertView.findViewById(R.id.buttonEditar);
        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NovoActivity.class);
                intent.putExtra("nome", itemPosicao.getNome());
                intent.putExtra("login", itemPosicao.getLogin());
                intent.putExtra("email", itemPosicao.getEmail());
                intent.putExtra("telefone_fixo", itemPosicao.getTelefone_fixo());
                intent.putExtra("celular", itemPosicao.getCelular());
                intent.putExtra("senha", itemPosicao.getSenha());
                intent.putExtra("id", itemPosicao.getId());
                context.startActivity(intent);
            }
        });

        Button buttonDeletar = (Button) convertView.findViewById(R.id.buttonApagar);
        buttonDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemPosicao.apagar();
                if (itemPosicao._status)
                    layout.setVisibility(View.GONE);
                else
                    Toast.makeText(context, itemPosicao.get_mensagem(), Toast.LENGTH_LONG).show();
            }
        });


        return layout;
    }
}
