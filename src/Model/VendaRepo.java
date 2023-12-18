package Model;

import Domain.Propriedade.Propriedade;
import Tools.CSVVendasReader;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class VendaRepo {

    private ArrayList<Propriedade> propriedadesArray;

    public VendaRepo (String filePath) throws FileNotFoundException {
        CSVVendasReader csvVendasReader = new CSVVendasReader(filePath);
        this.propriedadesArray = csvVendasReader.readCSVtoModel();
    }

    public ArrayList<Propriedade> getPropriedadesArray(){
        return propriedadesArray;
    }


}
