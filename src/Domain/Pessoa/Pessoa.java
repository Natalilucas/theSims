package Domain.Pessoa;

import Domain.Propriedade.Propriedade;

import java.util.ArrayList;

public class Pessoa {
    private String nome;
    private double dinheiro;

    private ArrayList<Propriedade> minhasPropriedades = new ArrayList<>();

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

    public ArrayList<Propriedade> getMinhasPropriedades() {
        return minhasPropriedades;
    }

    public void setMinhasPropriedades(ArrayList<Propriedade> minhasPropriedades) {
        this.minhasPropriedades = minhasPropriedades;
    }

    public void addAPropriedade(Propriedade novaPropriedade){
        this.minhasPropriedades.add(novaPropriedade);
        System.out.println("Propriedade adicionada!!! ");
        for (Propriedade novasPropriedades: minhasPropriedades) {
            System.out.println(novasPropriedades);
        }
    }
}
