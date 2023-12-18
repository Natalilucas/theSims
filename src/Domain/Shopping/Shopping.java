package Domain.Shopping;

import Domain.Pessoa.Pessoa;
import Domain.Propriedade.AcessorioModa;
import Domain.Propriedade.Imovel;
import Domain.Propriedade.Propriedade;
import Domain.Propriedade.Veiculo;
import Model.VendaRepo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Shopping {
    private ArrayList<Propriedade> coisasParaComprar;

    public Shopping() throws FileNotFoundException {
        VendaRepo repositoriodeVendas = new VendaRepo("Ficheiros/CoisasShopping.csv");
        coisasParaComprar = repositoriodeVendas.getPropriedadesArray();
    }

    public ArrayList<Propriedade> getCoisasParaComprar() {
        return coisasParaComprar;
    }

    public void Vender(Pessoa pessoa){
        Scanner input = new Scanner(System.in);
        int opcao = 0;
        System.out.println(" ----------------------------- WINTERFELL MALL -------------------------");
        //colocar um art de um shopping
        System.out.println("Em qual seção quer comprar: 1 - Imobiliária | 2 - Stand | 3 - Acessórios");
        opcao = input.nextInt();

        int indexAleatorio = 0, i = 0;
        Random random = new Random();

        switch (opcao){
            case 1:
                System.out.println("**************************** Imobiliaria Brandon the Builder *****************************");
                System.out.println("Veja nossa lista de imóveis: ");
                ArrayList<Integer> arrayIndexAleatorio = new ArrayList<>();

                while(arrayIndexAleatorio.size() < 10){
                    indexAleatorio = random.nextInt(0, this.coisasParaComprar.size());
                    if (arrayIndexAleatorio.size() == 0) {
                        arrayIndexAleatorio.add(indexAleatorio);
                    }

                    if(!arrayIndexAleatorio.contains(indexAleatorio) && coisasParaComprar.get(indexAleatorio) instanceof Imovel) {
                        arrayIndexAleatorio.add(indexAleatorio);
                    }
                }

                for (i = 0; i < arrayIndexAleatorio.size(); i++) {
                    System.out.println("Lista de imóveis" + i + ": ");
                    this.coisasParaComprar.get(arrayIndexAleatorio.get(i)).imprimirDetalhes();
                }

             break;
            case 2:

                break;
            case 3:

                break;
        }

    }




}







