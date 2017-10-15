package com.caseb.case_beauty_;

/**
 * Created by evandro on 16/03/2015.
 */
public class _Default {
    protected String _mensagem;
    protected boolean _status;

    public _Default(){
        this._status = true;
        this._mensagem = "Usuário cadastrado com sucesso!";
    }

    public String get_mensagem() {
        return _mensagem;
    }

    public void set_mensagem(String _mensagem) {
        this._mensagem = _mensagem;
    }

    public boolean is_status() {
        return _status;
    }

    public void set_status(boolean _status) {
        this._status = _status;
    }
}
