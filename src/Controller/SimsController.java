package Controller;

import Domain.Enum.Objetivo;
import Domain.Pessoa.Jogador;
import Domain.Pessoa.Profissao;
import Domain.Propriedade.AcessorioModa;
import Domain.Propriedade.Imovel;
import Domain.Propriedade.Propriedade;
import Domain.Propriedade.Veiculo;
import Model.ProfissoesRepository;
import Model.PropriedadeRepository;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SimsController {
    private ArrayList<Propriedade> coisasParaComprar;

    private static ArrayList<Profissao> escolherProfissao;

    public SimsController() throws FileNotFoundException {
        PropriedadeRepository repositoriodeVendas = new PropriedadeRepository("Ficheiros/CoisasShopping.csv");
        ProfissoesRepository repositorioProfissao = new ProfissoesRepository("Ficheiros/Profissoes.csv");
        coisasParaComprar = repositoriodeVendas.getPropriedadesArray();
        escolherProfissao = repositorioProfissao.getProfissaosArray();
    }


    public ArrayList<Propriedade> getCoisasParaComprar() {
        return coisasParaComprar;
    }

    public ArrayList<Profissao> getEscolherProfissao() {
        return escolherProfissao;
    }

    public static void imprimirProfissao(){
        for (int i = 0; i < escolherProfissao.size(); i++) {
            System.out.print("Option " + (i+1));
            escolherProfissao.get(i).imprimirDetalhes();
        }
    }
    //criar o escolher profissao if(null) choose

    public void vender(Jogador jogador) {
        Scanner input = new Scanner(System.in);
        int opcao = 0, opcaoCompra = 0;
        int itemComprar;
        boolean realizouCompra = false;

        System.out.println(" ----------------------------- WINTERFELL MALL -------------------------");
        //colocar um art de um shopping
        System.out.println("Em qual seção quer comprar: 1 - Imobiliária | 2 - Stand | 3 - Acessórios");
        opcao = input.nextInt();

        int indexAleatorio = 0, i = 0;
        Random random = new Random();

        switch (opcao) {
            case 1:
                System.out.println("**************************** Imobiliaria Brandon the Builder *****************************");
                System.out.println("Veja nossa lista de imóveis: ");
                ArrayList<Integer> arrayIndexAleatorio = new ArrayList<>(); //guarda as posicoes de index recebida

                while (arrayIndexAleatorio.size() < 10) { // Este array recebe posições aleatorias de 0 a 9.
                    indexAleatorio = random.nextInt(0, this.coisasParaComprar.size()); // recupera atraves da posição aleatorio e preenche o array aleatorio

                    if (arrayIndexAleatorio.size() == 0 && coisasParaComprar.get(indexAleatorio) instanceof Imovel) {
                        arrayIndexAleatorio.add(indexAleatorio);
                    }
                    //Garante que a primeira posição do array está preenchida com o primeiro index aleatorio


                    //verifica que o index ainda está vazio, senão add.
                    if (!arrayIndexAleatorio.contains(indexAleatorio) && coisasParaComprar.get(indexAleatorio) instanceof Imovel) {
                        arrayIndexAleatorio.add(indexAleatorio);
                    }
                }

                for (i = 0; i < arrayIndexAleatorio.size(); i++) {
                    System.out.println("Lista de imóveis " + i + " : ");
                    this.coisasParaComprar.get(arrayIndexAleatorio.get(i)).imprimirDetalhes();
                }


                System.out.println("Deseja realizar alguma compra? \n 1 - Sim | 2 - Não");
                opcaoCompra = input.nextInt();

                if (opcaoCompra == 1) {
                    System.out.println("Qual item da nossa lista deseja comprar ? ");
                    itemComprar = input.nextInt();
                    System.out.print("O item que deseja comprar: ");
                    //this.coisasParaComprar.get(itemComprar).imprimirDetalhes();
                    System.out.println(this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).getNome());  //Imprimir os detalhes do item que o cliente quer comprar
                    if (this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).getCusto() <= jogador.getDinheiro()) {
                        jogador.addAPropriedade(this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)));
                        this.coisasParaComprar.remove(itemComprar);
                        realizouCompra = true;
                        if (realizouCompra) {
                            System.out.println("item adicionado a sua lista de imóveis");
                        }
                    } else {
                        System.out.println("Xiiiii, não tens dinheiro suficiente para esta compra. Seu saldo é: " + jogador.getDinheiro());
                    }
                }


                break;
            case 2:
                System.out.println("**************************** Bravosí Car *****************************");
                System.out.println("Lista de carros: ");
                ArrayList<Integer> arrayIndexAleatorio1 = new ArrayList<>();

                while (arrayIndexAleatorio1.size() < 10) {
                    indexAleatorio = random.nextInt(0, this.coisasParaComprar.size());
                    if (arrayIndexAleatorio1.size() == 0) {
                        arrayIndexAleatorio1.add(indexAleatorio);
                    }

                    if (!arrayIndexAleatorio1.contains(indexAleatorio) && coisasParaComprar.get(indexAleatorio) instanceof Veiculo) {
                        arrayIndexAleatorio1.add(indexAleatorio);
                    }
                }

                for (i = 0; i < arrayIndexAleatorio1.size(); i++) {
                    System.out.println("Lista de veiculos " + i + " : ");
                    this.coisasParaComprar.get(arrayIndexAleatorio1.get(i)).imprimirDetalhes();
                }


                System.out.println("Deseja realizar alguma compra? \n 1 - Sim | 2 - Não");
                opcaoCompra = input.nextInt();

                if (opcaoCompra == 1) {
                    System.out.println("Qual item da nossa lista deseja comprar ? ");
                    itemComprar = input.nextInt();
                    System.out.print("O item que deseja comprar: ");

                    System.out.println(this.coisasParaComprar.get(arrayIndexAleatorio1.get(itemComprar)).getNome());  //Imprimir os detalhes do item que o cliente quer comprar
                    if (this.coisasParaComprar.get(arrayIndexAleatorio1.get(itemComprar)).getCusto() <= jogador.getDinheiro()) {
                        jogador.addAPropriedade(this.coisasParaComprar.get(arrayIndexAleatorio1.get(itemComprar)));
                        this.coisasParaComprar.remove(itemComprar);
                        realizouCompra = true;
                        if (realizouCompra) {
                            System.out.println("item adicionado a sua lista de veiculos");
                        }

                    } else {
                        System.out.println("Xiiiii, não tens dinheiro suficiente para esta compra. Seu saldo é: " + jogador.getDinheiro());
                    }
                }

                break;


            case 3:
                System.out.println("**************************** Drotaki Clothes *****************************");
                System.out.println("Carro: ");
                ArrayList<Integer> arrayIndexAleatorio2 = new ArrayList<>();

                while (arrayIndexAleatorio2.size() < 10) {
                    indexAleatorio = random.nextInt(0, this.coisasParaComprar.size());
                    if (arrayIndexAleatorio2.size() == 0) {
                        arrayIndexAleatorio2.add(indexAleatorio);
                    }

                    if (!arrayIndexAleatorio2.contains(indexAleatorio) && coisasParaComprar.get(indexAleatorio) instanceof AcessorioModa) {
                        arrayIndexAleatorio2.add(indexAleatorio);
                    }
                }

                for (i = 0; i < arrayIndexAleatorio2.size(); i++) {
                    System.out.println("Peça " + i + " : ");
                    this.coisasParaComprar.get(arrayIndexAleatorio2.get(i)).imprimirDetalhes();
                }

                System.out.println("Deseja realizar alguma compra? \n 1 - Sim | 2 - Não");
                opcaoCompra = input.nextInt();

                if (opcaoCompra == 1) {
                    System.out.println("Qual acessório deseja comprar ? ");
                    itemComprar = input.nextInt();
                    System.out.print("O item está disponível: ");

                    System.out.println(this.coisasParaComprar.get(arrayIndexAleatorio2.get(itemComprar)).getNome());  //Imprimir os detalhes do item que o cliente quer comprar
                    if (this.coisasParaComprar.get(arrayIndexAleatorio2.get(itemComprar)).getCusto() <= jogador.getDinheiro()) {
                        jogador.addAPropriedade(this.coisasParaComprar.get(arrayIndexAleatorio2.get(itemComprar)));
                        this.coisasParaComprar.remove(itemComprar);
                        realizouCompra = true;
                        if (realizouCompra) {
                            System.out.println("item adicionado a sua lista de acessórios");
                        }

                    } else {
                        System.out.println("Xiiiii, não tens dinheiro suficiente para esta compra. Seu saldo é: " + jogador.getDinheiro());
                    }
                }

                break;


        }

    }


    public static Jogador criarPessoa() {

        Scanner input = new Scanner(System.in);
        String nome;
        Objetivo objetivoVida = null;
        Profissao profissao = null;
        int contador, indexObjetivo, counter;

        System.out.println("****************Welcome to our game of thrones****************");
        System.out.println("AY, Name you boy/girl. Your king commands: ");
        nome = input.next();
        System.out.println("Wich is your goal, choose a number: ");

        contador = 1;
        for (Objetivo objetivo : Objetivo.values()) {
            System.out.println(contador++ + ": " + objetivo);
        }

        System.out.print("Choose now by the number ");
        indexObjetivo = input.nextInt();
        counter = 1;
        for (Objetivo objetivo : Objetivo.values()) {
            if (indexObjetivo == contador) {
                objetivoVida = objetivo;
            }
            contador++;
        }

        Jogador novoJogador = new Jogador(nome, 0, objetivoVida, null, 100, 100, 100, 0, 0);

        return novoJogador;

    }

    //criar o ciclo diario


    public static void cicloVida(Jogador jogador){
        int dia;
        //if(trabalhar) dinheiro =* salarioDia.
        String cicloAtual = null;

        Scanner sc = new Scanner(System.in);
        cicloAtual = sc.next();


        switch (cicloAtual){
            case "trabalhar":
                if(jogador.getProfissaoAtual().equals(null)){
                    System.out.println("You have the right to choose what you will be");
                    imprimirProfissao();
                }
                break;
            case "dormir":
                System.out.println("zzzzZZZZZZZ");
                if(jogador.getNecessidadeSono() < 100){
                    jogador.setNecessidadeSono(100);
                }
                break;
            case "refeicao":
                if(jogador.getNecessidadeRefeicao() < 100){
                    jogador.setNecessidadeRefeicao(100);
                    jogador.setDinheiro(jogador.getDinheiro()-5);
                }
                break;
            case "falarPessoa", "jogarPC", "hobby":
                if(jogador.getNecessidadeSocial() < 100){
                    jogador.setNecessidadeSocial(100);
                }
                break;
            case "irCompras":
                break;
            case "formacao":
                break;
            case "visitarProp":
                break;

        }

    }



    //Quando desenvolver o casamento, imprimir o NPC todos para o jogador poder selecionar (A casa deve ter capacidade para
    // pelo menos duas pessoas, a casa que o jogador mora é a casa com maior capacidade de lotação)

}
