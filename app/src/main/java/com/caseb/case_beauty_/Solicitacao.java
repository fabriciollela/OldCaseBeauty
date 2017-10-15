package com.caseb.case_beauty_;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by evandro on 16/03/2015.
 */
public class Solicitacao extends _Default {

    private int id;
    private String nome_cliente;
    private String servico;


    public Solicitacao() {
        super();
        this.id = -1;
        this.nome_cliente = "";
        this.servico = "";

    }

    public ArrayList<Solicitacao> getLista() {
        DB db = new DB();
        ArrayList<Solicitacao> lista = new ArrayList<>();
        try {
            ResultSet resultSet = db.select("SELECT * FROM solicitacao");
            if (resultSet != null) {
                while (resultSet.next()) {
                    Solicitacao obj = new Solicitacao();
                    obj.setId(resultSet.getInt("id"));
                    obj.setNomeCliente(resultSet.getString("nome_cliente"));
                    obj.setServico(resultSet.getString("servico"));
                    lista.add(obj);
                    obj = null;
                }
            }
        } catch (Exception ex) {
            this._mensagem = ex.getMessage();
            this._status = false;
        }
        return lista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nome_cliente;
    }

    public void setNomeCliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }


}
