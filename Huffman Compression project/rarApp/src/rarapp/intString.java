package rarapp;

import java.util.ArrayList;

public class intString {

    private int num;
    private String letters;
    intString left;
    intString right;

    /////////////////////
    public intString(int num, String letters) {
        this.num = num;
        this.letters = letters;

    }

    ///////////////
    public intString() {
    }

    //////////////////
    @Override
    public String toString() {

        return "(" + letters + ":" + num + ")";
    }
    ////////////////////

    public void set(int num, String letters) {
        this.num = num;
        this.letters = letters;
    }

    /////////////////
    public int getNum() {
        return num;
    }

    //////////////////
    public String getLetters() {
        return letters;
    }

    /////////////
    public void setNum(int num) {
        this.num = num;

    }
    /////////

    public void setLetters(String letters) {

        this.letters = letters;
    }
//////////////

    public void addTolist(int N, String S, ArrayList<intString> A) {
        intString temp = new intString(N, S);
        A.add(temp);

    }
    ///////////////////////

    public void increase(String S, ArrayList<intString> A) {

        for (int i = 0; i < A.size(); i++) {

            if (A.get(i).letters.equals(S)) {
                A.get(i).num++;
            }
        }
    }

    /////////////////////////////
    public boolean ifExists(String S, ArrayList<intString> A) {
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i).letters.equals(S)) {
                return true;
            }
        }
        return false;
    }
///////////////////////////////////////
    //Insertion Sort of intString object

    public void sort(ArrayList<intString> A) {
        int n = A.size();
        for (int i = 1; i < n; ++i) {
            intString key = A.get(i);

            int j = i - 1;

            while (j >= 0 && A.get(j).num > key.num) {

                A.set(j + 1, A.get(j));
                j--;
            }
            A.set(j + 1, key);
        }
    }

    ///////////////////////////
    public void sortAlphabeticaly(ArrayList<intString> A) {
        int n = A.size();
        for (int i = 1; i < n; ++i) {
            intString key = A.get(i);

            int j = i - 1;

            while (j >= 0 && A.get(j).getLetters().charAt(0) > key.getLetters().charAt(0)) {

                A.set(j + 1, A.get(j));
                j--;
            }
            A.set(j + 1, key);
        }
    }

    ///////////////////////////
    public intString Merge(int object1, int object2, ArrayList<intString> A) {
        int N = A.get(object1).num + A.get(object2).num;
        String S = A.get(object1).letters + A.get(object2).letters;

        intString temp = new intString(N, S);
        return temp;
    }
    ////////////////////////////

    public void add(intString O, ArrayList<intString> A) {
        A.add(O);

    }
    /////////////////////////

}
