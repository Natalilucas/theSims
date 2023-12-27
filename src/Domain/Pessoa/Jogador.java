package Domain.Pessoa;

import Domain.Enum.Objetivo;
import Domain.Propriedade.Propriedade;

import java.util.ArrayList;

public class Jogador extends Pessoa {

    protected Objetivo objetivoVida;
    protected Profissao profissaoAtual;
    private int necessidadeSono;
    private int necessidadeRefeicao;
    private int necessidadeSocial;
    private int estatuto;
    private int educacao;
    private ArrayList<Propriedade> propriedades;
    private ArrayList<NPC> familia;

    public Jogador(String nome, double dinheiro, Objetivo objetivoVida, Profissao profissaoAtual, int necessidadeSono, int necessidadeRefeicao, int necessidadeSocial, int estatuto, int educacao) {
        super(nome, dinheiro);
        this.objetivoVida = objetivoVida;
        this.profissaoAtual = profissaoAtual;
        this.necessidadeSono = necessidadeSono;
        this.necessidadeRefeicao = necessidadeRefeicao;
        this.necessidadeSocial = necessidadeSocial;
        this.estatuto = estatuto;
        this.educacao = educacao;
        this.propriedades = new ArrayList<>();
        this.familia = new ArrayList<>();
    }

    public Objetivo getObjetivoVida() {
        return objetivoVida;
    }

    public Profissao getProfissaoAtual() {
        return profissaoAtual;
    }

    public int getNecessidadeSono() {
        return necessidadeSono;
    }

    public int getNecessidadeRefeicao() {
        return necessidadeRefeicao;
    }

    public int getNecessidadeSocial() {
        return necessidadeSocial;
    }

    public int getEstatuto() {
        return estatuto;
    }

    public int getEducacao() {
        return educacao;
    }

    public ArrayList<Propriedade> getPropriedades() {
        return propriedades;
    }

    public void addAPropriedade(Propriedade novaPropriedade) {
        this.propriedades.add(novaPropriedade);
    }


    public void mostrarDetalhes() {
        System.out.println("Your player: " + this.nome);
        System.out.println(" | Golden bank:" + this.dinheiro);
        profissaoAtual.imprimirDetalhes();
        System.out.println(" | Necessidade de Sono: " + this.necessidadeSono);
        System.out.println(" | Necessidade de Refeição:" + this.necessidadeRefeicao);
        System.out.println(" | Necessidade Social: " + this.necessidadeSocial);
        System.out.println(" | Estatuto: " + this.estatuto);
        System.out.println(" | Educação: " + this.educacao);

        for (Propriedade propriedadeAtual : propriedades) {
            propriedadeAtual.imprimirDetalhes();
        }

        for (NPC npc : familia) {
            npc.imprimirDetalhes();
        }


    }

    public void setObjetivoVida(Objetivo objetivoVida) {
        this.objetivoVida = objetivoVida;
    }

    public void setProfissaoAtual(Profissao profissaoAtual) {
        this.profissaoAtual = profissaoAtual;
    }

    public void setNecessidadeSono(int necessidadeSono) {
        this.necessidadeSono = necessidadeSono;
    }

    public void setNecessidadeRefeicao(int necessidadeRefeicao) {
        this.necessidadeRefeicao = necessidadeRefeicao;
    }

    public void setNecessidadeSocial(int necessidadeSocial) {
        this.necessidadeSocial = necessidadeSocial;
    }

    public void setEstatuto(int estatuto) {
        this.estatuto = estatuto;
    }

    public void setEducacao(int educacao) {
        this.educacao = educacao;
    }

    public void setPropriedades(ArrayList<Propriedade> propriedades) {
        this.propriedades = propriedades;
    }

    public void setFamilia(ArrayList<NPC> familia) {
        this.familia = familia;
    }
}
