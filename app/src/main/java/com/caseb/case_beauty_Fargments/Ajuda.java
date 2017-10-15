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
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.caseb.case_beauty_.Historico;
import com.caseb.case_beauty_.HistoricoAdapter;
import com.caseb.case_beauty_.R;
import com.caseb.case_beauty_.Solicitacao;
import com.caseb.case_beauty_.SolicitacaoAdapter;

public class Ajuda extends Fragment {
    //ListView listView;  - exemplo
   // String[] itens = {"| Data | Seriços | Atendido por: |",  "| -------  | ------------ |  -------------------- |","| -------  | ------------ |  -------------------- |", "| -------  | ------------ |  -------------------- |", "| -------  | ------------ |  -------------------- |", "| -------  | ------------ |  -------------------- |"};
    private ListView listViewAjuda;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ajuda,
                container, false);

        /*ListView listView = (ListView) view.findViewById(R.id.listViewAjuda);
        ArrayAdapter<String> array = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_checked, itens);
        listView.setAdapter(array);*/

        this.listViewAjuda = (ListView) view.findViewById(R.id.listViewAjuda);
        this.listViewAjuda.setAdapter(new HistoricoAdapter(getActivity(),new Historico().getLista()));

        Button btnPagamento = (Button) view.findViewById(R.id.btnPagamento);
        btnPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Todos os trâmites monetário são de responsabilidade do solicitante e do prestador de serviços.", Toast.LENGTH_LONG).show();
            }
        });
       /* Button btnComoUsar = (Button) view.findViewById(R.id.btnComoUsar);
        btnComoUsar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse("https://pt.wikipedia.org/wiki/C.A.S.E._-_Beauty"));
                startActivity(myWebLink);
            }
        });*/

        Button botaoPesquisa = (Button) view.findViewById(R.id.btnComoUsar);
        botaoPesquisa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                shouldOverrideUrlLoading();
            }
        });

        return view;
    }
    private void shouldOverrideUrlLoading(){
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Manual do C.A.S.E.");

        WebView wv = new WebView(getActivity());
        wv.loadUrl("http://casebeautty.yolasite.com/Manual.php");
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }
        });

        alert.setView(wv);
        alert.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        alert.setPositiveButton("Abrir no Navegador", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse("http://casebeautty.yolasite.com/Manual.php"));
                startActivity(myWebLink);
            }
        });
        alert.show();

    }
}