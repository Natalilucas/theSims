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
        System.out.print("House: " + this.getNome() + " | Price: " +this.getCusto() + " | Status: " + this.getEstatuto());
        System.out.println(" | Capacity of members: " + this.capacidadePessoas);
        System.out.println();
    }

    public int getCapacidadePessoas() {
        return capacidadePessoas;
    }
}
