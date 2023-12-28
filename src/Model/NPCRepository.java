package Model;


import Domain.Pessoa.NPC;
import Tools.CSVNPCReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class NPCRepository {

    private ArrayList<NPC> npcArrayList;

    public NPCRepository(String filePath) throws FileNotFoundException {
        CSVNPCReader csvnpcReader = new CSVNPCReader(filePath);
        this.npcArrayList = csvnpcReader.readCVStoModel();
    }

    public ArrayList<NPC> getNpcArrayList() {
        return npcArrayList;
    }
}
