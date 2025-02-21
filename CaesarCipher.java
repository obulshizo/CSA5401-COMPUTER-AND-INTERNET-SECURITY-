package shree;
import java.util.Scanner;

class CaesarCipher {  // Corrected class name
    public static String encode(String enc, int offset) {
        offset = offset % 26 + 26;
        StringBuilder encoded = new StringBuilder();
        for (char i : enc.toCharArray()) {
            if (Character.isLetter(i)) {
                if (Character.isUpperCase(i)) {
                    encoded.append((char) ('A' + (i - 'A' + offset) % 26));
                } else {
                    encoded.append((char) ('a' + (i - 'a' + offset) % 26));
                }
            } else {
                encoded.append(i);
            }
        }
        return encoded.toString();
    }

    public static String decode(String enc, int offset) {
        return encode(enc, 26 - offset);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String msg = sc.nextLine();
        System.out.println("Input : " + msg);
        System.out.printf("Encrypted Message : ");
        System.out.println(CaesarCipher.encode(msg, 3));
        System.out.printf("Decrypted Message : ");
        System.out.println(CaesarCipher.decode(CaesarCipher.encode(msg, 3), 3));
        sc.close();
    }
}
