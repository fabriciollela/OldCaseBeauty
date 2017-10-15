package com.caseb.case_beauty_;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by evandro on 16/03/2015.
 */
public class Usuario extends _Default {

    private int id;
    private String nome;
    private String login;
    private String email;
    private String telefone_fixo;
    private String celular;
    private String senha;


    public Usuario() {
        super();
        this.id = -1;
        this.nome = "";
        this.login = "";
        this.email = "";
        this.telefone_fixo = "";
        this.celular = "";
        this.senha = "";
    }

    public ArrayList<Usuario> getLista() {
        DB db = new DB();
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            ResultSet resultSet = db.select("SELECT * FROM usuario");
            if (resultSet != null) {
                while (resultSet.next()) {
                    Usuario obj = new Usuario();
                    obj.setId(resultSet.getInt("id"));
                    obj.setNome(resultSet.getString("nome"));
                    obj.setLogin(resultSet.getString("login"));
                    obj.setEmail(resultSet.getString("email"));
                    obj.setTelefone_fixo(resultSet.getString("telefone_fixo"));
                    obj.setCelular(resultSet.getString("celular"));
                    obj.setSenha(resultSet.getString("senha"));
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



    public void salvar() {
        String comando = "";
        if (this.getId() == -1) {
            comando = String.format("INSERT INTO usuario (nome, login, email, telefone_fixo, celular, senha) VALUES ('%s','%s','%s','%s','%s','%s');",
                    this.getNome(), this.getLogin(), this.getEmail(), this.getTelefone_fixo(), this.getCelular(), this.getSenha());
        } else {
            comando = String.format("UPDATE usuario SET nome = '%s', login = '%s', email = '%s', telefone_fixo = '%s', celular = '%s', senha = '%s' WHERE id = %d;",
                    this.getNome(), this.getLogin(), this.getEmail(), this.getTelefone_fixo(), this.getCelular(), this.getSenha(), this.getId());
        }
        DB db = new DB();
        db.execute(comando);
        this._mensagem = db._mensagem;
        this._status = db._status;
    }

    public void apagar() {
        String comando = String.format("DELETE FROM usuario WHERE id = %d;", this.getId());
        DB db = new DB();
        db.execute(comando);
        this._mensagem = db._mensagem;
        this._status = db._status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefone_fixo() {
        return telefone_fixo;
    }

    public void setTelefone_fixo(String telefone_fixo) {
        this.telefone_fixo = telefone_fixo;
    }
    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
