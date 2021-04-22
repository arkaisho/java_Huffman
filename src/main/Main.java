package main;

import models.Huffman;
import models.Node;
import models.Trie;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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

        //CONTENT CAPTURE AND DICTIONARY INITIALIZATION
        Huffman huffman = new Huffman();
        String compressed = huffman.compress(filename,destiny);
        System.out.println(compressed);

    }
}