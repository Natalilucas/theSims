package Domain.Shopping;

import Domain.Propriedade.Propriedade;
import Model.PropriedadeRepository;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Shopping {
    private ArrayList<Propriedade> coisasParaComprar;

    public Shopping() throws FileNotFoundException {
        PropriedadeRepository repositoriodeVendas = new PropriedadeRepository("Ficheiros/CoisasShopping.csv");
        coisasParaComprar = repositoriodeVendas.getPropriedadesArray();
    }

    public ArrayList<Propriedade> getCoisasParaComprar() {
        return coisasParaComprar;
    }

}







