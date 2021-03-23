package cn.hokilin.studydemo.test;

/**
 * @author linhuankai
 * @date 2021/3/18 21:47
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()){
            String str = in.nextLine();
            String d = in.nextLine();
            System.out.println(Main.getLength(str, d));
        }
    }

    public static void main1(String[] args) {
        Scanner in = new Scanner(System.in);
        String extStr = "";
        while (in.hasNextLine()) {
            String s = extStr + in.nextLine();
            int length = s.length();
            if (length == 0) {
                continue;
            } else if (length < 8) {
                s = s + "0000000";
            } else {
                extStr = s.substring(8);
            }
            System.out.println(s.substring(0, 8));
        }
        while(extStr.length() > 0) {
            if (extStr.length() >= 8) {
                String s = extStr.substring(0, 8);
                System.out.println(s);
                extStr = extStr.replace(s, "");
            } else {
                extStr = extStr + "0000000";
                System.out.println(extStr.substring(0, 8));
                extStr = "";
            }
        }
    }

    private static int getLength(String str, String d) {
        int length = str.length();
        if (length == 0) {
            return 0;
        }
        int count = 0;
        for(int i = 0; i < str.length(); i++) {
            char s = str.charAt(i);
            if (Character.isDigit(s) || Character.isWhitespace(s)) {
                continue;
            }
            if (d.toUpperCase().toCharArray()[0] == Character.toUpperCase(s)) {
                count++;
            }
        }
        return count;
    }


}
