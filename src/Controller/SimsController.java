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
import java.util.Formattable;
import java.util.Random;
import java.util.Scanner;
import java.util.spi.AbstractResourceBundleProvider;

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


    public void listarPropriedades(Jogador jogador){
        ArrayList<Propriedade> propriedadesDoJogador = new ArrayList<>();
        propriedadesDoJogador = jogador.getPropriedades();
        System.out.println("Here are your properties: " );
        for (Propriedade propriedadeAtual: propriedadesDoJogador) {
            if(propriedadeAtual instanceof  Imovel){
                propriedadeAtual.imprimirDetalhes();
            }
        }
    }

    public static void listarAcessorios(Jogador jogador){
        ArrayList<Propriedade> acessoriosDoJogador = new ArrayList<>();
        acessoriosDoJogador = jogador.getPropriedades();
        System.out.println("Here are your properties: " );
        for (Propriedade propriedadeAtual: acessoriosDoJogador) {
            if(propriedadeAtual instanceof  AcessorioModa){
                propriedadeAtual.imprimirDetalhes();
                propriedadeAtual.getEstatuto();
            }
        }
    }

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

                while (arrayIndexAleatorio.size() < 10) {
                    indexAleatorio = random.nextInt(0, this.coisasParaComprar.size());
                    if (arrayIndexAleatorio.size() == 0) {
                        arrayIndexAleatorio.add(indexAleatorio);
                    }

                    if (!arrayIndexAleatorio.contains(indexAleatorio) && coisasParaComprar.get(indexAleatorio) instanceof Veiculo) {
                        arrayIndexAleatorio.add(indexAleatorio);
                    }
                }

                for (i = 0; i < arrayIndexAleatorio.size(); i++) {
                    System.out.println("Vehicle list " + i + " : ");
                    this.coisasParaComprar.get(arrayIndexAleatorio.get(i)).imprimirDetalhes();
                }


                System.out.println("Do you wat to buy somenthing? \n 1 - Yes | 2 - No");
                opcaoCompra = input.nextInt();

                if (opcaoCompra == 1) {
                    System.out.println("Wich item from our list you want to buy? ");
                    itemComprar = input.nextInt();
                    System.out.print("You bought: ");
                    System.out.println(this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).getNome());  //Imprimir os detalhes do item que o cliente quer comprar

                    if (this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).getCusto() <= jogador.getDinheiro()) {
                        jogador.addAPropriedade(this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)));
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
        double dinheiro = 1000000.00;
        int estatuto = 0, educacao = 0, necessidadeRefeicao = 100, necessidadeSocial = 100, necessidadeSono = 100;


        Objetivo objetivoVida = null;
        Profissao profissao = new Profissao( "null", 0, false, 0, 0);
        int contador, indexObjetivo, counter;

        System.out.println("****************");
        System.out.println("Welcome to our Game of Thrones");
        System.out.println("****************");
        System.out.println("AY, Name you boy/girl. Your king commands you: ");
        nome = input.next();


        contador = 1;
        for (Objetivo objetivo : Objetivo.values()) {
            System.out.println(contador++ + " = " + objetivo);
        }

        System.out.println("Which one is your goal, choose a number: ");
        indexObjetivo = input.nextInt();

        if (indexObjetivo >= 1 && indexObjetivo <= Objetivo.values().length) {
            objetivoVida = Objetivo.values()[indexObjetivo - 1];
            System.out.println("You choose: " + objetivoVida);
        } else {
            System.out.println("Invalid choice. Exiting...");
        }

        Jogador novoJogador = new Jogador(nome, dinheiro, objetivoVida , profissao, necessidadeSono, necessidadeRefeicao, necessidadeSocial, estatuto, educacao);

        return novoJogador;

    }


    public static void interacaoDiaria(Jogador jogador){
        jogador.setNecessidadeSono(jogador.getNecessidadeSono()-25);
        jogador.setNecessidadeRefeicao(jogador.getNecessidadeRefeicao()-20);
        jogador.setNecessidadeSocial(jogador.getNecessidadeSocial()-15);
    }

    public static void detalhesJogador(Jogador jogador){
        System.out.println(" Sleep: " + jogador.getNecessidadeSono()+ " | Meal: " + jogador.getNecessidadeRefeicao() + "| Social: " + jogador.getNecessidadeSocial());
    }


    public void cicloVida(Jogador jogador){
        int cicloAtual, escolhaAtividade, profissaoAtualIndex, dia, changePro;
        boolean temNivel = false;
        System.out.println("Now, if you want to complet your player, you may require to pick a profession ..");

        Scanner sc = new Scanner(System.in);
        for (dia = 1; dia < 100; dia ++) { //clico de dias
            System.out.println("Dia: " + dia);
            for (int momentoDia = 1; momentoDia < 5  ; momentoDia++) { //momento do dia, manhã, meio dia, tarde ou noite
                switch (momentoDia){
                    case 1:
                        detalhesJogador(jogador);
                        System.out.println("What you wanna do now, right dow? It's morning ..Do you want to 1 - work | 2 - sleep | 3 - snack | 4 - talkPeople\", \"playPC\", \"hobby\", 5 - goShop | 6 - Exibir detalhes do Jogador | 7 - Tirar formação | 8 -  Escolher Profissão | 9 - Visistar Propriedades | 0 - Out  ");

                        escolhaAtividade = sc.nextInt();
                        momentoAtual(escolhaAtividade, jogador);

                        /*if( jogador.getNecessidadeSocial() < 25 || jogador.getNecessidadeSono() < 25 ||  jogador.getNecessidadeRefeicao() < 25) {
                            if (jogador.getNecessidadeSocial() <= 25) {
                                escolhaAtividade = sc.nextInt();
                                momentoAtual(escolhaAtividade, jogador);
                                do {
                                    System.out.println("While you need to socialize, you only option is nº 4");
                                    escolhaAtividade = sc.nextInt();
                                    momentoAtual(escolhaAtividade, jogador);
                                } while (escolhaAtividade != 4);
                            }

                            if (jogador.getNecessidadeSono() <= 25) {
                                escolhaAtividade = sc.nextInt();
                                momentoAtual(escolhaAtividade, jogador);
                                do {
                                    System.out.println("While you need to sleep, you only option is nº 2");
                                    escolhaAtividade = sc.nextInt();
                                    momentoAtual(escolhaAtividade, jogador);
                                } while (escolhaAtividade != 2);

                            }

                            if (jogador.getNecessidadeRefeicao() <= 25) {
                                escolhaAtividade = sc.nextInt();
                                momentoAtual(escolhaAtividade, jogador);
                                do {
                                    System.out.println("While you need to eat, you only option is nº 3");
                                    escolhaAtividade = sc.nextInt();
                                    momentoAtual(escolhaAtividade, jogador);
                                } while (escolhaAtividade != 3);
                            }
                        } else {
                            escolhaAtividade = sc.nextInt();
                            momentoAtual(escolhaAtividade, jogador);
                        }*/





                        break;
                    case 2:
                        interacaoDiaria(jogador);
                        detalhesJogador(jogador);
                        System.out.println("What you wanna do now, right dow? It's mid-day ..Do you want to 1 - work | 2 - sleep | 3 - snack | 4 - talkPeople\", \"playPC\", \"hobby\", 5 - goShop | 6 - Exibir detalhes do Jogador | 7 - Tirar formação | 8 -  Escolher Profissão | 9 - Visistar Propriedades | 0 - Out  ");
                        escolhaAtividade = sc.nextInt();
                        momentoAtual(escolhaAtividade, jogador);
                        break;
                    case 3:
                        interacaoDiaria(jogador);
                        detalhesJogador(jogador);
                        System.out.println("What you wanna do now, right dow? It's afternoon ..Do you want to 1 - work | 2 - sleep | 3 - snack | 4 - talkPeople\", \"playPC\", \"hobby\", 5 - goShop | 6 - Exibir detalhes do Jogador | 7 - Tirar formação | 8 -  Escolher Profissão | 9 - Visistar Propriedades | 0 - Out  ");
                        escolhaAtividade = sc.nextInt();
                        momentoAtual(escolhaAtividade, jogador);
                        break;
                    case 4:
                        interacaoDiaria(jogador);
                        detalhesJogador(jogador);
                        System.out.println("What you wanna do now, right dow? It's evening ..Do you want to 1 - work | 2 - sleep | 3 - snack | 4 - talkPeople\", \"playPC\", \"hobby\", 5 - goShop | 6 - Exibir detalhes do Jogador | 7 - Tirar formação | 8 -  Escolher Profissão | 9 - Visistar Propriedades | 0 - Out  ");
                        escolhaAtividade = sc.nextInt();
                        momentoAtual(escolhaAtividade, jogador);
                        break;
                }
            }
        }



    }


    public void momentoAtual(int momentoAtual, Jogador jogador) {
        int profissaoAtualIndex, changePro;
        boolean temNivel = false;
        Scanner sc = new Scanner(System.in);


        do {
            switch (momentoAtual) {
                case 1: // trabalhar

                    Profissao profissaoAtual = jogador.getProfissaoAtual();
                    if (profissaoAtual.getNome().equals("null")){
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
                    return;
                case 2: //Dormir
                    System.out.println("zzzzZZZZZZZ");
                    System.out.println("Sleep need: " +  jogador.getNecessidadeSono());
                    if (jogador.getNecessidadeSono() < 100) {
                        jogador.setNecessidadeSono(100);

                    }
                    return;

                case 3: // comer
                    System.out.println("Huuumm..");
                    jogador.setDinheiro(jogador.getDinheiro() - 5);
                    System.out.println("Your didn't starve, and you paid for it");
                    System.out.println(" Bank balance : " + jogador.getDinheiro());
                    if (jogador.getNecessidadeRefeicao() < 100) {
                        jogador.setNecessidadeRefeicao(100);
                    }
                    return;
                case 4: // Socializar, Jogar ou Fazer algum Hobby
                    System.out.println("Playing ...talking.. doing some hobby");
                    if (jogador.getNecessidadeSocial() < 100) {
                        jogador.setNecessidadeSocial(100);
                    }
                    return;
                case 5: //Chamar o método de vender
                    vender(jogador);
                    return;
                case 6: //Mostrar detalhes do jogador
                    System.out.println("Player information: ");
                    jogador.mostrarDetalhes();
                    return;

                case 7: //Formação +2
                    System.out.println("Do you want to learn something so you can change your future? ");
                    System.out.print("Welcome to CESAE, your level of education is up ");
                    jogador.setEducacao(jogador.getEducacao()+2);
                    System.out.println("points");
                    System.out.println(jogador.getEducacao());
                    return;
                case 8: // Escolher a profissão
                    profissaoAtual = jogador.getProfissaoAtual();
                    System.out.println("Actual profession: " + profissaoAtual);

                    if (profissaoAtual.getNome().equals("null")) {
                        System.out.println("You have the right to choose what you will be");
                        imprimirProfissao();
                        System.out.println("Which one do you choose to be? ");

                        profissaoAtualIndex = sc.nextInt();
                        Profissao profissaoEscolhida = this.getEscolherProfissao().get(profissaoAtualIndex - 1);

                        System.out.println("Minimun Education level required: " + profissaoEscolhida.getNivelMinimoEducacao());
                        System.out.println("Player education: " + jogador.getEducacao());

                       /* boolean temObjetoFormal = false;
                        for (Propriedade propriedadeAtual : jogadorNovo.getTodasAsPropriedades()) {
                            if (propriedadeAtual instanceof AcessorioModa && ((AcessorioModa) propriedadeAtual).getFormal() == true) {
                                temObjetoFormal = true;
                            } else {
                                System.out.println("Não tens acessorios formais!");
                            }
                        }

                        if (listaProfissoes().get(opcaoProfissao - 1).isFormal() && temObjetoFormal == true) {
                            jogadorNovo.setProfissao(listaProfissoes().get(opcaoProfissao - 1));
                        }*/



                        if (profissaoEscolhida.getNivelMinimoEducacao() <= jogador.getEducacao() || profissaoEscolhida.getEstatuto() <= jogador.getEstatuto() ) { // || jogador.getPropriedades()
                            temNivel = true;
                            jogador.setProfissaoAtual(profissaoEscolhida);
                            System.out.println("You want to be " + jogador.getProfissaoAtual().getNome() + ". Grated");
                        } else {
                            System.out.println("You don't have the education for that. Go back and find yourself an education");
                            if(escolherProfissao.get(profissaoAtualIndex).isFormal() == true ){
                                listarAcessorios(jogador);
                            }
                            return;
                        }
                    } else {
                        System.out.println("You already have a profession: " + profissaoAtual.getNome() + " To change, type 1 ou 0 to go back to menu");
                        changePro = sc.nextInt();
                        if(changePro == 1){
                            boolean b = profissaoAtual == null;
                            return;
                        } else if (changePro == 0){
                            return;
                        }
                    }

                    return;

                case 9: //visitar a propriedade
                    listarPropriedades(jogador);
                    return;

                case 0: //Sair
                    return;
            }
        }while (momentoAtual != 0) ;
    }
}

    //Quando desenvolver o casamento, imprimir o NPC todos para o jogador poder selecionar (A casa deve ter capacidade para
    // pelo menos duas pessoas, a casa que o jogador mora é a casa com maior capacidade de lotação)


