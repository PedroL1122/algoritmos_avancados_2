package org.example.exercicios;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Troco {

    public static Map<Integer, Integer> darTroco(int quantia, int[] moedas) {
        Arrays.sort(moedas);
        Map<Integer, Integer> troco = new HashMap<>();

        for (int i = moedas.length - 1; i >= 0; i--) {
            int moedaAtual = moedas[i];
            int quantidadeMoedas = quantia / moedaAtual;

            if (quantidadeMoedas > 0) {
                troco.put(moedaAtual, quantidadeMoedas);
                quantia %= moedaAtual;
            }
        }

        return troco;
    }

    public static void main(String[] args) {
        int quantia1 = 18;
        int[] moedas1 = {5, 2, 1};
        Map<Integer, Integer> troco1 = darTroco(quantia1, moedas1);

        System.out.println("Quantia: " + quantia1);
        System.out.println("Moedas disponíveis: " + Arrays.toString(moedas1));
        System.out.println("Troco: " + troco1);
    }
}
