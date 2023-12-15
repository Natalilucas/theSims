package Domain.Pessoa;

import Domain.Objetivo;
import Domain.Propriedade.Propriedade;

import java.util.ArrayList;

public class Jogador extends Pessoa {

    private Objetivo objetivoVida;
    private Profissao profissaoAtual;
    private int necessidadeSono;
    private int necessidadeRefeicao;
    private int necessidadeSocial;
    private int estatuto;
    private int educacao;
    private ArrayList<Propriedade> propriedades;
    private ArrayList<NPC> familia;

    public Jogador(String nome, double dinheiro, Objetivo objetivoVida, Profissao profissaoAtual, int necessidadeSono, int necessidadeRefeicao, int necessidadeSocial, int estatuto, int educacao, ArrayList<Propriedade> propriedades, ArrayList<NPC> familia) {
        super(nome, dinheiro);
        this.objetivoVida = objetivoVida;
        this.profissaoAtual = profissaoAtual;
        this.necessidadeSono = necessidadeSono;
        this.necessidadeRefeicao = necessidadeRefeicao;
        this.necessidadeSocial = necessidadeSocial;
        this.estatuto = estatuto;
        this.educacao = educacao;
        this.propriedades = propriedades;
        this.familia = familia;
    }

    public void mostrarDetalhes(){
        System.out.println("Seu jogador: " + this.getNome()+ " \t Saldo bancário: " + this.getDinheiro() + " \t Profissão: " + this.profissaoAtual +
                " \t Necessidade de Sono: " + this.necessidadeSono + " \t Necessidade de Refeição: " + this.necessidadeRefeicao + " \t Necessidade Social: " + this.necessidadeSocial
                + " \t Estatuto: " + this.estatuto + " \t Educação: " + this.educacao);

        //Fazer a função para trazer o array list.
        System.out.println(" \t Profissão: " + this.propriedades + " \t Profissão: " + this.familia );

    }
}
