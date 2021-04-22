package models;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Huffman {
    private HashMap<String, String> codeMap;
    private HashMap<Character, Node> codeList;

    public Huffman() {
        this.codeMap = new HashMap<>();
        this.codeList = new HashMap<>();
    }

    public String compress(String filename, String destiny) {

        String content = readFile(filename);

        //CRIAÇÃO DA ESTRUTURA DA ARVORE E TABELA DE CODIGOS
        ArrayList<Node> nodes = new ArrayList<>(codeList.values());

        Collections.sort(nodes);
        Trie trie = new Trie(nodes);

        String[] codeTable = trie.buildCode();

        codeMap = new HashMap<>();
        for (int i = 0; i < codeTable.length; i++) {
            if (codeTable[i] != null) {
                getCodeMap().put(String.valueOf((char) i), codeTable[i]);
            }
        }

        //ESCRITA DO ARQUIVO USANDO A TABELA DE CÓDIGOS
        try {
            OutputStream outputStream = new FileOutputStream(destiny);
            String buffer = "";
            for (String ch : content.split("")) {
                buffer += getCodeMap().get(ch);
            }

            for (String b : buffer.split("(?<=\\G........)")) {
                outputStream.write(Integer.parseInt(b, 2));
            }

            outputStream.close();

            //RETORNA A STRING COM OS BITS COMPRIMIDOS
            return buffer;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String readFile(String filename) {
        codeList = new HashMap<>();
        String content = "";
        try {
            InputStream fis = new FileInputStream(filename);
            int data = fis.read();
            while (data != -1) {
                content += (char) data;
                if (codeList.containsKey((char) data)) {
                    codeList.get((char) data).freq += 1;
                } else {
                    codeList.put((char) data, new Node((char) data, true));
                }
                data = fis.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public HashMap<String, String> getCodeMap() {
        return codeMap;
    }
}