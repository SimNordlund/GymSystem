import javax.swing.*;

public class Person { //Superklass

    private String namePerson;
    private String socialSecurityNumber;

    //Konstruktorer
    public Person() {
    }

    public Person(String socialSecurityNumber, String name) {
        this.socialSecurityNumber = socialSecurityNumber;
        this.namePerson = name;
    }

    //Frågar om namn eller personnummer. Returnerar svar av användaren. Vid isTest 1 & 2 så körs olika tester.
    public String askNameOrSSNumber(int isTest) {
        String userInput;
        if (isTest == 1) {
            userInput = "Samuel Testsson";
            return userInput;
        } else if (isTest == 2) {
            return null;
        } else {
            userInput = JOptionPane.showInputDialog(null, "Ange fullständigt namn eller" + //Ber användaren ange namn eller prs nr.
                    " personnummer utan mellanslag, 10 siffror");
            if (userInput == null) {
                System.out.println("Programmet avslutas");
                System.exit(1);
            }
            return userInput.trim();
        }
    }

    //Getters & Setters
    public String getNamePerson() {
        return namePerson;
    }

    public void setNamePerson(String namePerson) {
        this.namePerson = namePerson;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }
}
