package ru.shanin.multythreads.data;

import java.util.Random;

public class User {

    private final String firstName, secondName,threadName;

    private final static int firstNameLength, secondNameLength;

    static {
        firstNameLength = 8;
        secondNameLength = 12;
    }

    public User(String threadName) {
        this.threadName=threadName;
        this.firstName = GenData.getNextRandomString(firstNameLength);
        this.secondName = GenData.getNextRandomString(secondNameLength);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    static private class GenData {

        static String getNextRandomString(int length) {
            String strChar = "abcdefghijklmnopqrstuvwxyz";
            Random random = new Random();
            StringBuilder sbRandomString = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                int randomInt = random.nextInt(strChar.length());
                sbRandomString.append(strChar.charAt(randomInt));
            }
            return sbRandomString.toString();
        }
    }
}


