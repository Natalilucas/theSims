package Domain.Propriedade;
import java.util.ArrayList;

public class Propriedade {
    private String nome;
    private double custo;
    private int estatuto;

    public Propriedade(String nome, double custo, int estatuto) {
        this.nome = nome;
        this.custo = custo;
        this.estatuto = estatuto;
    }

    public String getNome() {
        return nome;
    }

    public double getCusto() {
        return custo;
    }

    public int getEstatuto() {
        return estatuto;
    }

    public void imprimirDetalhes(){
        System.out.println("Nome: "+ this.nome + "\t" + "| Custo: " + this.custo + " | Estatuto: " + this.estatuto);
    }


}
