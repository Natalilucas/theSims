package Domain.Pessoa;

import Domain.Pessoa.Pessoa;

public class NPC extends Pessoa {

    private int estatutoMinimo;

    public NPC(String nome, double dinheiro, int estatutoMinimo) {
        super(nome, dinheiro);
        this.estatutoMinimo = estatutoMinimo;
    }

    public int getEstatutoMinimo() {
        return estatutoMinimo;
    }

    public void imprimirDetalhes(){
        System.out.println("Nome: "+ this.getNome() + "\t" + "| Custo: " + this.getDinheiro() + " | Estatuto: " + this.estatutoMinimo);
    }

}


