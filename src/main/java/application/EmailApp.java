package application;

import email.Email;

public class EmailApp {

    public static void main(String[] args) {
        Email email = new Email().createEmailAccount();
        System.out.println(email.showInfo());
    }
}
