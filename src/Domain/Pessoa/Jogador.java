package Domain.Pessoa;

import Domain.Objetivo;
import Domain.Propriedade.Propriedade;

import java.sql.DriverPropertyInfo;
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
        System.out.println("Seu jogador: " + this.nome);
        System.out.println(" | Saldo bancário:" + this.dinheiro);
        profissaoAtual.imprimirDetalhes();
        System.out.println(" | Necessidade de Sono: " + this.necessidadeSono);
        System.out.println(" | Necessidade de Refeição:" + this.necessidadeRefeicao);
        System.out.println(" | Necessidade Social: " + this.necessidadeSocial);
        System.out.println(" | Estatuto: " + this.estatuto );
        System.out.println(" | Educação: " + this.educacao);

        for(Propriedade propriedadeAtual: propriedades ){
            propriedadeAtual.imprimirDetalhes();
        }

        for(NPC npc: familia){
            npc.imprimirDetalhes();
        }




    }
}
