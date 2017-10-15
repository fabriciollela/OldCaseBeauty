package com.caseb.case_beauty_;

import java.math.BigDecimal;
import java.text.ParseException;

public class ChildEntity {
    private Integer id;

    private String descricao;

    private BigDecimal valor;

    public ChildEntity() {
        // TODO Auto-generated constructor stub
    }

    public ChildEntity(int id, String descr, BigDecimal valor) {
        this.id = id;
        this.descricao = descr;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getValorFormatado() throws ParseException {
        // TODO Auto-generated method stub
        return this.getValor().toString();
    }
}