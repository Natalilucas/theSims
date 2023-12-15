package Tools;

import Model.VendaRepo;

import java.util.ArrayList;
import java.util.Scanner;

public class CSVVendasReader {

    String filePath;

    Scanner scFile = new Scanner(System.in);

    public CSVVendasReader(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<VendaRepo> readCSVtoModel(){
        String linha = scFile.nextLine();

        ArrayList<VendaRepo> vendaRepo = new ArrayList<>();

        while(scFile.hasNextLine()){
            linha = scFile.nextLine();
            String [] linhaDividida = linha.split(";");

            String tipo = linhaDividida[0];
            String nome = linhaDividida[1];
            double custo = Double.parseDouble(linhaDividida[2]);
            int estatuto =  Integer.parseInt(linhaDividida[3]);
            int lotacaoMax = Integer.parseInt(linhaDividida[4]);
            String marca = linhaDividida[5];
            String modelo = linhaDividida[6];
            Boolean formal = Boolean.parseBoolean(linhaDividida[7]);
            //tipo;nome;custo;estatuto;lotacaoMax;marca;modelo;formal
        }

        return new ArrayList<>();
    }





}

