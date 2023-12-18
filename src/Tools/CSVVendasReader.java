package Tools;

import Domain.Propriedade.AcessorioModa;
import Domain.Propriedade.Imovel;
import Domain.Propriedade.Propriedade;
import Domain.Propriedade.Veiculo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVVendasReader {
    private String filePath;
    public CSVVendasReader(String filePath) {
        this.filePath = filePath;
    }
    public ArrayList<Propriedade> readCSVtoModel() throws FileNotFoundException {

        Scanner sc = new Scanner(new File(this.filePath));

        String linha = sc.nextLine();

        ArrayList<Propriedade> propriedadesVenda= new ArrayList<>();

        while(sc.hasNextLine()){
            linha = sc.nextLine();
            String [] linhaDividida = linha.split(";");

            String tipo = linhaDividida[0];
            String nome = linhaDividida[1];
            double custo = Double.parseDouble(linhaDividida[2]);
            int estatuto =  Integer.parseInt(linhaDividida[3]);
            int capacidadePessoas = Integer.parseInt(linhaDividida[4]);
            String marca = linhaDividida[5];
            String modelo = linhaDividida[6];
            Boolean formal = Boolean.parseBoolean(linhaDividida[7]);

            Propriedade propriedadeAtual = null;

            switch (tipo){
                case "Imovel":
                    propriedadeAtual = new Imovel(nome, custo, estatuto, capacidadePessoas);
                    break;
                case "Veiculo":
                    propriedadeAtual = new Veiculo(nome, custo, estatuto, marca, modelo);
                    break;
                case "AcessorioModa":
                    propriedadeAtual = new AcessorioModa(nome, custo, estatuto, marca, formal);
                    break;
            }
            propriedadesVenda.add(propriedadeAtual);
        }

        return propriedadesVenda;

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

