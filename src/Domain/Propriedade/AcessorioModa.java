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
        System.out.print("Name: "+ this.getNome() + "\t" + "| Cost: " + this.getCusto() + " | Status: " + this.getEstatuto());
        System.out.println(" | Brand " + this.marca + "| Formal " + this.formal);
        System.out.println();
    }
}
