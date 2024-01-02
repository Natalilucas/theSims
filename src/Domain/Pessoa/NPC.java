package Domain.Pessoa;

import Domain.Pessoa.Pessoa;

public class NPC extends Pessoa {

    private int estatutoMinimo;

    /**
     * construtor do npc
     * @param nome
     * @param dinheiro
     * @param estatutoMinimo
     */
    public NPC(String nome, double dinheiro, int estatutoMinimo) {
        super(nome, dinheiro);
        this.estatutoMinimo = estatutoMinimo;
    }

    public int getEstatutoMinimo() {
        return estatutoMinimo;
    }

    /**
     * metodo comum a todas as classes do tipo pessoa para imprimir as informações
     */
    public void imprimirDetalhes(){
        System.out.println("Family member name: "+ this.getNome() + "\t" + " | Heritage: " + this.getDinheiro() + " | Status: " + this.estatutoMinimo);
    }

}


