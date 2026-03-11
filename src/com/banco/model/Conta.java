package com.banco.model;

import com.banco.exception.SaldoInsuficienteException;
import com.banco.service.OperacoesConta;

public class Conta implements OperacoesConta {

    private int numero;
    private Cliente cliente;
    private TipoConta tipo;
    private double saldo;
    private boolean ativa;

    public Conta(int numero, Cliente cliente, TipoConta tipo){

         this.numero = numero;
         this.cliente = cliente;
         this.tipo = tipo;
         this.saldo = 0;
         this.ativa = false;
    }

    public void abrirConta(){
         ativa = true;

         if(tipo == TipoConta.CORRENTE){
             saldo += 50;
         }else{
             saldo += 150;
         }
    }

    public void fecharConta(){
        if (saldo > 0){
            System.out.println("Conta possui saldo. Saque o dinheiro antes de fechar");
        } else if (saldo < 0) {
            System.out.println("Conta está em debito. Quite a divida antes de fechar");
        }else {
            ativa = false;
            System.out.println("Conta fechada com sucesso");
        }
    }

    public void transferir(double valor, Conta destino) throws SaldoInsuficienteException{

        this.sacar(valor);
        destino.depositar(valor);
    }

    @Override
    public void depositar(double valor){
        if(ativa && valor > 0){
             saldo += valor;
        }
    }

    @Override
    public void sacar(double valor) throws SaldoInsuficienteException{
        if(!ativa){
            System.out.println("Conta fechada");
            return;
        }
        if (saldo < valor){
            throw new SaldoInsuficienteException("Saldo insuficiente!");
        }

        saldo -= valor;
    }

    @Override
    public void pagarMensalidade(){

        int valor;

        if (tipo == TipoConta.CORRENTE){
            valor = 12;
        }else{
            valor = 20;
        }

        if (saldo >= valor){
            saldo -= valor;
        }
    }

    public int getNumero(){
        return numero;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "numero=" + numero +
                ", cliente=" + cliente.getNome() +
                ", tipo=" + tipo +
                ", saldo=" + saldo +
                '}';
    }
}
