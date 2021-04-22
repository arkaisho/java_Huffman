package main;

import models.Huffman;

public class Main {
    public static void main(String[] args) {

        //PARAMETERS READING
        String filename = "";
        String destiny = "";
        for (String arg : args) {
            if (arg.contains("file=")) {
                filename = arg.replace("file=", "");
            }
            if (arg.contains("destiny=")) {
                destiny = arg.replace("destiny=", "");
            }
        }
        System.out.println("CONFIGS:");
        System.out.println("Origin: " + filename);
        System.out.println("Destiny: " + destiny);

        //LEITURA DE CONTEUDO DE ARQUIVO E COMPRESSÃO
        Huffman huffman = new Huffman();
        String compressed = huffman.compress(filename,destiny);
        System.out.println(compressed);
    }
}