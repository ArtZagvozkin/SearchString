package com.company;
import java.util.*;

public class SearchString {
    public static void main(String[] args) {
        int countWords = 10000000;
        MyArray myArr = new MyArray(countWords);
        Map<Integer, List<String>> myMap = new HashMap<>();

        Scanner console = new Scanner(System.in);
        String menu = "0";

        while (!menu.equals("3")) {
            System.out.println("1: Generate " + countWords + " random words");
            System.out.println("2: Search string");
            System.out.println("3: Exit");
            System.out.print("Type action: ");
            menu = console.nextLine();
            System.out.println("\n\n");

            switch (menu) {
                case "1": { //Generate 1000000 random words
                    String randString;

                    //clear hash
                    for (int i=0; i<26; i++) {
                        List<String> list = new ArrayList<>();
                        myMap.put(i + 97, list);
                    }

                    //fill array and hash_table
                    for (int i=0; i<countWords; i++) {
                        randString = getRandStr();

                        myArr.addWord(i, randString); //add to static array

                        int key = randString.charAt(0);
                        myMap.get(key).add(randString); //add to hash
                    }

                    System.out.println("Generated " + countWords +" words");
                } break;
                case "2": { //Search string
                    long startTime, timeSpent, count;
                    System.out.println("Search string");
                    System.out.print("Type string: ");
                    String inpStr = console.nextLine();

                    //Search in array
                    startTime = System.currentTimeMillis();
                    count = myArr.searchWord(inpStr);
                    timeSpent = System.currentTimeMillis() - startTime;
                    System.out.println("\nSearch in array:");
                    System.out.println("Total found: " + count);
                    System.out.println("Time spent: " + timeSpent + "ms");

                    //Search in hash
                    count = 0;
                    startTime = System.currentTimeMillis();
                    int key = inpStr.charAt(0);
                    if (key >= 97 && key <= 123) {
                        for (String value : myMap.get(key)) {
                            if (value.length() >= inpStr.length()) {
                                if (inpStr.equals(value.substring(0, inpStr.length()))) { //string comparison
                                    count++;
//                                System.out.println(myArray[i]); //print result
                                }
                            }
                        }
                    }
                    timeSpent = System.currentTimeMillis() - startTime;
                    System.out.println("\nSearch in hash:");
                    System.out.println("Total found: " + count);
                    System.out.println("Time spent: " + timeSpent + "ms");
                } break;
            }
            System.out.println("\n\n");
        }
        console.close();
    }

    static String getRandStr() {
        Random rand = new Random();
        String word = "";
        int indChar = 0;
        int lenWord = rand.nextInt(25) + 1; //length from 1 to 25

        //generate random word
        for (int i=0; i<lenWord; i++) {
            indChar = rand.nextInt(26) + 97;
            word += Character.toString((char)(indChar));
        }

        return word;
    }
}

class MyArray {
    String[] strArray;
    int countWords;

    public MyArray(int _countWords) {
        countWords = _countWords;
        strArray = new String[countWords];
    }

    public void addWord(int _index, String _word) {
        strArray[_index] = _word;
    }

    public void printArray() {
        for (int i=0; i<countWords; i++) {
            System.out.println(strArray[i]);
        }
    }

    public int searchWord(String _inpStr) {
        int count = 0;
        for (int i=0; i<countWords; i++) {
            if (strArray[i].length() >= _inpStr.length()) {
                if (_inpStr.equals(strArray[i].substring(0, _inpStr.length()))) { //string comparison
//                    System.out.println(myArray[i]); //print result
                    count++;
                }
            }
        }
        return count;
    }
}