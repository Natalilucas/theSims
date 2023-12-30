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
     * @param jogador
     */
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

    /**
     * Método para verificar a capacidade das propriedades comprada por daquele jogador
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
    public void imprimirProfissao(){
        for (int i = 0; i < escolherProfissao.size(); i++) {
            System.out.print("Option " + (i+1));
            escolherProfissao.get(i).imprimirDetalhes();
        }
    }

    /**
     * Método que imprime a lista de npc's para utilizar para o casamento
     */
    public void casarComNPC(){
        for (int i = 0; i < obterNPC.size(); i++) {
            System.out.print("Bride or Groom " + (i+1) + " : ");
            obterNPC.get(i).imprimirDetalhes();
        }
    }

    public void npcFilhos(){
        for (int i = 0; i < obterNPC.size(); i++) {

            //colocar condição para imprimir somente os filhos(foreach)
            obterNPC.get(i).imprimirDetalhes();
        }
    }

    /**
     * Método para imprimir os acessórios do jogador
     * @param jogador
     */
    public void listarAcessorios(Jogador jogador){
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

    /**
     * Método para efetuar a ação de vender propriedades(imoveis/carros/acessorios)
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

    /**
     * Método para criar o jogador de acordo com os requisitos minimos
     * @return jogador
     */
    public static Jogador criarPessoa() {
        Scanner input = new Scanner(System.in);

        String nome;
        double dinheiro = 1000000.00;
        int estatuto = 0, educacao = 0, necessidadeRefeicao = 100, necessidadeSocial = 100, necessidadeSono = 100;


        Objetivo objetivoVida = null;
        Profissao profissao = new Profissao( "null", 0, false, 0, 0);
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

        Jogador novoJogador = new Jogador(nome, dinheiro, objetivoVida , profissao, necessidadeSono, necessidadeRefeicao, necessidadeSocial, estatuto, educacao);

        return novoJogador;

    }

    /**
     * Método para checar a cada ciclo e aplicar a necessidade de sono, refeicao e interacao social
     * @param jogador
     */
    public static void interacaoDiaria(Jogador jogador){
        jogador.setNecessidadeSono(jogador.getNecessidadeSono()-25);
        jogador.setNecessidadeRefeicao(jogador.getNecessidadeRefeicao()-20);
        jogador.setNecessidadeSocial(jogador.getNecessidadeSocial()-15);
    }

    /**
     * Método para imprimir os detalhes basicos do jogador para cada interação poder saber que está diminuindo
     * @param jogador
     */
    public static void detalhesJogador(Jogador jogador){
        System.out.print("Player " + jogador.getNome());
        System.out.println(" | Sleep: " + jogador.getNecessidadeSono()+ " | Meal: " + jogador.getNecessidadeRefeicao() + "| Social: " + jogador.getNecessidadeSocial());
    }

    /**
     * Método que imprime as opções de npc para que o usuário case, verifica se o estatuto minimo e a capacidade da casa possa abrigar os dois, e aplica o dinheiro ao jogador,
     * @param jogador
     * @return
     */
    public boolean casamento(Jogador jogador){
        Scanner sc = new Scanner(System.in);

        System.out.println("Which one do you choose? ");
        int opcaoCasamento = sc.nextInt();
        NPC novoMembroFamilia = this.getNPC().get(opcaoCasamento-1);

        System.out.print("You have choosen: ");
        novoMembroFamilia.imprimirDetalhes();
        System.out.println("Minimum Status Level Required : " + novoMembroFamilia.getEstatutoMinimo());
        System.out.println("Your status level: " + jogador.getEstatuto());
        int capacidade = capacidadeImovel(jogador);
        System.out.println("Your house has a place for "+ capacidade);

        if (jogador.getEstatuto() >= novoMembroFamilia.getEstatutoMinimo() && capacidade >= 2){
            System.out.println("********************************************************  WELCOME TO THE SEPT FOR THE WEEDING ************************************************");
            System.out.println("In the sight of the Seven, I hereby seal these two souls, binding them as one for eternity. Look upon one another and say the words.");
            System.out.println("**********************************************************************************************************************************************");
            jogador.addMembroFamilia(novoMembroFamilia);
            jogador.setDinheiro(jogador.getDinheiro() + novoMembroFamilia.getDinheiro());
            System.out.println("Update player information ");
            jogador.mostrarDetalhes();
            return true;
        } else {
            System.out.println("You do not meet the minimum status level requirement for marriage.");
            return false;
        }

        //30 por dia

        //criar opção para adotar
    }

    /**
     * método para adotar, ainda não finalizado.
     * @param jogador
     */
    public void adotar(Jogador jogador){
        int opcao;
        int capacidade = capacidadeImovel(jogador);

        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to adopt a little bird?? 1 - Yes or 2 - No");
        opcao = sc.nextInt();
        NPC novoMembro = this.getNPC().get(opcao-1);
        if(opcao == 1){
            System.out.println("Your house has a place for "+ capacidade);
            if(capacidade > 3){
                jogador.addMembroFamilia(novoMembro);
            }
        } else {
            System.out.println("You don't have means to adopt! ");
        }
    }

    /**
     * Método para verificar ao fim do jogo, se o jogador atingiu seus objetivos.
     * Adicionar o restantes das verificações
     * @param jogador
     */
    public void fimDoJogo(Jogador jogador){
        System.out.println("FIM DO JOGO");
        double dinheiro = jogador.getDinheiro();
        Objetivo objetivo = jogador.getObjetivoVida();

        //MILIONARIO, FAMILIACOMPLETA, CELEBRIDADE, KING, LORDCOMMANDER, EUNUCO, NOONE, THEHAND, FIRSTMAN, FREEMAN, UNSULLIED, LORD, LADY, QUEEN, SOLDIER, KINGSGUARD, PROTECTOR

        if(objetivo == Objetivo.MILIONARIO){
            if(dinheiro > 5){
                System.out.println("YOU WOON");
                jogador.mostrarDetalhes();
            }
        }
    }

    /**
     * método para aplicar o ciclo de vida ao jogador, a cada interação ocorre o método de momentoAtual, que aplica aquilo que o jogador deseja fazer.
     * @param jogador
     */
    public void cicloVida(Jogador jogador){
        int escolhaAtividade, dia;

        System.out.println("Welcome to the westeros, if you want to become part a great house you must play ...");

        Scanner sc = new Scanner(System.in);
        for (dia = 1; dia < 3; dia ++) { //clico de dias
            System.out.println(" ");
            System.out.println("Dia: " + dia);
            System.out.println(" ");

            if(dia == 2){
                System.out.println("Gaomagon ao jaelagon naejot jikagon naejot se citadel se become nykeā giēñrȳī?");
                System.out.println(" 1 - Kessa nykeā 2 - daor ");
                System.out.println("Traslated: Do you want to go to the Citadel and become a maester ?");
                System.out.println("1 - Yes ou 2 - No");
                int opcao = sc.nextInt();
                if(opcao == 1){
                    jogador.setDinheiro(jogador.getDinheiro()-3000);
                    System.out.println("Aōha debit iksos 3000\n - Tistālior: " + jogador.getDinheiro());
                    System.out.println("Your debit is 3000 \n - bank: " + jogador.getDinheiro());
                    jogador.setEducacao(jogador.getEducacao() + 50);
                    System.out.println(" Aōha education level iksos bē 50");
                    System.out.println("Your education level is up 50");
                }
            }

            if(dia == 3){
                System.out.println("Do you want to get marry? ");
                System.out.println("1 - Yes ou 2 - No");
                int opcao = sc.nextInt();
                if(opcao == 1){
                    //listar todos os npc's do jogo
                    casarComNPC();
                    //casar o jogador com algum dos npc
                    if(casamento(jogador)){
                        for (int i = 0; i > dia; i++) {
                            //TÁ MAL = jogador.setDinheiro(jogador.getDinheiro() + 30 * dia);
                        }
                        System.out.println("Now that you are married, the small council decide that you'll have a help of 30 per day");
                        jogador.mostrarDetalhes();
                    }



                }
            }






            for (int momentoDia = 1; momentoDia < 5  ; momentoDia++) { //momento do dia, manhã, meio dia, tarde ou noite
                switch (momentoDia){
                    case 1:
                        detalhesJogador(jogador);
                        System.out.println("It's morning .. What do you want to do right now?");
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
                        if(dia >= 22 && dia <= 60){
                            System.out.print("| 10 - Adopt a child");
                        }
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
                        System.out.println("It's morning .. What do you want to do right now?");
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
                        if(dia >= 22 && dia <= 60){
                            System.out.print("| 10 - Adopt a child");
                        }
                        escolhaAtividade = sc.nextInt();
                        momentoAtual(escolhaAtividade, jogador);
                        break;
                    case 3:
                        interacaoDiaria(jogador);
                        detalhesJogador(jogador);
                        System.out.println("It's morning .. What do you want to do right now?");
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
                        if(dia >= 22 && dia <= 60){
                            System.out.print("| 10 - Adopt a child");
                        }
                        escolhaAtividade = sc.nextInt();
                        momentoAtual(escolhaAtividade, jogador);
                        break;
                    case 4:
                        interacaoDiaria(jogador);
                        detalhesJogador(jogador);
                        System.out.println("It's morning .. What do you want to do right now?");
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
                        if(dia >= 22 && dia <= 60){
                            System.out.print("| 10 - Adopt a child");
                        }
                        escolhaAtividade = sc.nextInt();
                        momentoAtual(escolhaAtividade, jogador);
                        break;
                }
            }
        } fimDoJogo(jogador);



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
                    if(momentoAtual >= 22 && momentoAtual <= 60){
                        System.out.print("| 10 - Adopt a child ");
                        adotar(jogador);
                    }
                    return;
            }
        }while (momentoAtual != 0);
    }


}
