package br.com.codenation.calculadora;


public class CalculadoraSalario {
	public static long calcularSalarioLiquido(double salarioBase) {
		if (salarioBase < 1039.0) {
			return Math.round(0.0);
		} else {
			double salarioInss = calcularInss(salarioBase);
			return Math.round(calcularIrrf(salarioInss));
		}
	}

	public static double calcularIrrf(double salarioInss) {
		if (salarioInss <= 3000.0) {
			return salarioInss;
		} else if (salarioInss <= 6000.0) {
			return salarioInss - (salarioInss * 0.075);
		} else {
			return salarioInss - (salarioInss * 0.15);
		}
	}

	public static double calcularInss(double salarioBase) {
		if (salarioBase <= 1500.0) {
			return salarioBase - (salarioBase * 0.08);
		} else if (salarioBase <= 4000.0) {
			return salarioBase - (salarioBase * 0.09);
		} else {
			return salarioBase - (salarioBase * 0.11);
		}
	}

	public static void main(String[] args) {
		System.out.println(calcularSalarioLiquido(10000.0));
	}
}
