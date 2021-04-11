package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// Seguindo orientações Gustavo Gomes e Neto
public class CalculadorDeClasses implements Calculavel {
    private BigDecimal somaSeBigDecimal(final Object objeto, final Class annotationClasse) {
        Field[] atributos = objeto.getClass().getDeclaredFields();
        List<BigDecimal> lista = new ArrayList<>();

        for (Field f: atributos) {
            if (f.getType().equals(BigDecimal.class)) {
                f.setAccessible(true);
                try {
                    if (f.get(objeto) != null && f.isAnnotationPresent(annotationClasse)) {
                        lista.add((BigDecimal) f.get(objeto));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return (lista.size() > 0) ? lista.stream().reduce(BigDecimal.ZERO, BigDecimal::add) : BigDecimal.ZERO;
    }

    @Override
    public BigDecimal somar(final Object objeto) {
        return somaSeBigDecimal(objeto, Somar.class);
    }

    @Override
    public BigDecimal subtrair(final Object objeto) {
        return somaSeBigDecimal(objeto, Subtrair.class);
    }

    @Override
    public BigDecimal totalizar(final Object objeto) {
        return this.somar(objeto).subtract(this.subtrair(objeto));
    }
}
