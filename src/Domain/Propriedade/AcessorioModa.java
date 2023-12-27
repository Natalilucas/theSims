package Domain.Propriedade;

public class AcessorioModa extends Propriedade {
    private String marca;
    private boolean formal;

    public AcessorioModa(String nome, double custo, int estatuto, String marca, boolean formal) {
        super(nome, custo, estatuto);
        this.marca = marca;
        this.formal = formal;
    }

    public String getMarca() {
        return marca;
    }

    public boolean isFormal() {
        return formal;
    }

    @Override
    public void imprimirDetalhes() {
        super.imprimirDetalhes();
        System.out.println(" Marca " + this.marca + "| É formal " + this.formal);
        System.out.println();
    }
}
