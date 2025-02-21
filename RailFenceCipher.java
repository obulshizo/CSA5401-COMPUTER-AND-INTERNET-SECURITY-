package shree;
import java.util.*;
public class RailFenceCipher {
    public static String encrypt(String text, int key) {
        char[][] rail = new char[key][text.length()];
        boolean down = false;
        int row = 0, col = 0;
        
        for (char ch : text.toCharArray()) {
            if (row == 0 || row == key - 1) {
                down = !down;
            }
            rail[row][col++] = ch;
            row += down ? 1 : -1;
        }
        
        StringBuilder result = new StringBuilder();
        for (char[] r : rail) {
            for (char c : r) {
                if (c != 0) {
                    result.append(c);
                }
            }
        }
        return result.toString();
    }
    public static String decrypt(String cipher, int key) {
        char[][] rail = new char[key][cipher.length()];
        boolean down = false;
        int row = 0, col = 0;
        for (int i = 0; i < cipher.length(); i++) {
            if (row == 0) {
                down = true;
            } else if (row == key - 1) {
                down = false;
            }
            rail[row][col++] = '*';
            row += down ? 1 : -1;
        }
        int index = 0;
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < cipher.length(); j++) {
                if (rail[i][j] == '*' && index < cipher.length()) {
                    rail[i][j] = cipher.charAt(index++);
                }
            }
        }
        
        StringBuilder result = new StringBuilder();
        row = 0;
        col = 0;
        for (int i = 0; i < cipher.length(); i++) {
            if (row == 0) {
                down = true;
            } else if (row == key - 1) {
                down = false;
            }
            result.append(rail[row][col++]);
            row += down ? 1 : -1;
        }
        return result.toString();
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text to encrypt: ");
        String text = scanner.nextLine();
        int key = 2;
        
        String encrypted = encrypt(text, key);
        System.out.println("Encrypted Text: " + encrypted);
        
        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted Text: " + decrypted);
    }
}
