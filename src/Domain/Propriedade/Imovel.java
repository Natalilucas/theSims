package Domain.Propriedade;

public class Imovel extends Propriedade {

    private int capacidadePessoas;

    public Imovel(String nome, double custo, int estatuto, int capacidadePessoas) {
        super(nome, custo, estatuto);
        this.capacidadePessoas = capacidadePessoas;
    }

    @Override
    public void imprimirDetalhes() {
        super.imprimirDetalhes();
    }
}
