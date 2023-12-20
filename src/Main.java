import Domain.Objetivo;
import Domain.Pessoa.Jogador;
import Domain.Pessoa.NPC;
import Domain.Pessoa.Pessoa;
import Domain.Pessoa.Profissao;
import Domain.Propriedade.Propriedade;
import Domain.Shopping.Shopping;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        //Pessoa clienteManoel = new Pessoa("Manoel", 2300);

        //Shopping shopping = new Shopping();

        //shopping.Vender(clienteManoel);

        Profissao novaProfissao = new Profissao("Advogado", 3000.00, true, 120, 200);

        Propriedade novaPropriedadeNova = new Propriedade("AP2", 40000.00, 20);

        ArrayList<Propriedade> novaPropriedade = new ArrayList<>();
        novaPropriedade.add(novaPropriedadeNova);


        ArrayList<NPC> familia = new ArrayList<>();
        NPC novoMembro = new NPC("filho", 400, 100);
        familia.add(novoMembro);

        Jogador novoJogador = new Jogador("Nuno", 3000, Objetivo.DESEMPREGADO,novaProfissao, 5, 7, 8, 320, 90, novaPropriedade , familia );
        novoJogador.mostrarDetalhes();
    }
}
