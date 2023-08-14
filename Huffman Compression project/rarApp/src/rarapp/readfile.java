package rarapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class readfile {

    String textinFile = "";

    public void getStats(String filePath, ArrayList<intString> A) throws FileNotFoundException, IOException {
        intString x = new intString();
        File inputFile = new File(filePath);
        FileReader fr = new FileReader(inputFile);

        BufferedReader br = new BufferedReader(fr);

        int c = 0;
        while ((c = br.read()) != -1) {

            if (x.ifExists(String.valueOf((char) c), A)) {
                x.increase(String.valueOf((char) c), A);
            } else {
                x.addTolist(1, String.valueOf((char) c), A);
            }

        }
    }

    ///////////////////////
    public String get8bit(int ascii) {
        char charBits[] = {'0', '0', '0', '0', '0', '0', '0', '0'};

        String s = Integer.toBinaryString((char) ascii);
        int k = 1;
        for (int i = 7; i > (7 - s.length()); i--) {

            charBits[i] = s.charAt(s.length() - k++);
        }
        String str = new String(charBits);
        return str;
    }

    ////////////////////
    public void compres(String filePath, String filePath2) throws FileNotFoundException, IOException {
        File output = new File(filePath2);
        FileWriter fw = new FileWriter(output);
        ArrayList<intString> charCounter = new ArrayList<>();
        readfile rf = new readfile();

        rf.getStats(filePath, charCounter);
        huffmanTree hFtree = new huffmanTree();
        hFtree.buildHaffmanTree(charCounter);
        String hash[] = hFtree.getHash();

        File inputFile = new File(filePath);
        FileReader fr = new FileReader(inputFile);
        BufferedReader br = new BufferedReader(fr);

        int c = 0;
        String tempHash = "";
        String temp = "";
        //////
        int c2 = 0;
        FileReader fr2 = new FileReader(inputFile);
        BufferedReader br2 = new BufferedReader(fr2);

        int fileLeanth = 0;
        for (int g = 1; g <= hFtree.root.getNum(); g++) {
            c2 = br2.read();
            tempHash = tempHash + hash[c2];

            while (tempHash.length() >= 8) {
                temp = tempHash.substring(0, 8);
                tempHash = tempHash.substring(8);
                fileLeanth++;

            }
        }
        br2.close();
        fr2.close();
        ////////
        tempHash = "";
        temp = "";

        fw.write(hFtree.getHeader());
        String fL = Integer.toHexString(fileLeanth);

        fw.write("" + fL.length());
        fw.write(fL);
        for (int g = 1; g <= hFtree.root.getNum(); g++) {
            c = br.read();
            tempHash = tempHash + hash[c];

            while (tempHash.length() >= 8) {
                temp = tempHash.substring(0, 8);
                tempHash = tempHash.substring(8);
                fw.write(Integer.parseInt(temp, 2));

            }

        }
        fw.write(tempHash);

        fw.flush();
        fw.close();
    }
    ///////////////

    public void unCompres(String filePath, String filePath2) throws FileNotFoundException, IOException {
        File output2 = new File(filePath2);
        FileWriter fw = new FileWriter(output2);
        readfile rf = new readfile();
        ArrayList<intString> charCounter = new ArrayList<>();
        File inputFile = new File(filePath);
        FileReader fr = new FileReader(inputFile);
        BufferedReader br = new BufferedReader(fr);
        //char readed ascii
        int c = br.read();
        int headerLeanthDigits = Integer.parseInt(((char) c) + "", 10);
        int headerLeanth = 0;
        //read header leanth
        String tempString = "";
        for (int i = 1; i <= headerLeanthDigits; i++) {
            tempString = tempString + (char) br.read();
        }
        headerLeanth = Integer.parseInt(tempString, 10);
        huffmanTree hf = new huffmanTree();
        intString ff = new intString();

        int count = 0;
        int countLenth = 0;
        String hex = "";
        char charAtNode;
        int stopCounter = 0;
        while (stopCounter < headerLeanth) {
            hex = "";
            charAtNode = (char) br.read();
            stopCounter++;
            tempString = (char) br.read() + "";
            stopCounter++;
            countLenth = Integer.parseInt(tempString, 10);

            for (int w = 1; w <= countLenth; w++) {
                hex = hex + (char) br.read();
                stopCounter++;
            }
            count = Integer.parseInt(hex, 16);
            ff.addTolist(count, charAtNode + "", charCounter);

        }
        ////////

        stopCounter = Integer.parseInt(((char) br.read()) + "", 10);
        String HexfileLeanth = "";
        for (int w = 1; w <= stopCounter; w++) {
            HexfileLeanth = HexfileLeanth + (char) br.read();

        }
        int fileLeanth = Integer.parseInt(HexfileLeanth, 16);
        hf.buildHaffmanTree(charCounter);
        String hash[] = hf.getHash();

        String tempHash = "";
        String temp = "";
        for (int z = 1; z <= fileLeanth; z++) {
            tempHash = tempHash + rf.get8bit(br.read());

            for (int v = 0; v <= tempHash.length(); v++) {
                temp = temp + tempHash.substring(0, 1);

                tempHash = tempHash.substring(1);
                for (int o = 0; o < 256; o++) {
                    if (temp.equals(hash[o])) {
                        fw.write((char) o);

                        temp = "";
                    }
                }
            }
            ////

        }

        //////////
        while ((c = br.read()) != -1) {
            tempHash = tempHash + (char) c;
        }
        ///////
        while (tempHash.length() != 0) {
            for (int v = 0; v <= tempHash.length(); v++) {
                temp = temp + tempHash.substring(0, 1);
                tempHash = tempHash.substring(1);
                for (int o = 0; o < 256; o++) {
                    if (temp.equals(hash[o])) {
                        fw.write((char) o);

                        temp = "";
                    }
                }
            }
        }
        fw.flush();
        fw.close();

        //////////////////
    }
}
