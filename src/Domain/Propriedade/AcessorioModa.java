package Domain.Propriedade;

public class AcessorioModa extends Propriedade {
    private String marca;
    private boolean formal;

    public AcessorioModa(String nome, double custo, int estatuto, String marca, boolean formal) {
        super(nome, custo, estatuto);
        this.marca = marca;
        this.formal = formal;
    }

    public void addAcessorioModa(Propriedade[] propriedades){
        int i = 0;
        while(this.propriedades.size() < 10 ) {
            this.propriedades.add(propriedades[i]);
        }
    }
}
