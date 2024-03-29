package org.example.algoritmo_guloso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SistemaBackup {

    public static interface Lote {
        int[] getTamanhosArquivos();

        int getTamanhoFita();
    }

    private static class NovoLote implements Lote {

        @Override
        public int[] getTamanhosArquivos() {
            return new int[]{
                    70, 10, 20, 30, 50, 100, 90, 10, 20
            };
        }

        @Override
        public int getTamanhoFita() {
            return 100;
        }
    }

    private class Fita {
        private int armazenado;
        private int numeroArquivos;

        public Fita(int armazenado) {
            this.armazenado = armazenado;
            this.numeroArquivos = 1;
        }

        public int getArmazenado() {
            return armazenado;
        }

        public void adicionarArmazenamento(int armazenamento) {
            this.armazenado += armazenamento;
        }

        public int getNumeroArquivos() {
            return numeroArquivos;
        }

        public void adicionarArquivos() {
            this.numeroArquivos++;
        }
    }

    public int getNumeroMinimoFitas(final Lote lote) {
//        inicializa um array com o tamanho dos arquivos
        int[] tamanhosArquivos = lote.getTamanhosArquivos();
//        ordena o array de tamanhosArquivos
        Arrays.sort(tamanhosArquivos);

        int fitas = 0;
        List<Fita> listaFitas = new ArrayList<>();
//         percorre o array do maior para o menor
        for (int i = tamanhosArquivos.length - 1; i > -1; i--) {
            boolean armazenado = false;

            for (Fita fita : listaFitas) {
//                verifica se a soma do que está aramazenado com o indice do array <= ao tamanho da fita
//                e se o número de arquivos é menor que 2
                if (((fita.getArmazenado() + tamanhosArquivos[i]) <= lote.getTamanhoFita()) &&
                        (fita.getNumeroArquivos() < 2)) {
                    fita.adicionarArmazenamento(tamanhosArquivos[i]);
                    fita.adicionarArquivos();
                    armazenado = true;
                    break;
                }
            }
//          verifica se foi armazenado, se não foi, acrescenta uma nova fita e instancia uma nova fita contendo o
//            arquivo que não foi adicionado e adiciona na lista de fitas
            if (!armazenado) {
                fitas++;
                listaFitas.add(new Fita(tamanhosArquivos[i]));
            }
        }
        return fitas;
    }

    public static void main(String[] args) {
        SistemaBackup sistemaBackup = new SistemaBackup();
        Lote lote = new NovoLote();

        System.out.println(sistemaBackup.getNumeroMinimoFitas(lote));
    }
}