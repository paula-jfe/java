package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CalculadorDeClasses implements Calculavel {
    private BigDecimal somaSeBigDecimal(Object objeto) {
        BigDecimal vazio = BigDecimal.ZERO;
        Field[] atributos = objeto.getClass().getDeclaredFields();
        List<BigDecimal> somarLista = new ArrayList<>();
        List<BigDecimal> subtrairLista = new ArrayList<>();
        // Exemplo https://www.guj.com.br/t/java-8-somatorio-de-lista-de-bigdecimal/320578/2

        for (Field f: atributos) {
            if (f.getType().equals(BigDecimal.class)) {
                if (f.isAnnotationPresent(Somar.class)) {
                    try {
                        f.setAccessible(true);
                        somarLista.add((BigDecimal) f.get(objeto));
                        System.out.println("somar");
                        System.out.println(somarLista.toString());
                        return somarLista.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else if (f.isAnnotationPresent(Subtrair.class)) {
                    try {
                        f.setAccessible(true);
                        subtrairLista.add((BigDecimal) f.get(objeto));
                        System.out.println("subtrair");
                        return subtrairLista.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    return vazio;
                }
            } else {
                return vazio;
            }
        }
        return null;
    }

    @Override
    public BigDecimal somar(Object objeto) {
        return somaSeBigDecimal(objeto);
    }

    @Override
    public BigDecimal subtrair(Object objeto) {
        return somaSeBigDecimal(objeto);
    }

    @Override
    public BigDecimal totalizar(Object objeto) {
        return this.somar(objeto).subtract(this.subtrair(objeto));
    }
}
