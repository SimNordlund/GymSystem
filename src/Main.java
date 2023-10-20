import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int isTest = 0;
        FileHandler fh = new FileHandler();
        Member mb = new Member();
        Membership typeOfMembership; //Håller koll på medlemskapet, Enum.

        while (true) {
            String userInput = mb.askNameOrSSNumber(isTest); //Frågar användaren om personnummer eller namn
            List<Member> ObjectMemberList = fh.convertToObjectList(); //Läser memberInfo.txt och skapar en lista av objekt.
            Member memberObject = mb.findMemberObject(userInput, ObjectMemberList); //Hämtar det matchade objektet om det finns.

            //Om personen ej finns med i register så skrivs det ut. Null betyder att personen inte är i listan.
            if (memberObject == null) {
                typeOfMembership = Membership.NEVERMEMBER;
                JOptionPane.showMessageDialog(null, typeOfMembership.Type);
            }
            //Skriver ut om man har betalt avgift inom 1 år eller inte.
            else {
                typeOfMembership = mb.compareDate(memberObject.getDateMembership());
                JOptionPane.showMessageDialog(null, typeOfMembership.Type);
                //Om man har betalt avgiften inom 1 år sparas det ner i PT:ns fil.
                if (typeOfMembership.equals(Membership.CURRENTMEMBER)) {
                    fh.writeMemberToFile(memberObject.getSocialSecurityNumber(), memberObject.getNamePerson());
                }
            }
            //Om användaren inte trycker Ja så avslutas programmet. Annars startas det om.
            boolean runProgram = mb.askSearchAgain(); //Frågar om användaren vill köra igen.
            if (!runProgram) {
                System.out.println("Programmet avslutas");
                break;
            }
        }
    }
}
