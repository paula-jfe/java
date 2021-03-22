import com.challenge.desafio.CalculadorDeClasses;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Teste novoTeste = new Teste(new BigDecimal(5), new BigDecimal(9));
        CalculadorDeClasses calculadora = new CalculadorDeClasses();
        System.out.println(calculadora.subtrair(novoTeste));
    }
}
