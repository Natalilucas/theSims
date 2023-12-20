package Domain.Pessoa;

import Domain.Propriedade.Propriedade;

import java.util.ArrayList;

public class Pessoa {
    protected String nome;
    protected double dinheiro;


    public Pessoa(String nome, double dinheiro) {
        this.nome = nome;
        this.dinheiro = dinheiro;
    }

    public String getNome() {
        return nome;
    }

    public double getDinheiro() {
        return dinheiro;
    }


}
