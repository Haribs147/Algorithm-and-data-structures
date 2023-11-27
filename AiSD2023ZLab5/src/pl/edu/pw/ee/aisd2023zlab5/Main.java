package pl.edu.pw.ee.aisd2023zlab5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Main {

    public static void main(String[] args) throws IOException {
        String filePath = "F:\\AiSD\\niemanie.txt"; //C:\Users\mjago\AiSD\niemanie.txt
        int maxSize=10000;
        // wyjmuje dwa sk³adam je w jeden z left i right i go wk³adam spowrotem
        Heap huffHeap = new Heap(maxSize * 2 + 1);
        huffHeap = huffHeap.buildHeap(maxSize, filePath);

        HuffTree tree = new HuffTree(huffHeap);

        tree.printTreeWithValues(tree.getRoot(),0);
        tree.buildDictionary();
        //tree.printTreeWithCodes( tree.getRoot(),"");
        tree.printDictionary();

        String wynik = tree.dictionaryEncoding();
        System.out.println(wynik);
    }
}
