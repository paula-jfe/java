package challenge;

import java.util.ArrayList;
import java.util.List;


public class CriptografiaCesariana implements Criptografia {
    public static String checkText(String texto, String type) {
        if (texto == null ) {
            throw new NullPointerException("O texto não pode ser nulo.");
        } else if (texto == "" ) {
            throw new IllegalArgumentException("O texto não pode estar vazio.");
        } else if (type == "regress") {
            int factor = -3;
            return changeText(texto, factor);
        } else {
            int factor = 3;
            return changeText(texto, factor);
        }
    }

    public static String changeText(String texto, int factor) {
        char[] newText = texto.toCharArray();
        List<Character> encryptedList = new ArrayList<>();

        for (int i = 0; i < newText.length; i++) {
            char element = newText[i];
            if(element == ' ' || Character.isDigit(element)) {
                encryptedList.add(element);
            } else {
                char textElement = (char) (element + factor);
                encryptedList.add(textElement);
            }
        }

        String encryptedString = encryptedList.toString()
                .replaceAll("[\\[\\]]", "")
                .replaceAll(", ", "");
        return encryptedString;
    }

    @Override
    public String criptografar(String texto) {
        String type = "advance";
        return checkText(texto.toLowerCase(), type);
    }

    @Override
    public String descriptografar(String texto) {
        String type = "regress";
        return checkText(texto.toLowerCase(), type);
    }

    public static void main(String[] args) {
        System.out.println(checkText("d oljhlud udsrvd pduurp vdowrx vreuh r fdfkruur fdqvdgr", "regress"));
    }
}
