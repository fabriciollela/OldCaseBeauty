package com.caseb.case_beauty_Fargments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.caseb.case_beauty_.R;

public class CadProfFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_cadprof, container, false);

        String[] values =
                {"UF","AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PR","PB","PA","PE","PI","RJ","RN","RS","RO","RR","SC","SE","SP","TO",};
        Spinner spinner = (Spinner) v.findViewById(R.id.spinnerUF);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        String[] values2 =
                {"Experiência Média","Menos que um ano","1 ano","2 anos","3 Anos","4 Anos","5 Anos","Mais que 5 anos",};
        Spinner spinner2 = (Spinner) v.findViewById(R.id.spinnerExpMedia);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values2);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(adapter2);

        Button buttonSalvarPerfilProf = (Button) v.findViewById(R.id.buttonSalvarPerfilProf);
        buttonSalvarPerfilProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Cadastro atualizado com sucesso! ", Toast.LENGTH_SHORT).show();
            }
        });

        return v;

    }


}

