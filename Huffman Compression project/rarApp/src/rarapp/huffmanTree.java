package rarapp;

import java.util.ArrayList;

public class huffmanTree {

    intString GG = new intString();
    intString root;

    public intString buildHaffmanTree(ArrayList<intString> A) {
        if (!(A.isEmpty())) {
            if (A.size() == 1) {

                root = A.remove(0);
                return root;
            }
            while (A.size() > 1) {
                GG.sortAlphabeticaly(A);
                GG.sort(A);
                intString temp = GG.Merge(0, 1, A);

                temp.left = A.remove(0);
                temp.right = A.remove(0);

                GG.add(temp, A);

            }
            root = A.get(0);
            return A.get(0);
        }
        intString temp = new intString(-999, "Empty list");
        root = temp;
        return temp;
    }

    ////////////////////////
    public String[] getHash() {
        String[] a = new String[256];
        for (int i = 0; i < root.getLetters().length(); i++) {

            a[(int) (root.getLetters().charAt(i))] = getCode(root.getLetters().charAt(i));
        }

        return a;
    }

    /////////////////////
    public String getCode(char k) {
        intString temp = root;
        String s = String.valueOf(k);
        String code = "";

        //left =0
        //right=1
        if (root.getLetters().equals(s)) {
            return "0";
        }
        if (temp.getLetters().contains(s)) {
            while ((temp.left != null) && (temp.right != null)) {
                if (temp.left.getLetters().contains(s)) {
                    code = code + "0";
                    temp = temp.left;
                } else {
                    code = code + "1";
                    temp = temp.right;
                }

            }
            return code;
        } else {
            return "-1";
        }

    }

    /////////////////////////////
    public int getCount(char k) {
        intString temp = root;
        String s = String.valueOf(k);

        if (root.getLetters().equals(s)) {
            return root.getNum();
        }
        if (temp.getLetters().contains(s)) {
            while ((temp.left != null) && (temp.right != null)) {
                if (temp.left.getLetters().contains(s)) {

                    temp = temp.left;
                } else {

                    temp = temp.right;
                }

            }

            return temp.getNum();
        } else {
            return -1;
        }
    }

    /////////////////
    public String getHeader() {
        String header = "";
        for (int i = 0; i < root.getLetters().length(); i++) {
            header = header + root.getLetters().charAt(i) + (Integer.toHexString((getCount(root.getLetters().charAt(i))))).length() + Integer.toHexString((getCount(root.getLetters().charAt(i))));
        }
        String temp = "" + header.length();
        header = "" + temp.length() + header.length() + header;
        return header;
    }
}
