package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

	public static List<Integer> fibonacci() {
		List<Integer> sequence = new ArrayList<>();
		sequence.add(0);
		sequence.add(1);

		int result = 0;
		int s = 2;

		do {
			int element = sequence.get(s - 2) + sequence.get(s - 1);
			sequence.add(element);
			result = element;
			s += 1;
		} while (result < 350);

		return sequence;
	}

	public static Boolean isFibonacci(Integer a) {
		if (fibonacci().contains(a)) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println(fibonacci());
	}
}