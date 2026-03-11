package com.banco.service;

public interface OperacoesConta {

    void depositar(double valor);
    void sacar(double valor) throws Exception;
    void pagarMensalidade();
}
