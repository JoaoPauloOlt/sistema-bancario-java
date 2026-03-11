package com.banco.service;

import com.banco.model.Conta;
import java.util.ArrayList;
import java.util.List;

public class Banco {

    private List<Conta> contas = new ArrayList<>();

    public void adicionarConta(Conta conta){
        contas.add(conta);
    }

    public List<Conta> getContas(){
        return contas;
    }

    public Conta buscarConta(int numero){
        for (Conta conta : contas){
            if (conta.getNumero() == numero){
                return conta;
            }
        }
        return null;
    }
}
