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
        int opcao = 0, opcaoCompra = 0;
        int itemComprar;

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

                while(arrayIndexAleatorio.size() <= 10){
                    indexAleatorio = random.nextInt(0, this.coisasParaComprar.size());
                    if (arrayIndexAleatorio.size() == 0) {
                        arrayIndexAleatorio.add(indexAleatorio);
                    }

                    if(!arrayIndexAleatorio.contains(indexAleatorio) && coisasParaComprar.get(indexAleatorio) instanceof Imovel) {
                        arrayIndexAleatorio.add(indexAleatorio);
                    }
                }

                for (i = 0; i < arrayIndexAleatorio.size(); i++) {
                    System.out.println("Lista de imóveis " + i + " : ");
                    this.coisasParaComprar.get(arrayIndexAleatorio.get(i)).imprimirDetalhes();
                }


                System.out.println("Deseja realizar alguma compra? \n 1 - Sim | 2 - Não");
                opcaoCompra = input.nextInt();

                if(opcaoCompra == 1){
                    System.out.println("Qual item da nossa lista deseja comprar ? ");
                    itemComprar = input.nextInt();
                    System.out.println(itemComprar);
                    System.out.print("O item que deseja comprar ");
                    this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).imprimirDetalhes();
                    System.out.println("A compra será realizada mediante dinheiro disponível ..");
                    this.coisasParaComprar.remove(itemComprar);
                    System.out.println("item retirado da lista de imoveis");
                    this.coisasParaComprar.get(arrayIndexAleatorio.get(i)).imprimirDetalhes();
                }




                break;
            case 2:
                System.out.println("**************************** Bravosí Car *****************************");
                System.out.println("Lista de carros: ");
                ArrayList<Integer> arrayIndexAleatorio1 = new ArrayList<>();

                while(arrayIndexAleatorio1.size() < 10){
                    indexAleatorio = random.nextInt(0, this.coisasParaComprar.size());
                    if (arrayIndexAleatorio1.size() == 0) {
                        arrayIndexAleatorio1.add(indexAleatorio);
                    }

                    if(!arrayIndexAleatorio1.contains(indexAleatorio) && coisasParaComprar.get(indexAleatorio) instanceof Veiculo) {
                        arrayIndexAleatorio1.add(indexAleatorio);
                    }
                }

                for (i = 0; i < arrayIndexAleatorio1.size(); i++) {
                    System.out.println("Lista de imóveis " + i + " : ");
                    this.coisasParaComprar.get(arrayIndexAleatorio1.get(i)).imprimirDetalhes();
                }
                break;


            case 3:
                System.out.println("**************************** Drotaki Clothes *****************************");
                System.out.println("Carro: ");
                ArrayList<Integer> arrayIndexAleatorio2 = new ArrayList<>();

                while(arrayIndexAleatorio2.size() < 10){
                    indexAleatorio = random.nextInt(0, this.coisasParaComprar.size());
                    if (arrayIndexAleatorio2.size() == 0) {
                        arrayIndexAleatorio2.add(indexAleatorio);
                    }

                    if(!arrayIndexAleatorio2.contains(indexAleatorio) && coisasParaComprar.get(indexAleatorio) instanceof AcessorioModa) {
                        arrayIndexAleatorio2.add(indexAleatorio);
                    }
                }

                for (i = 0; i < arrayIndexAleatorio2.size(); i++) {
                    System.out.println("Peça " + i + " : ");
                    this.coisasParaComprar.get(arrayIndexAleatorio2.get(i)).imprimirDetalhes();
                }
                break;


        }

    }
}







