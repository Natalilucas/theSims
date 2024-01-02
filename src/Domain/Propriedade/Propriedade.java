package Domain.Propriedade;
import java.util.ArrayList;

public class Propriedade {
    private String nome;
    private double custo;
    private int estatuto;


    /**
     * construtor do tipo propriedade
     * @param nome
     * @param custo
     * @param estatuto
     */
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

    /**
     * metodo para imprimir os detalhes, será sobreescrito
     */
    public void imprimirDetalhes(){
    }


}
