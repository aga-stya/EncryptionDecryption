package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
            EncryptionDecryption ed = new EncryptionDecryption(args);
            ed.startProcess();
        } catch (IllegalArgumentException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
