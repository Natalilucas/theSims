package Controller;

import Domain.Enum.Objetivo;
import Domain.Pessoa.Jogador;
import Domain.Pessoa.Profissao;
import Domain.Propriedade.AcessorioModa;
import Domain.Propriedade.Imovel;
import Domain.Propriedade.Propriedade;
import Domain.Propriedade.Veiculo;
import Domain.Shopping.Shopping;
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
        ArrayList<Integer> arrayIndexAleatorio = new ArrayList<>(); //guarda as posicoes de index recebida

        System.out.println(" ----------------------------- WINTERFELL MALL -------------------------");
        //colocar um art de um shopping
        System.out.println("Wich section do you want to see: 1 - Immobile | 2 - Stand | 3 - Acessories");
        opcao = input.nextInt();

        int indexAleatorio = 0, i = 0;
        Random random = new Random();

        switch (opcao) {
            case 1:
                System.out.println("**************************** Brandon the Builder of Immobiles *****************************");
                System.out.println("See our immobile list: ");


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
                    System.out.println("Immobile list " + i + " : ");
                    this.coisasParaComprar.get(arrayIndexAleatorio.get(i)).imprimirDetalhes();
                }


                System.out.println("Do you wat to buy somenthing? \n 1 - Yes | 2 - No");
                opcaoCompra = input.nextInt();

                if (opcaoCompra == 1) {
                    System.out.println("Which item from our list do you want to buy? ");
                    itemComprar = input.nextInt();
                    System.out.print("The item you want to buy : ");
                    //this.coisasParaComprar.get(itemComprar).imprimirDetalhes();
                    System.out.println(this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).getNome());  //Imprimir os detalhes do item que o cliente quer comprar
                    if (this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).getCusto() <= jogador.getDinheiro()) {
                        jogador.addAPropriedade(this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)));
                        this.coisasParaComprar.remove(itemComprar);
                        realizouCompra = true;
                        if (realizouCompra) {
                            jogador.setDinheiro( jogador.getDinheiro() - this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).getCusto());
                            System.out.println(" item added to your property list ");
                            jogador.setDinheiro( jogador.getDinheiro() - (this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).getCusto()));
                            jogador.setEstatuto(jogador.getEstatuto() + (this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).getEstatuto()));
                            jogador.mostrarDetalhes();
                        }
                    } else {
                        System.out.println("Ups, you don't have enough money. Account balance: " + jogador.getDinheiro());
                    }
                }


                break;
            case 2:
                System.out.println("**************************** Bravosí Car *****************************");
                System.out.println("Car list: ");
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
                    System.out.println("Vehicle list " + i + " : ");
                    this.coisasParaComprar.get(arrayIndexAleatorio1.get(i)).imprimirDetalhes();
                }


                System.out.println("Do you wat to buy somenthing? \n 1 - Yes | 2 - No");
                opcaoCompra = input.nextInt();

                if (opcaoCompra == 1) {
                    System.out.println("Wich item from our list you want to buy? ");
                    itemComprar = input.nextInt();
                    System.out.print("You bought: ");
                    System.out.println(this.coisasParaComprar.get(arrayIndexAleatorio1.get(itemComprar)).getNome());  //Imprimir os detalhes do item que o cliente quer comprar

                    if (this.coisasParaComprar.get(arrayIndexAleatorio1.get(itemComprar)).getCusto() <= jogador.getDinheiro()) {
                        jogador.addAPropriedade(this.coisasParaComprar.get(arrayIndexAleatorio1.get(itemComprar)));
                        this.coisasParaComprar.remove(itemComprar);
                        realizouCompra = true;
                        if (realizouCompra) {
                            System.out.println("Car add to your vehicle list");
                            jogador.setDinheiro( jogador.getDinheiro() - (this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).getCusto()));
                            jogador.setEstatuto(jogador.getEstatuto() + (this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).getEstatuto()));
                            jogador.mostrarDetalhes();
                        }

                    } else {
                        System.out.println("Ups, you don't have enough money. Account balance: " + jogador.getDinheiro());
                    }
                }

                break;


            case 3:
                System.out.println("**************************** Drotaki Clothes *****************************");
                System.out.println("Acessories: ");


                while (arrayIndexAleatorio.size() < 10) {
                    indexAleatorio = random.nextInt(0, this.coisasParaComprar.size());
                    if (arrayIndexAleatorio.size() == 0) {
                        arrayIndexAleatorio.add(indexAleatorio);
                    }

                    if (!arrayIndexAleatorio.contains(indexAleatorio) && coisasParaComprar.get(indexAleatorio) instanceof AcessorioModa) {
                        arrayIndexAleatorio.add(indexAleatorio);
                    }
                }

                for (i = 0; i < arrayIndexAleatorio.size(); i++) {
                    System.out.println("Item " + i + " : ");
                    this.coisasParaComprar.get(arrayIndexAleatorio.get(i)).imprimirDetalhes();
                }

                System.out.println("Do you wat to buy somenthing? \n 1 - Yes | 2 - No");
                opcaoCompra = input.nextInt();

                if (opcaoCompra == 1) {
                    System.out.println(" Which accessory do you want to buy? ");
                    itemComprar = input.nextInt();
                    //System.out.print("O item está disponível: ");

                    //System.out.println(this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).getNome());  //Imprimir os detalhes do item que o cliente quer comprar
                    if (this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).getCusto() <= jogador.getDinheiro()) {
                        jogador.addAPropriedade(this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)));
                        //this.coisasParaComprar.remove(itemComprar);
                        realizouCompra = true;
                        if (realizouCompra) {
                            System.out.println("item added to your accessories list");
                            jogador.setDinheiro( jogador.getDinheiro() - (this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).getCusto()));
                            jogador.setEstatuto(jogador.getEstatuto() + (this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).getEstatuto()));
                            jogador.mostrarDetalhes();
                        }

                    } else {
                        System.out.println("Ups, you don't have enough money. Account balance: " + jogador.getDinheiro());
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

        Jogador novoJogador = new Jogador(nome, 0, objetivoVida , null, 100, 100, 100, 0, 0);

        return novoJogador;

    }

    //criar o ciclo diario




    public void cicloVida(Jogador jogador){
        int cicloAtual, momentoAtual, profissaoAtualIndex, dia;
        boolean temNivel = false;
        System.out.println("Now, if you want to complet your player, you may require to pick a profession ..");

        Scanner sc = new Scanner(System.in);
        for (dia = 1; dia < 100; dia ++) { //clico de dias
            System.out.println("Dia: " + dia);
            for (int momentoDia = 1; momentoDia < 5  ; momentoDia++) { //momento do dia, manhã, meio dia, tarde ou noite
                switch (momentoDia){
                    case 1:
                        do {
                            System.out.println(jogador.getObjetivoVida());
                            System.out.println("What you wanna do now, right dow? It's morning ..Do you want to 1 - work | 2 - sleep | 3 - snack | 4 - talkPeople\", \"playPC\", \"hobby\", 5 - goShop | 6 - Exibir detalhes do Jogador | 7 - Escolher Profissão | 0 - Out  ");

                            momentoAtual = sc.nextInt();
                            momentoAtual(momentoAtual, jogador);


                        }while(momentoAtual == 0);
                        break;
                    case 2:
                        System.out.println("What you wanna do now, right dow? It's midday ..Do you want to 1 - work | 2 - sleep | 3 - snack | 4 - talkPeople\", \"playPC\", \"hobby\", 5 - goShop | 6 - Exibir detalhes do Jogador | 7 - Escolher Profissão |  0 - Out   ");
                        momentoAtual = sc.nextInt();
                        momentoAtual(momentoAtual, jogador);

                        break;


                    case 3:

                        System.out.println("What you wanna do now, right dow? It's afternoon ..Do you want to 1 - work | 2 - sleep | 3 - snack | 4 - talkPeople\", \"playPC\", \"hobby\", 5 - goShop | 6 - Exibir detalhes do Jogador | 7 - Escolher Profissão |  0 - Out    ");
                        momentoAtual = sc.nextInt();
                        momentoAtual(momentoAtual, jogador);

                        break;
                    case 4:

                        System.out.println("What you wanna do now, right dow? It's night ..Do you want to 1 - work | 2 - sleep | 3 - snack | 4 - talkPeople\", \"playPC\", \"hobby\", 5 - goShop | 6 - Exibir detalhes do Jogador | 7 - Escolher Profissão |  0 - Out ");
                        momentoAtual = sc.nextInt();
                        momentoAtual(momentoAtual, jogador);


                        break;



                }


            }

        }



    }


    public void momentoAtual(int momentoAtual, Jogador jogador) {
        int profissaoAtualIndex;
        boolean temNivel = false;
        do {
            switch (momentoAtual) {
                case 1: // case para fazer o player trabalhar

                    Profissao profissaoAtual = jogador.getProfissaoAtual();
                    if (profissaoAtual == null) {
                        System.out.println("Go lock for a job ");
                        return;
                    } else {
                        System.out.println("Good morning, get back to labor: " + profissaoAtual.getNome());
                        double dinheiroAtual = jogador.getDinheiro();
                        double salarioTrabalhado = jogador.getProfissaoAtual().getSalarioDia();
                        jogador.setDinheiro(dinheiroAtual += salarioTrabalhado);
                        System.out.println("Now your have a new job.");
                        System.out.println("After a long day of work, your account was updated!!");
                        System.out.println("Bank Account: " + dinheiroAtual);
                    }
                    break;
                case 2:
                    System.out.println("zzzzZZZZZZZ");
                    if (jogador.getNecessidadeSono() < 100) {
                        jogador.setNecessidadeSono(100);
                    }
                    return;

                case 3:
                    System.out.println("Huuumm..");
                    jogador.setDinheiro(jogador.getDinheiro() - 5);
                    System.out.println("Your didn't starve, and you paid for it");
                    System.out.println(" Bank balance : " + jogador.getDinheiro());
                    if (jogador.getNecessidadeRefeicao() < 100) {
                        jogador.setNecessidadeRefeicao(100);
                    }
                    return;
                case 4:
                    if (jogador.getNecessidadeSocial() < 100) {
                        jogador.setNecessidadeSocial(100);
                    }
                    break;
                case 5:
                    vender(jogador);
                    break;
                case 6:
                    System.out.println("Player information: ");
                    jogador.mostrarDetalhes();
                    break;
                case 7:
                    profissaoAtual = jogador.getProfissaoAtual();
                    System.out.println("Actual profession: " + profissaoAtual);

                    if (profissaoAtual == null) {
                        System.out.println("You have the right to choose what you will be");
                        imprimirProfissao();
                        System.out.println("Which one do you choose to be? ");
                        Scanner sc = new Scanner(System.in);
                        profissaoAtualIndex = sc.nextInt();
                        Profissao profissaoEscolhida = this.getEscolherProfissao().get(profissaoAtualIndex - 1);

                        System.out.println("Education level min required: " + profissaoEscolhida.getNivelMinimoEducacao());
                        System.out.println("Player education: " + jogador.getEducacao());

                        if (profissaoEscolhida.getNivelMinimoEducacao() >= jogador.getEducacao()) {
                            System.out.println("You don't have the education for that. Go back and find yourself an education");
                            return;
                        } else {
                            temNivel = true;
                            jogador.setProfissaoAtual(profissaoEscolhida);
                            System.out.println("You want to be " + jogador.getProfissaoAtual().getNome() + ". Grated");
                        }
                    } else {
                        System.out.println("You already have a profession: " + profissaoAtual.getNome() + " To change, go back to menu");
                    }

                    break;

                case 0:
                    break;
            }
        }while (momentoAtual != 0) ;
    }
}

    //Quando desenvolver o casamento, imprimir o NPC todos para o jogador poder selecionar (A casa deve ter capacidade para
    // pelo menos duas pessoas, a casa que o jogador mora é a casa com maior capacidade de lotação)


