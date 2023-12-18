package Domain.Shopping;

import Domain.Pessoa.Pessoa;
import Domain.Propriedade.AcessorioModa;
import Domain.Propriedade.Imovel;
import Domain.Propriedade.Propriedade;
import Domain.Propriedade.Veiculo;
import Model.VendaRepo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Shopping {
    private ArrayList<Propriedade> coisasParaComprar;

    public Shopping(ArrayList<Propriedade> propriedadesVenda) throws FileNotFoundException {
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

        ArrayList<Integer> arrayIndexAleatorio = new ArrayList<>();
        ArrayList<Integer> indexAleatorio = new ArrayList<>();


        switch (opcao){
            case 1:
                System.out.println("**************************** Imobiliaria Brandon the Builder *****************************");
                System.out.println("Veja nossa lista de imóveis");
                if(!arrayIndexAleatorio.contains(indexAleatorio)){
                    

                    }
                }




                break;
            case 2:

                break;
            case 3:

                break;
        }

    }




}






}
