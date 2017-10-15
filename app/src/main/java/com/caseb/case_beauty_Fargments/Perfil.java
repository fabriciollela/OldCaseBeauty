package com.caseb.case_beauty_Fargments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.caseb.case_beauty_.R;

public class Perfil extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil,
                container, false);
        Button btnSalvarPerfil = (Button) view.findViewById(R.id.btnSalvarPerfilR);
        btnSalvarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Alterações realizadas com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
