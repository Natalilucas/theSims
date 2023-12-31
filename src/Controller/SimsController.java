package Controller;

import Domain.Enum.Objetivo;
import Domain.Pessoa.Jogador;
import Domain.Pessoa.NPC;
import Domain.Pessoa.Profissao;
import Domain.Propriedade.AcessorioModa;
import Domain.Propriedade.Imovel;
import Domain.Propriedade.Propriedade;
import Domain.Propriedade.Veiculo;
import Domain.Shopping.Shopping;
import Model.NPCRepository;
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

    private ArrayList<Profissao> escolherProfissao;

    private ArrayList<NPC> obterNPC;

    public ArrayList<Propriedade> getCoisasParaComprar() {
        return coisasParaComprar;
    }

    public ArrayList<Profissao> getEscolherProfissao() {
        return escolherProfissao;
    }

    public ArrayList<NPC> getNPC() {
        return obterNPC;
    }

    public SimsController() throws FileNotFoundException {
        PropriedadeRepository repositoriodeVendas = new PropriedadeRepository("Ficheiros/CoisasShopping.csv");
        ProfissoesRepository repositorioProfissao = new ProfissoesRepository("Ficheiros/Profissoes.csv");
        NPCRepository npcRepository = new NPCRepository("Ficheiros/NPC.csv");
        coisasParaComprar = repositoriodeVendas.getPropriedadesArray();
        escolherProfissao = repositorioProfissao.getProfissaosArray();
        obterNPC = npcRepository.getNpcArrayList();
    }

    /**
     * Método para listar somente os imoveis do jogador
     *
     * @param jogador
     */
    public void listarPropriedades(Jogador jogador) {
        ArrayList<Propriedade> propriedadesDoJogador = new ArrayList<>();
        propriedadesDoJogador = jogador.getPropriedades();
        System.out.println("Here are your properties: ");
        for (Propriedade propriedadeAtual : propriedadesDoJogador) {
            if (propriedadeAtual instanceof Imovel) {
                propriedadeAtual.imprimirDetalhes();
            }
        }
    }

    /**
     * Método para verificar a capacidade das propriedades comprada por daquele jogador
     *
     * @param jogador
     * @return capacidade do imovel
     */
    public int capacidadeImovel(Jogador jogador) {
        int capacidadeTotal = 0;

        for (Propriedade propriedade : jogador.getPropriedades()) {
            if (propriedade instanceof Imovel) {
                Imovel imovel = (Imovel) propriedade;
                capacidadeTotal += imovel.getCapacidadePessoas();
            }
        }
        return capacidadeTotal;
    }

    /**
     * Método para imprimir as profissões para que o jogador possa escolher
     */
    public void imprimirProfissao() {
        for (int i = 0; i < escolherProfissao.size(); i++) {
            System.out.print("Option " + (i + 1));
            escolherProfissao.get(i).imprimirDetalhes();
        }
    }

    /**
     * Método que imprime a lista de npc's para utilizar para o casamento
     */
    public void casarComNPC() {
       ArrayList<NPC> npc = this.getNPC();
        for (int i = 0; i <= npc.size(); i++) {
            if(npc.get(i).getEstatutoMinimo() != 0) {
                System.out.print("Bride or Groom " + (i + 1) + " : ");
                npc.get(i).imprimirDetalhes();
            } else {
                break;
            }
        }
    }

    public void npcFilhos() {
        ArrayList<NPC> npc = this.getNPC();
        for (int i = 0; i < npc.size(); i++) {
            if (npc.get(i).getEstatutoMinimo() == 0) {
                System.out.print("Baby " + (i - 24) + " : ");
                npc.get(i).imprimirDetalhes();
            }
        }
    }

    /**
     * Método para imprimir os acessórios do jogador
     *
     * @param jogador
     */
    public void listarAcessorios(Jogador jogador) {
        ArrayList<Propriedade> acessoriosDoJogador = new ArrayList<>();
        acessoriosDoJogador = jogador.getPropriedades();
        System.out.println("Here are your properties: ");
        for (Propriedade propriedadeAtual : acessoriosDoJogador) {
            if (propriedadeAtual instanceof AcessorioModa) {
                propriedadeAtual.imprimirDetalhes();
                propriedadeAtual.getEstatuto();
            }
        }
    }

    /**
     * Método para efetuar a ação de vender propriedades(imoveis/carros/acessorios)
     *
     * @param jogador
     */
    public void vender(Jogador jogador) {
        Scanner input = new Scanner(System.in);
        int opcao = 0, opcaoCompra = 0;
        int itemComprar;
        boolean realizouCompra = false;
        ArrayList<Integer> arrayIndexAleatorio = new ArrayList<>(); //guarda as posicoes de index recebida

        System.out.println(" ----------------------------- WINTERFELL MALL -------------------------");
        //colocar um art de um shopping
        System.out.println("Wich section do you want to see: 1 - Immobile | 2 - Stand | 3 - Acessories | 0 - Out");
        opcao = input.nextInt();

        int indexAleatorio = 0, i = 0;
        Random random = new Random();

        switch (opcao) {
            case 1:
                System.out.println("**************************** Brandon the Builder of Immobiles *****************************");
                System.out.println("See our immobile list: ");
                System.out.println();


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
                    System.out.println();
                    this.coisasParaComprar.get(arrayIndexAleatorio.get(i)).imprimirDetalhes();
                }


                System.out.println("Do you want to buy somenthing? \n 1 - Yes | 2 - No");
                opcaoCompra = input.nextInt();

                if (opcaoCompra == 1) {
                    System.out.println("Which item from our list do you want to buy? ");
                    itemComprar = input.nextInt();

                    System.out.print("You bought: ");
                    System.out.println(this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).getNome());  //Imprimir os detalhes do item que o cliente quer comprar

                    if (this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).getCusto() <= jogador.getDinheiro()) {
                        jogador.addAPropriedade(this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)));
                        realizouCompra = true;
                        if (realizouCompra) {
                            System.out.println();
                            System.out.println(" item added to your property list ");
                            jogador.setDinheiro(jogador.getDinheiro() - (this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).getCusto()));
                            jogador.setEstatuto(jogador.getEstatuto() + (this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).getEstatuto()));
                            System.out.println();
                            jogador.mostrarDetalhes();
                            System.out.println();
                        }
                    } else {
                        System.out.println();
                        System.out.println("Ups, you don't have enough money. Account balance: " + jogador.getDinheiro());
                        System.out.println();
                    }
                }


                break;
            case 2:
                System.out.println("**************************** Bravosí Car *****************************");
                System.out.println("Car list: ");
                System.out.println();

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
                    System.out.println();
                    System.out.println("Vehicle list " + i + " : ");
                    this.coisasParaComprar.get(arrayIndexAleatorio.get(i)).imprimirDetalhes();
                }


                System.out.println("Do you wat to buy somenthing? \n 1 - Yes | 2 - No");
                opcaoCompra = input.nextInt();

                if (opcaoCompra == 1) {
                    System.out.println("Wich item from our list you want to buy? ");
                    itemComprar = input.nextInt();
                    System.out.println();
                    System.out.println();
                    System.out.print("You bought: ");
                    System.out.println(this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).getNome());  //Imprimir os detalhes do item que o cliente quer comprar

                    if (this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).getCusto() <= jogador.getDinheiro()) {
                        jogador.addAPropriedade(this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)));
                        realizouCompra = true;
                        if (realizouCompra) {
                            System.out.println();
                            System.out.println("Car add to your vehicle list");
                            System.out.println();
                            jogador.setDinheiro(jogador.getDinheiro() - (this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).getCusto()));
                            jogador.setEstatuto(jogador.getEstatuto() + (this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).getEstatuto()));
                            jogador.mostrarDetalhes();
                        }

                    } else {
                        System.out.println();
                        System.out.println("Ups, you don't have enough money. Account balance: " + jogador.getDinheiro());
                        System.out.println();
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

                    System.out.print("You bought: ");
                    System.out.println(this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).getNome());//Imprimir os detalhes do item que o cliente quer comprar

                    if (this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).getCusto() <= jogador.getDinheiro()) {
                        jogador.addAPropriedade(this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)));
                        realizouCompra = true;
                        if (realizouCompra) {
                            System.out.println("item added to your accessories list");
                            jogador.setDinheiro(jogador.getDinheiro() - (this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).getCusto()));
                            jogador.setEstatuto(jogador.getEstatuto() + (this.coisasParaComprar.get(arrayIndexAleatorio.get(itemComprar)).getEstatuto()));
                            jogador.mostrarDetalhes();
                        }

                    } else {
                        System.out.println("Ups, you don't have enough money. Account balance: " + jogador.getDinheiro());
                    }
                }

                break;
            case 0:
                break;


        }

    }

    /**
     * Método para criar o jogador de acordo com os requisitos minimos
     *
     * @return jogador
     */
    public static Jogador criarPessoa() {
        Scanner input = new Scanner(System.in);

        String nome;
        double dinheiro = 1000000.00;
        int estatuto = 0, educacao = 0, necessidadeRefeicao = 100, necessidadeSocial = 100, necessidadeSono = 100;


        Objetivo objetivoVida = null;
        Profissao profissao = new Profissao("null", 0, false, 0, 0);
        int contador, indexObjetivo, counter;


        System.out.println("Aye, wright dow your caracters first name: ");
        nome = input.next();
        System.out.println("last name: ");
        input.next();

        contador = 1;
        for (Objetivo objetivo : Objetivo.values()) {
            System.out.println(contador++ + ": " + objetivo);
        }

        System.out.println("Which one is your goal, choose a number: ");
        indexObjetivo = input.nextInt();


        if (indexObjetivo >= 1 && indexObjetivo <= Objetivo.values().length) {
            objetivoVida = Objetivo.values()[indexObjetivo - 1];
            System.out.println("You choose: " + objetivoVida);
        } else {
            System.out.println("Invalid choice. Exiting...");
        }

        Jogador novoJogador = new Jogador(nome, dinheiro, objetivoVida, profissao, necessidadeSono, necessidadeRefeicao, necessidadeSocial, estatuto, educacao);

        return novoJogador;

    }

    /**
     * Método para checar a cada ciclo e aplicar a necessidade de sono, refeicao e interacao social
     *
     * @param jogador
     */
    public static void interacaoDiaria(Jogador jogador) {
        jogador.setNecessidadeSono(jogador.getNecessidadeSono() - 25);
        jogador.setNecessidadeRefeicao(jogador.getNecessidadeRefeicao() - 20);
        jogador.setNecessidadeSocial(jogador.getNecessidadeSocial() - 15);
    }

    /**
     * Método para imprimir os detalhes basicos do jogador para cada interação poder saber que está diminuindo
     *
     * @param jogador
     */
    public static void detalhesJogador(Jogador jogador) {
        System.out.print("Player " + jogador.getNome());
        System.out.println(" | Sleep: " + jogador.getNecessidadeSono() + " | Meal: " + jogador.getNecessidadeRefeicao() + "| Social: " + jogador.getNecessidadeSocial());
    }

    /**
     * Método que imprime as opções de npc para que o usuário case, verifica se o estatuto minimo e a capacidade da casa possa abrigar os dois, e aplica o dinheiro ao jogador,
     *
     * @param jogador
     * @return
     */

    boolean casou = false;
    public void casamento(Jogador jogador) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Which one do you choose? ");
        int opcaoCasamento = sc.nextInt();
        NPC novoMembroFamilia = this.getNPC().get(opcaoCasamento - 1);

        System.out.print("You have choosen: ");
        novoMembroFamilia.imprimirDetalhes();
        System.out.println("Minimum Status Level Required : " + novoMembroFamilia.getEstatutoMinimo());
        System.out.println("Your status level: " + jogador.getEstatuto());
        int capacidade = capacidadeImovel(jogador);
        System.out.println("Your house has a place for " + capacidade);

        if (jogador.getEstatuto() >= novoMembroFamilia.getEstatutoMinimo() && capacidade >= 2) {
            System.out.println("********************************************************  WELCOME TO THE SEPT FOR THE WEEDING ************************************************");
            System.out.println("In the sight of the Seven, I hereby seal these two souls, binding them as one for eternity. Look upon one another and say the words.");
            System.out.println("**********************************************************************************************************************************************");
            jogador.addMembroFamilia(novoMembroFamilia);
            jogador.setDinheiro(jogador.getDinheiro() + novoMembroFamilia.getDinheiro());
            System.out.println("Update player information ");
            jogador.mostrarDetalhes();
            casou = true;
        } else {
            System.out.println("You do not meet the minimum status level requirement for marriage.");
            casou = false;
        }

        //30 por dia

        //criar opção para adotar
    }

    public void auxilioSS(Jogador jogador, int dia) {
        if(casou && dia >= 2) {
            jogador.setDinheiro(jogador.getDinheiro() + 30);
        }
    }


    /**
     * método para adotar, ainda não finalizado.
     *
     * @param jogador
     */
    public void adotar(Jogador jogador) {
        int opcao = 0, opcaoMenu;
        int capacidade = capacidadeImovel(jogador);


        Scanner sc = new Scanner(System.in);
        System.out.println(" 1 - Yes or 2 - No");
        opcaoMenu = sc.nextInt();
        switch (opcaoMenu){
            case 1:
                npcFilhos();
                System.out.println("Who you reconize by son/daughter");
                opcao = sc.nextInt();
                NPC novoMembro = this.getNPC().get(opcao+24);
                System.out.println("Your house has a place for " + capacidade);
                if (capacidade >= jogador.getFamilia().size() + 2) {
                    jogador.addMembroFamilia(novoMembro);
                    System.out.println("The bastard has been reconized by the king Ned Stark");
                    jogador.mostrarDetalhes();
                    break;
                } else {
                    System.out.println("You don't have space in your house to adopt! Buy a bigger house");
                    System.out.println(capacidade);
                }
                break;
            case 2:
                break;
        }
    }

    public void pagar10PorMember(Jogador jogador){
        if(jogador.getFamilia().size() >= 1){
            jogador.setDinheiro(jogador.getDinheiro() - ( 10 * jogador.getFamilia().size()));
            System.out.println("As you have been good to the realm bringing us more soldiers, you have a help of 10 coins per member");
        }
    }

    /**
     * Método para verificar ao fim do jogo, se o jogador atingiu seus objetivos.
     * Adicionar o restantes das verificações
     *
     * @param jogador
     */
    public void fimDoJogo(Jogador jogador) {
        System.out.println("FIM DO JOGO");
        double dinheiro = jogador.getDinheiro();
        Objetivo objetivo = jogador.getObjetivoVida();

        //MILIONARIO, FAMILIACOMPLETA, CELEBRIDADE, KING, LORDCOMMANDER, EUNUCO, NOONE, THEHAND, FIRSTMAN, FREEMAN, UNSULLIED, LORD, LADY, QUEEN, SOLDIER, KINGSGUARD, PROTECTOR

        if (objetivo == Objetivo.MILIONARIO) {
            if (dinheiro > 5) {
                System.out.println("YOU WOON");
                jogador.mostrarDetalhes();
            }
        }
    }


    public void verificarNecessidades(Jogador jogador){
        Scanner sc = new Scanner(System.in);
        int escolhaAtividade;

        if (jogador.getNecessidadeSocial() <= 25) {
                escolhaAtividade = sc.nextInt();
                momentoAtual(escolhaAtividade, jogador);
                do {
                    System.out.println("Mad king send his regards, go heal yourself");
                    System.out.println("While you need to socialize, you only option is nº 4");
                    escolhaAtividade = sc.nextInt();
                    momentoAtual(escolhaAtividade, jogador);
                } while (escolhaAtividade != 4);

        }

        if (jogador.getNecessidadeSono() <= 25) {
                escolhaAtividade = sc.nextInt();
                momentoAtual(escolhaAtividade, jogador);
                do {
                    System.out.println("Shame, shame, shame .. as you didn´t sleep you have your torment");
                    System.out.println("While you need to sleep, you only option is nº 2");
                    escolhaAtividade = sc.nextInt();
                    momentoAtual(escolhaAtividade, jogador);
                } while (escolhaAtividade != 2);

        }

        if (jogador.getNecessidadeRefeicao() <= 25) {
                escolhaAtividade = sc.nextInt();
                momentoAtual(escolhaAtividade, jogador);
                do {
                    System.out.println("Highgarden has some lefovers in the kitchen, go get..");
                    System.out.println("While you need to eat, you only option is nº 3");
                    escolhaAtividade = sc.nextInt();
                    momentoAtual(escolhaAtividade, jogador);
                } while (escolhaAtividade != 3);

        }

    }

    public void assistenciaSocial(Jogador jogadorNovo) {
        if (jogadorNovo.getDinheiro() <= 700000) {
            System.out.println("*****    SEGURANÇA-SOCIAL    *****");
            System.out.println();
            System.out.println("Estás com dívida abaixo de 3250!");
            System.out.println("Não tens condições monetárias para ter crias!");
            System.out.println("A segurança social acabou de as levar!");

            for (int i = 0; i < jogadorNovo.getFamilia().size(); i++) {
                if (jogadorNovo.getFamilia().get(i).getEstatutoMinimo() == 0 ) {
                    jogadorNovo.retirarFilhos(jogadorNovo.getFamilia().get(i));
                }
            }
        }
    }

    /**
     * método para aplicar o ciclo de vida ao jogador, a cada interação ocorre o método de momentoAtual, que aplica aquilo que o jogador deseja fazer.
     *
     * @param jogador
     */
    public void cicloVida(Jogador jogador) {
        int escolhaAtividade, dia;
        System.out.println("Welcome to the westeros, if you want to become part a great house you must play ...");

        Scanner sc = new Scanner(System.in);



        for (dia = 1; dia < 5; dia++) { //clico de dias
            System.out.println(" ");
            System.out.println("Dia: " + dia);
            System.out.println(" ");

            if (dia == 2) {
                System.out.println("Gaomagon ao jaelagon naejot jikagon naejot se citadel se become nykeā giēñrȳī?");
                System.out.println(" 1 - Kessa nykeā 2 - daor ");
                System.out.println("Traslated: Do you want to go to the Citadel and become a maester ?");
                System.out.println("1 - Yes ou 2 - No");
                int opcao = sc.nextInt();
                if (opcao == 1) {
                    jogador.setDinheiro(jogador.getDinheiro() - 3000);
                    System.out.println("Aōha debit iksos 3000\n - Tistālior: " + jogador.getDinheiro());
                    System.out.println("Your debit is 3000 \n - bank: " + jogador.getDinheiro());
                    jogador.setEducacao(jogador.getEducacao() + 50);
                    System.out.println(" Aōha education level iksos bē 50");
                    System.out.println("Your education level is up 50");
                }
            }

            if (dia == 3 || dia == 22 || dia == 44 || dia == 66 || dia == 88) {
                System.out.println("Do you want to get marry? ");
                System.out.println("1 - Yes ou 2 - No");
                int opcao = sc.nextInt();
                if (opcao == 1) {
                    //listar todos os npc's do jogo
                    casarComNPC();
                    //casar o jogador com algum dos npc
                    casamento(jogador);

                }
            }

            if(dia >= 3) {
                auxilioSS(jogador, dia);
            }

            pagar10PorMember(jogador);
            assistenciaSocial(jogador);

            for (int momentoDia = 1; momentoDia < 5; momentoDia++) { //momento do dia, manhã, meio dia, tarde ou noite
                switch (momentoDia) {

                    case 1:
                        detalhesJogador(jogador);
                        System.out.println("[09:00] " + dia + "/1987 ..Good morning .. What do you want to do right now?");
                        System.out.println("| 0 - Out  ");
                        System.out.println("| 1 - work ");
                        System.out.println("| 2 - sleep");
                        System.out.println("| 3 - snack");
                        System.out.println("| 4 - playPC, hobby, talkPeople");
                        System.out.println("| 5 - goShop");
                        System.out.println("| 6 - Show Player details ");
                        System.out.println("| 7 - Study ");
                        System.out.println("| 8 -  Choose a profession ");
                        System.out.println("| 9 - Visit Properties");
                        if (dia >= 3 && dia <= 60) {
                            System.out.print("| 10 - Adopt a child");
                            System.out.println(" ");
                        }

                        verificarNecessidades(jogador);
                        escolhaAtividade = sc.nextInt();
                        momentoAtual(escolhaAtividade, jogador);

                        break;
                    case 2:
                        interacaoDiaria(jogador);
                        detalhesJogador(jogador);
                        System.out.println("[12:00]" + dia + "/1987 .. What do you want to do right now?");
                        System.out.println("| 0 - Out  ");
                        System.out.println("| 1 - work ");
                        System.out.println("| 2 - sleep");
                        System.out.println("| 3 - snack");
                        System.out.println("| 4 - playPC, hobby, talkPeople");
                        System.out.println("| 5 - goShop");
                        System.out.println("| 6 - Show Player details ");
                        System.out.println("| 7 - Study ");
                        System.out.println("| 8 -  Choose a profession ");
                        System.out.println("| 9 - Visit Properties");
                        if (dia >= 3 && dia <= 60) {
                            System.out.print("| 10 - Adopt a child");
                            System.out.println(" ");
                        }
                        verificarNecessidades(jogador);
                        escolhaAtividade = sc.nextInt();
                        momentoAtual(escolhaAtividade, jogador);

                        break;
                    case 3:
                        interacaoDiaria(jogador);
                        detalhesJogador(jogador);
                        System.out.println("[16:00] "+ dia + "/1987 .. What do you want to do right now?");
                        System.out.println("| 0 - Out  ");
                        System.out.println("| 1 - work ");
                        System.out.println("| 2 - sleep");
                        System.out.println("| 3 - snack");
                        System.out.println("| 4 - \\\"playPC\\\", \\\"hobby\\\", talkPeople");
                        System.out.println("| 5 - goShop");
                        System.out.println("| 6 - Show Player details ");
                        System.out.println("| 7 - Study ");
                        System.out.println("| 8 -  Choose a profession ");
                        System.out.println("| 9 - Visit Properties");
                        if (dia >= 3 && dia <= 60) {
                            System.out.print("| 10 - Adopt a child");
                            System.out.println(" ");
                        }
                        verificarNecessidades(jogador);
                        escolhaAtividade = sc.nextInt();
                        momentoAtual(escolhaAtividade, jogador);

                        break;
                    case 4:

                        interacaoDiaria(jogador);
                        detalhesJogador(jogador);
                        System.out.println("[20:00]"+ dia +"/1987 Good night .. What do you want to do right now?");
                        System.out.println("| 0 - Out  ");
                        System.out.println("| 1 - work ");
                        System.out.println("| 2 - sleep");
                        System.out.println("| 3 - snack");
                        System.out.println("| 4 - playPC, hobby, talkPeople");
                        System.out.println("| 5 - goShop");
                        System.out.println("| 6 - Show Player details ");
                        System.out.println("| 7 - Study ");
                        System.out.println("| 8 -  Choose a profession ");
                        System.out.println("| 9 - Visit Properties");
                        if (dia >= 3 && dia <= 60) {
                            System.out.print("| 10 - Adopt a child");
                            System.out.println(" ");
                        }
                        verificarNecessidades(jogador);
                        escolhaAtividade = sc.nextInt();
                        momentoAtual(escolhaAtividade, jogador);

                        break;
                }
            }

        }
        fimDoJogo(jogador);
    }


    /**
     * método que aplica ao ciclo as opções diarias que o jogador pode escolher.
     * @param momentoAtual
     * @param jogador
     */
    public void momentoAtual(int momentoAtual, Jogador jogador) {
        int profissaoAtualIndex, changePro;
        Scanner sc = new Scanner(System.in);


        do {
            switch (momentoAtual) {
                case 1: // trabalhar

                    Profissao profissaoAtual = jogador.getProfissaoAtual();
                    if (profissaoAtual.getNome().equals("null")){
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Go lock for a job ");
                        System.out.println("-----------------------------------------------------------------");
                        return;
                    } else {
                        double dinheiroAtual = jogador.getDinheiro();
                        double salarioTrabalhado = jogador.getProfissaoAtual().getSalarioDia();
                        jogador.setDinheiro(dinheiroAtual += salarioTrabalhado);
                        System.out.println("Now your have a new job.");
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("After a long day of work, your account was updated!!");
                        System.out.println("Bank Account: " + dinheiroAtual);
                        System.out.println("-----------------------------------------------------------------");
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
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("Your didn't starve, and you paid for it");
                    System.out.println(" Bank balance : " + jogador.getDinheiro());
                    System.out.println("-----------------------------------------------------------------");
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
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("Player information: ");
                    System.out.println("-----------------------------------------------------------------");
                    jogador.mostrarDetalhes();
                    return;

                case 7: //Formação +2
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("Do you want to learn something so you can change your future? ");
                    System.out.print("Welcome to CESAE, your level of education is up ");
                    System.out.println("-----------------------------------------------------------------");
                    jogador.setEducacao(jogador.getEducacao()+2);
                    System.out.print(" points");
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
                case 10:
                    if(momentoAtual >= 3 && momentoAtual <= 60){
                        System.out.print("| 10 - Adopt a child ");
                        adotar(jogador);
                        return;
                    } else {
                        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                        System.out.println("You have to wait the day to unlock this option!! ");
                        System.out.println("As you are such a smart ass, you lost a chance to do somenthing good for you, go ahead!");
                        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                        System.out.println();
                    }
                    return;
                default:
                    System.out.println("Hummm, You don't have much choice, The kingdom is a place with very short options");
                    System.out.println();
                    return;
            }
        }while (momentoAtual != 0);
    }


}
