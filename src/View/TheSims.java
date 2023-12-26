package View;

import Controller.SimsController;
import Domain.Pessoa.Jogador;

import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class TheSims {

    public TheSims() {
    }

    public static void jogoMethod() throws FileNotFoundException {

        SimsController shopping = new SimsController();
        SimsController novoJogador = new SimsController();

        Jogador novoJogaddor = SimsController.criarPessoa();
        System.out.println("You choose to be: " + novoJogaddor.getObjetivoVida());
        //System.out.println(novoJogaddor.getNome() + ", After a long jorney to the north, buy what you want from us in our shopping");
        //novoJogaddor.mostrarDetalhes();
        //System.out.println("So " + novoJogaddor.getNome() + ", there's is a easy way for you to get money in this city. Go to work.. Here's a opportunity for you to choose your new job.. ");

        novoJogador.cicloVida(novoJogaddor);

        //SimsController.imprimirProfissao();*/


        //shopping.vender(novoJogaddor);

        // escolher profissão

        //novoJogaddor.mostrarDetalhes();


    }
}


