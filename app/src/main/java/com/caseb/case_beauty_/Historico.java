package com.caseb.case_beauty_;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by evandro on 16/03/2015.
 */
public class Historico extends _Default {

    private int id;
    private String prestador;
    private String servico;
    private String data;
    private String status;


    public Historico() {
        super();
        this.id = -1;
        this.data = "";
        this.prestador = "";
        this.servico = "";
        this.status = "";

    }

    public ArrayList<Historico> getLista() {
        DB db = new DB();
        ArrayList<Historico> lista = new ArrayList<>();
        try {
            ResultSet resultSet = db.select("SELECT * FROM solicitacao");
            if (resultSet != null) {
                while (resultSet.next()) {
                    Historico obj = new Historico();
                    obj.setId(resultSet.getInt("id"));
                    obj.setPrestador(resultSet.getString("prestador"));
                    obj.setServico(resultSet.getString("servico"));
                    obj.setData(resultSet.getString("data"));
                    obj.setStatus(resultSet.getString("status"));
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

    public String getPrestador() {
        return prestador;
    }

    public void setPrestador(String prestador) {
        this.prestador = prestador;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
