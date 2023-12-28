package Tools;

import Domain.Pessoa.NPC;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVNPCReader {
    private String path;

    public CSVNPCReader(String path) {
        this.path = path;
    }

    public ArrayList<NPC> readCVStoModel() throws FileNotFoundException {

        ArrayList<NPC> npcList = new ArrayList<>();
        Scanner sc = new Scanner(new File(this.path));
        String linha = sc.nextLine();

        while(sc.hasNextLine()) {
            linha = sc.nextLine();
            String[] linhadividida = linha.split(";");

            String nome = linhadividida[0];
            double dinheiro = Double.parseDouble(linhadividida[1]);
            int estatuto = Integer.parseInt(linhadividida[2]);

            NPC npc = null;

            npc = new NPC(nome, dinheiro, estatuto);

            npcList.add(npc);


        }
        return npcList;

    }

    public static void imprimirFicheiro(String caminho) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File(caminho));
        String linha;

        while (fileScanner.hasNextLine()){
            linha = fileScanner.nextLine();
            System.out.println(linha);
        }

    }
}
