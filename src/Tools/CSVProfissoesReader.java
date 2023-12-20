package Tools;

import Domain.Pessoa.Profissao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVProfissoesReader {

    private String path;

    public CSVProfissoesReader(String path) throws FileNotFoundException {
        this.path = path;
    }


    public ArrayList<Profissao> readCVStoModel() throws FileNotFoundException {
        ArrayList<Profissao> profissoesLista = new ArrayList<>();

        Scanner sc = new Scanner(new File(this.path));
        String linha = sc.nextLine();

        while(sc.hasNextLine()){
            linha = sc.nextLine();
            String [] linhaDividida = linha.split(";");

            String nome = linhaDividida[0];
            double salarioDia = Double.parseDouble(linhaDividida[1]);
            boolean formal = Boolean.parseBoolean(linhaDividida[2]);
            int estatuto = Integer.parseInt(linhaDividida[3]);
            int nivelMinimoEducacao = Integer.parseInt(linhaDividida[4]);

            Profissao profissao = null;

            profissao = new Profissao(nome, salarioDia, formal, estatuto, nivelMinimoEducacao);

            profissoesLista.add(profissao);

        }

        return profissoesLista;
    }


    public static void imprimirFicheiro(String caminho) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File(caminho));
        String linha;

        while (fileScanner.hasNextLine()) {
            linha = fileScanner.nextLine();
            System.out.println(linha);
        }

    }

}
