package com.banco;

import com.banco.exception.SaldoInsuficienteException;
import com.banco.model.*;
import com.banco.service.Banco;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Banco banco = new Banco();

        int opcao;

        do {
            System.out.println("\n==== BANCO ====");
            System.out.println("1 - Criar conta");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Transferir");
            System.out.println("5 - Listar contas");
            System.out.println("6 - Fechar conta");
            System.out.println("0 - Sair");

            opcao = sc.nextInt();

            switch (opcao) {

                case 1:
                    System.out.println("Nome:");
                    String nome = sc.next();

                    Cliente cliente = new Cliente(nome, "000");

                    Conta conta = new Conta(banco.getContas().size() + 1, cliente, TipoConta.CORRENTE);

                    conta.abrirConta();
                    banco.adicionarConta(conta);

                    System.out.println("Conta criada!");
                    break;

                case 2:
                    System.out.println("Número da conta:");
                    int num = sc.nextInt();

                    Conta c = banco.buscarConta(num);

                    if (c != null){
                        System.out.println("Valor:");
                        double valor = sc.nextDouble();

                        c.depositar(valor);
                    }
                    break;

                case 3:
                    System.out.println("Número da conta:");
                    num = sc.nextInt();

                    c = banco.buscarConta(num);

                    if (c != null){
                        System.out.println("Valor:");
                        double valor = sc.nextDouble();

                        try {
                            c.sacar(valor);
                        } catch (SaldoInsuficienteException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;

                case 4:
                    System.out.println("Conta origem:");
                    int origem = sc.nextInt();

                    System.out.println("Conta destino:");
                    int destino = sc.nextInt();

                    Conta contaOrigem = banco.buscarConta(origem);
                    Conta contaDestino = banco.buscarConta(destino);

                    if (contaOrigem != null && contaDestino != null){
                        System.out.println("Valor:");
                        double valor = sc.nextDouble();

                        try {
                            contaOrigem.transferir(valor, contaDestino);
                        }catch (SaldoInsuficienteException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;

                case 5:
                    System.out.println("Número da conta:");
                    num = sc.nextInt();

                    c = banco.buscarConta(num);

                    if (c != null){
                        c.fecharConta();
                    }
                    break;

                case 6:
                    for (Conta contaListada : banco.getContas()){
                        System.out.println(contaListada);
                    }
                    break;
            }

        } while (opcao != 0);
    }
}