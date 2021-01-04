package email;

import org.apache.commons.text.WordUtils;
import utils.Departments;
import utils.PasswordGenerator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Email {

    private String firstName;
    private String lastName;
    private String password;
    private String department;
    private int mailboxCapacity = 500;
    private String email;
    private String alternateEmail;
    private String companyName = "TheCompany";

    public Email(){
    }

    public Email createEmailAccount(){
        inputName();
        this.department = inputDepartment();
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        this.password = passwordGenerator.generatePassword();
        createEmail();
        return this;
    }

    private void inputName(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the employees first name: ");
            this.firstName = scanner.next().toLowerCase().trim();
            this.firstName = WordUtils.capitalize(this.firstName);
        System.out.println("Please enter the employees last name: ");
            this.lastName = scanner.next().toLowerCase().trim();
            this.lastName =WordUtils.capitalize(this.lastName);
        System.out.println(String.format("Employee name: %s %s \nCorrect? Enter \"0\" \nIncorrect? Enter \"1\"", firstName, lastName ));
        //invalid input check
        int question = 4;
        boolean correctInput = false;
        while(!correctInput){
            try {
                question = scanner.nextInt();
                correctInput = (question==1||question==0) ? true : false;
                if(!correctInput) {
                    System.out.println("Invalid selection.");
                }
            } catch (InputMismatchException e){
                System.out.println("Please enter a valid code.");
                scanner.nextLine();
            }
        }
        boolean correctName = (question==0) ? true : false;
        while (!correctName){
            inputName();
            correctName = true;
        }
    }


    private String inputDepartment(){
        System.out.println(String.format("Enter the department code \n\"1\" for %s\n\"2\" for %s\n\"3\" for %s",
                Departments.SALES,
                Departments.DEVELOPMENT,
                Departments.ACCOUNTING));
        System.out.println( "Enter \"0\" to leave department unset.");

        Scanner scanner = new Scanner(System.in);
        int question = 4;
        boolean correctInput = false;
        while(!correctInput){
            try {
                question = scanner.nextInt();
                correctInput = (question==1||question==2||question==3||question==0) ? true : false;
                if(!correctInput) {
                    System.out.println("Invalid department selection.");
                }
            } catch (InputMismatchException e){
                System.out.println("Please enter a valid department code.");
                scanner.nextLine();
            }
        }

        if(question == 1){ return Departments.SALES.toString();}
            else if(question == 2){ return Departments.DEVELOPMENT.toString();}
            else if(question == 3){ return Departments.ACCOUNTING.toString();}
        else {return "unset";}
    }

    public String getDepartment() {
        return department;
    }

    private String createEmail(){
        boolean departmentHasBeenSet = (this.department.equals(null)||this.department.equals("unset")) ? false : true;
        if(departmentHasBeenSet){
            this.email = String.format("%s.%s@%s.%s.com", this.firstName, this.lastName, this.department, this.companyName).toLowerCase();
        } else {
            this.email = String.format("%s.%s@%s.com", this.firstName, this.lastName, this.companyName).toLowerCase();
        }
        return this.email;
    }

    public void setMailboxCapacity(int capacity){
        this.mailboxCapacity = capacity;
    }

    public int getMailboxCapacity(){
        return mailboxCapacity;
    }

    public void setAlternateEmail(String alternateEmail){
        this.alternateEmail = alternateEmail;
    }

    public String getAlternateEmail(){
        return alternateEmail;
    }

    public String changePassword(String password){
        this.password = password;
        return password;
    }

    public boolean passwordHasBeenGenerated(){
        return (this.password.length()>0) ? true : false;
    }

    public String showInfo(){
        return String.format("Name: %s %s\n" +
                "email.Email: %s\n" +
                "Mailbox Capacity: %sMB\n" +
                "Password Generated: %s\n",
                firstName, lastName, email, mailboxCapacity, String.valueOf(passwordHasBeenGenerated()));
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
