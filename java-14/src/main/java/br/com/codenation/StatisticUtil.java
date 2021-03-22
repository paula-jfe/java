package br.com.codenation;

import java.util.Arrays;

public class StatisticUtil {

	public static int average(int[] elements) {
		int sum = 0;
		for (int number : elements) {
			sum += number;
		}
		return sum / (elements.length);
		// Seria melhor retornar float ou double.
	}

	public static int mode(int[] elements) {
		int moreRepeatedNumber = 0;
		int referenceCounter = 0;
		for (int i : elements) {
			int counter = 0;
			for (int j : elements) {
				if (i == j) {
					counter += 1;
				}
			}
			if (counter > referenceCounter) {
				referenceCounter = counter;
				moreRepeatedNumber = i;
			}
		}
		return moreRepeatedNumber;
	}

	public static int median(int[] elements) {
		Arrays.sort(elements);
		int medianPosition = 0;
		int medianValue = 0;
		if (elements.length % 2 != 0) {
			medianPosition = (int) Math.floor(elements.length / 2);
			medianValue = elements[medianPosition];
		} else {
			int afterPosition = (int) Math.ceil(elements.length / 2);
			int beforePosition = ((int) Math.ceil(elements.length / 2)) - 1;
			medianValue = (elements[afterPosition] + elements[beforePosition]) / 2;
		}
		return medianValue;
		// Seria melhor retornar float ou double.
	}

	public static void main(String[] args) {
		int array[] = {1,2,3,4,5};
		System.out.println(median(array));
	}
}