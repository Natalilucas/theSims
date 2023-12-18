import Domain.Pessoa.Pessoa;
import Domain.Shopping.Shopping;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Pessoa clienteManoel = new Pessoa("Manoel", 2300);

        Shopping shopping = new Shopping();

        shopping.Vender(clienteManoel);

    }
}
