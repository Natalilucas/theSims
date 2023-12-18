package Controller;

import Domain.Pessoa.Pessoa;
import Domain.Propriedade.Propriedade;

import java.util.Scanner;

public class TheSimsController {

    public static void vender(Pessoa Pessoa, Propriedade secao){
        int opcao = 0;

        Scanner input = new Scanner(System.in);
        System.out.println("BEM VINDO AO UNIVERSO MALL ! \t");
        System.out.println("Qual loja quer ascender: 1 - Imobiliária | 2 - Stand de veiculos | 3 - Acessorios pessoais \t");
        opcao = input.nextInt();
        switch (opcao){
            case 1:
                System.out.println("Nossa Imobiliária possui imóveis incrivéis, veja nossas opções e escolha: ");
                   // função que traz o array de imoveis opcaoImoveis();
                break;
            case 2:
                break;
            case 3:
                break;
            case 0:
                break;
            default:
                System.out.println("Ainda não temos a loja que precisa, escolha uma das opções ou 0 para sair");
                opcao = input.nextInt();
        }
    }

    public static void comprar(){

    }




}
