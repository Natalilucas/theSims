package Model;

import Domain.Propriedade.Propriedade;
import Tools.CSVVendasReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class PropriedadeRepository {

    private ArrayList<Propriedade> propriedadesArray;

    public PropriedadeRepository(String filePath) throws FileNotFoundException {
        CSVVendasReader csvVendasReader = new CSVVendasReader(filePath);
        this.propriedadesArray = csvVendasReader.readCSVtoModel();
    }

    public ArrayList<Propriedade> getPropriedadesArray(){
        return propriedadesArray;
    }


}
