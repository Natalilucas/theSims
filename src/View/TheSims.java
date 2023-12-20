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

        Jogador novoJogaddor = SimsController.criarPessoa();
        System.out.println("You choose to be: " + novoJogaddor.getObjetivoVida());
        System.out.println(novoJogaddor.getObjetivoVida() + " " + novoJogaddor.getNome() + " After a long jorney to the north, buy what you want from us in our shopping");
        shopping.vender(novoJogaddor);
        novoJogaddor.mostrarDetalhes();
        novoJogaddor.

    }


}
