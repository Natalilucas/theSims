package View;

import Controller.SimsController;
import Domain.Pessoa.Jogador;

import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class TheSims {

    public TheSims() {
    }

    public static void jogo() throws FileNotFoundException {

        SimsController novoJogo = new SimsController();

        Scanner sc = new Scanner(System.in);
        int opcao;
        System.out.println("Press 1 to play! ");
        System.out.println("Or 0 to leave .. ");
        opcao = sc.nextInt();


            switch (opcao){
            case 1:
                System.out.println("********************************************************************************");
                System.out.println("                      Welcome to our Game of Thrones                            ");
                System.out.println("********************************************************************************");
                System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣄⣀⠀⠀⠀⠀⠙⣟⡓⠲⠶⠤⠤⠶⠒⠛⠙⢓⣲⣦⣤⣄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢛⣯⣉⠉⠉⠩⣭⡭⠴⠶⠶⠿⢄⠀⠀⠀⠀⠀⣀⣀⡤⠽⠿⠿⣶⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⢿⣖⣒⠛⠉⢉⡄⢖⠒⠚⠋⣁⣤⣀⣰⣄⣀⣤⠑⠀⠀⠀⢀⣠⡤⣤⣤⠤⢤⣽⡈⠳⠦⣤⣤⣄⣀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⢀⣀⣀⡤⠤⠖⠛⡽⠋⠉⣹⠏⠀⠀⠙⠲⣄⠰⠬⢭⡀⠁⠈⠁⠑⠲⠯⠑⣦⠀⠙⢄⡈⢙⡾⠷⠒⠉⢉⣉⣀⣉⡉⠛⢷⡶⠦⣤\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣉⣽⡶⠶⠖⣾⠁⠀⠐⠛⡲⠦⠤⢴⠆⠈⠳⣄⠀⠙⠋⠉⠉⠁⠂⠀⠀⠛⢀⣠⠴⠚⣋⡀⠐⠒⠒⠲⣤⡀⠀⠉⠲⡀⠙⣯⠋\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠈⠳⢖⡛⠉⠉⠁⡼⠀⠀⠐⣻⠶⠶⠶⣾⠁⠀⠀⢸⠀⠀⠀⠈⠙⠛⠛⠉⣩⠉⠂⠀⡠⠚⠉⣠⠶⢯⠉⡉⠓⠦⣄⡀⠀⠙⢄⡀⠀⢀⡴⠃⠀\n" +
                        "⠀⠀⠀⢀⣀⣀⣠⡤⠴⠒⣻⠟⢛⣽⣃⣀⢀⣼⠃⠀⠀⠀⡏⠀⣀⣀⣼⠴⠒⠒⢶⠁⠀⠀⠉⠁⠀⠀⡘⠁⣠⡎⠙⠦⠚⢯⣩⠷⡖⣬⣭⠷⢦⣤⣤⡤⠞⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠉⢓⡦⢤⣀⡼⠃⠀⢠⠏⠁⠉⣩⣇⣀⣀⣤⡞⠋⠉⠉⢸⠃⠀⠀⠀⢸⠀⠀⠀⢀⡄⠀⠀⠁⡞⠹⡙⢶⣾⣀⡄⠁⠀⠉⠀⠸⠗⢊⣏⡜⠀⠀⠀⠀⠀\n" +
                        "⠠⣤⡤⠴⠚⠉⣰⠋⠛⠓⠒⢶⡟⠀⠀⢀⡟⠉⠀⠀⢸⡇⠀⠀⠀⣼⢀⣀⣀⣠⡿⠒⠒⠚⣿⠁⠀⠀⠀⠁⠀⠦⣀⠉⠢⢼⡤⢶⡄⡀⠀⢀⡌⠁⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠙⠲⠤⣼⠃⠀⠀⠀⣐⡿⠒⠶⠶⢾⠀⠀⠀⢀⣾⡥⠤⠴⣾⠛⠉⠉⠉⡏⠀⠀⠀⠀⣿⠀⠀⢀⡰⠀⠀⠀⠀⠙⠲⢄⡈⠛⠯⣼⠚⣡⠃⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠒⢹⡏⠉⠛⠉⡟⠀⠀⠀⢀⣾⡤⠴⠖⡿⠁⠀⠀⠀⡇⠀⠀⠀⢠⣇⣤⣤⡤⢶⡷⠶⡞⠉⢀⡴⠚⠉⠉⠉⠲⡀⠈⠒⠀⠀⡹⠁⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⣸⣀⣠⣤⣴⣧⠤⠤⠶⣾⠁⠀⠀⠀⡇⠀⠀⠀⣰⣧⠤⠤⣶⠋⠁⢠⡇⠀⠀⣀⣼⠁⣰⠋⠀⠀⠀⠀⠀⢀⠷⠶⣾⣃⡴⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠁⠀⠀⠀⡟⠀⠀⠀⢀⡇⠀⠀⣀⣰⡷⠖⠒⣻⠏⠀⠀⠀⣿⠀⠀⠸⠗⣻⠋⢉⣼⣼⠃⠀⠀⠀⠀⠀⠀⠀⠀⠐⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⢀⣇⣀⣀⣤⡾⠛⠉⠉⢹⠏⠀⠀⠀⢹⡀⠀⠀⢀⣿⣀⣤⡤⠞⣿⢀⡞⠈⢿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠀⠀⢸⡇⠀⠀⠀⢸⠀⠀⠀⢀⣸⣧⠴⠖⠋⠁⢸⠃⠀⣴⢿⣼⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣇⣀⣀⣤⣿⠗⠛⠋⠉⢹⡇⠀⠀⠀⢀⣿⠀⡼⠁⠀⢻⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠰⡏⠀⠀⠀⠀⣼⣇⣤⡴⠖⢋⣽⣶⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣇⣀⣀⣤⣶⠋⠁⢸⡇⢀⡞⠁⠻⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⡇⠀⠀⣠⣿⣼⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣧⠀⡼⠁⠈⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣴⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
                Jogador novoJogador = SimsController.criarPessoa();
                novoJogo.cicloVida(novoJogador);
                break;
            case 0:
                break;





        }

    }


}


