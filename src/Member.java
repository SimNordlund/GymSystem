import javax.swing.*;
import java.time.*;
import java.util.List;

public class Member extends Person { //Subklass, ärver Person
    private String dateMembership;
    private Membership typeMembership; //Enum. Tre olika utfall av medlemskap.

    //Konstruktorer
    public Member() {
    }

    public Member(String socialSecurityNumber, String name, String dateMembership) {
        super(socialSecurityNumber, name);
        this.dateMembership = dateMembership;
    }

    //Letar upp om angiven person finns med i listan. Returnerar då objekt annars null.
    public Member findMemberObject(String userInput, List<Member> ObjectMemberList) {
        Member memberObject = null;

        for (Member searchMember : ObjectMemberList) { //Söker på namn
            if (searchMember.getNamePerson().equals(userInput)) {
                memberObject = searchMember;
                break;
            } else if ((searchMember.getSocialSecurityNumber().equals(userInput))) { //Söker på personnummer
                memberObject = searchMember;
                break;
            }
        }
        return memberObject;
    }

    //Jämför datumen i filen med dagens datum. Returnerar om de finns giltigt medlemskap eller ej.
    public Membership compareDate(String memberDate) {
        LocalDate dateNow = LocalDate.now();
        LocalDate dateOfMembership = LocalDate.parse(memberDate);
        Period periodMember = Period.between(dateOfMembership, dateNow);
        int membershipTime = periodMember.getYears(); //Tar fram om man varit medlem 1 år.

        if (membershipTime < 1) {
            return Membership.CURRENTMEMBER;
        }
        return Membership.PREVIOUSMEMBER;
    }

    //Frågar om användaren vill fortsätta använda programmet. Inget test för denna, la in senare för att kunna loopa.
    public boolean askSearchAgain() {
        boolean runProgram = false;
        int userAnswer = JOptionPane.showConfirmDialog(null, "Vill du kolla upp en till person?");
        if (userAnswer == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Ok! Programmet startas om.");
            runProgram = true;
        }
        return runProgram;
    }

    //Getters och Setters.
    public Membership getTypeMembership() {
        return typeMembership;
    }

    public void setTypeMembership(Membership typeMembership) {
        this.typeMembership = typeMembership;
    }

    public String getDateMembership() {
        return dateMembership;
    }

    public void setDateMembership(String dateMembership) {
        this.dateMembership = dateMembership;
    }
}

