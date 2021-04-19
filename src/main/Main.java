package main;

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
        HashMap<Character, Node> codeList = new HashMap<>();
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
        System.out.println("\nFILE CONTENT:\n" + content);

        //BINARY TRIE CREATION
        ArrayList<Node> nodes = new ArrayList<>(codeList.values());
        Collections.sort(nodes);
        Trie trie = new Trie(nodes);
        System.out.println("\nBINARY TRIE:\n" + trie.root);

        //CHAR-CODE MAP CREATION
        System.out.println("\nCODE TABLE:");
        String[] codeTable = trie.buildCode();

        HashMap<String, String> codeMap = new HashMap<>();
        for (int i = 0; i < codeTable.length; i++) {
            if (codeTable[i] != null) {
                System.out.println((char) i + ":" + codeTable[i]);
                codeMap.put(String.valueOf((char) i), codeTable[i]);
            }
        }

        //CONTENT COMPRESSION
        System.out.println("\nBYTES COMPRESSED:");
        try {
            OutputStream outputStream = new FileOutputStream(destiny);
            String buffer = "";
            for (String ch : content.split("")) {
                buffer += codeMap.get(ch);
            }
            for(String b: buffer.split("(?<=\\G........)")){
                System.out.println(b+"("+Integer.parseInt(b,2)+")");
                outputStream.write(Integer.parseInt(b,2));
            }
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
