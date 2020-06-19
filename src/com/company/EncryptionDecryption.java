package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class EncryptionDecryption {
    private String mode = "enc";
    private String key = "0";
    private String data = "";
    private PrintWriter output;
    private String in = null;

    EncryptionDecryption(String[] args) throws FileNotFoundException, IOException{
        output = new PrintWriter(System.out);
        readArgs(args);
    }

    public String encryption(StringBuilder toConvert, int key) {
        for (int i = 0; i < toConvert.length(); i++) {
            int afterAdd = toConvert.charAt(i) + key;
            toConvert.setCharAt(i, (char)afterAdd);
        }
        return toConvert.toString();
    }

    public String decryption(StringBuilder toConvert, int key) {
        for (int i = 0; i < toConvert.length(); i++) {
            int afterAdd = toConvert.charAt(i) - key;
            toConvert.setCharAt(i, (char)afterAdd);

        }
        return toConvert.toString();
    }

    public void startProcess() throws IOException{
        switch (mode) {
            case "enc":
                String res = encryption(new StringBuilder(data), Integer.parseInt(key));
                //System.out.println ("res: " + res);
                this.output.write(res);
                this.output.close();
                break;
            case "dec":
                this.output.write(decryption(new StringBuilder(data), Integer.parseInt(key)));
                this.output.close();
                break;
            default:
                break;
        }
    }

    public void readArgs(String[] args) throws FileNotFoundException, IOException{
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode" :
                    if (i + 1 == args.length || args[i + 1].startsWith("-")) {
                        throw new IllegalArgumentException ("missing argument for -mode");
                    }
                    this.mode = args[i + 1];
                    break;
                case "-key":
                    if (i + 1 == args.length || args[i + 1].startsWith("-")) {
                        throw new IllegalArgumentException ("missing argument for -key");
                    } else if (!args[i + 1].matches("[0-9]+")) {
                        throw new IllegalArgumentException ("invalid argument for -key");
                    }
                    this.key = args[i + 1];
                    break;
                case "-data":
                    if (i + 1 == args.length || args[i + 1].startsWith("-")) {
                        throw new IllegalArgumentException ("missing argument for -data");
                    }
                    this.data = args[i + 1];
                    break;
                case "-out":
                    if (i + 1 == args.length || args[i + 1].startsWith("-")) {
                        throw new IllegalArgumentException ("missing argument for -out");
                    }
                    this.output = new PrintWriter(new File(args[i + 1]));
                    break;
                case "-in":
                    if (i + 1 == args.length || args[i + 1].startsWith("-")) {
                        throw new IllegalArgumentException ("missing argument for -in");
                    }
                    Scanner sc = new Scanner (new File (args[i + 1]));
                    this.data = sc.nextLine();
                    break;
                default:
                    //throw new IllegalStateException("Unexpected value: " + args[i]);
            }
        }
    }
}
