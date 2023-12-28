package Domain.Propriedade;

import Domain.Propriedade.Propriedade;

public class Veiculo extends Propriedade {
    private String marca;
    private String modelo;

    public Veiculo(String nome, double custo, int estatuto, String marca, String modelo) {
        super(nome, custo, estatuto);
        this.marca = marca;
        this.modelo = modelo;
    }

    @Override
    public void imprimirDetalhes() {
        super.imprimirDetalhes();
        System.out.println("Name: " + this.getNome() + " | Cost: " + this.getCusto() + " | Status: " + this.getEstatuto() + " | Brand: " + this.marca + " | Model: " + this.modelo);
        System.out.println();
    }
}
