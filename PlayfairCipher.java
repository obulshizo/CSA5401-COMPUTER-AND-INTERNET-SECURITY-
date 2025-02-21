package shree;
import java.util.Scanner;
class PlayfairCipher {
    private static char[][] charTable;
    private static Position[] positions;
    static class Position {
        int x, y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static String prepareText(String s, boolean chgJtoI) {
        s = s.toUpperCase().replaceAll("[^A-Z]", "");
        return chgJtoI ? s.replace("J", "I") : s.replace("Q", "");
    }
    private static void createTbl(String key, boolean chgJtoI) {
        charTable = new char[5][5];
        positions = new Position[26]; // Array to store character positions
        String s = prepareText(key + "ABCDEFGHIJKLMNOPQRSTUVWXYZ", chgJtoI);
        int len = s.length();
        for (int i = 0, k = 0; i < len; i++) {
            char c = s.charAt(i);
            if (positions[c - 'A'] == null) {
                charTable[k / 5][k % 5] = c;
                positions[c - 'A'] = new Position(k % 5, k / 5);
                k++;
            }
        }
    }

    private static String codec(StringBuilder txt, int dir) {
        int len = txt.length();
        for (int i = 0; i < len; i += 2) {
            char a = txt.charAt(i);
            char b = txt.charAt(i + 1);
            Position posA = positions[a - 'A'];
            Position posB = positions[b - 'A'];
            int row1 = posA.y, col1 = posA.x;
            int row2 = posB.y, col2 = posB.x;
            if (row1 == row2) { 
                col1 = (col1 + dir + 5) % 5;
                col2 = (col2 + dir + 5) % 5;
            } else if (col1 == col2) { 
                row1 = (row1 + dir + 5) % 5;
                row2 = (row2 + dir + 5) % 5;
            } else { 
                int temp = col1;
                col1 = col2;
                col2 = temp;
            }
            txt.setCharAt(i, charTable[row1][col1]);
            txt.setCharAt(i + 1, charTable[row2][col2]);
        }
        return txt.toString();
    }
    private static String encode(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i += 2) {
            if (i == sb.length() - 1) {
                sb.append(sb.length() % 2 == 1 ? 'X' : "");
            } else if (sb.charAt(i) == sb.charAt(i + 1)) {
                sb.insert(i + 1, 'X');
            }
        }
        return codec(sb, 1);
    }
    private static String decode(String s) {
        return codec(new StringBuilder(s), -1);
    }
    public static void main(String[] args) {
    	Scanner sc=new Scanner(System.in);
        String key = sc.nextLine();
        String txt = sc.nextLine();
        boolean chgJtoI = true;
        createTbl(key, chgJtoI);
        String enc = encode(prepareText(txt, chgJtoI));
        System.out.println("Input Message : " + txt);
        System.out.println("Encrypted Message : " + enc);
        System.out.println("Decrypted Message : " + decode(enc));
    }
}
