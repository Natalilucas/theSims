package Domain.Propriedade;
import java.util.ArrayList;

public class Propriedade {
    protected ArrayList<Propriedade> propriedades;
    private String nome;
    private double custo;
    private int estatuto;

    public Propriedade(String nome, double custo, int estatuto, Propriedade propriedade) {
        this.nome = nome;
        this.custo = custo;
        this.estatuto = estatuto;
        this.propriedades = new ArrayList<>();
    }


    public Propriedade(String nome, double custo, int estatuto) {
    }
}
