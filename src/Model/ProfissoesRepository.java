package Model;

import Domain.Pessoa.Profissao;
import Tools.CSVProfissoesReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ProfissoesRepository {

    private ArrayList<Profissao> profissaosArray;

    public ProfissoesRepository(String filePath) throws FileNotFoundException {
        CSVProfissoesReader csvProfissoesReader = new CSVProfissoesReader(filePath);
        this.profissaosArray = csvProfissoesReader.readCVStoModel();
    }

    public ArrayList<Profissao> getProfissaosArray() {
        return profissaosArray;
    }
}
