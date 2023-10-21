import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

class MemberTest {
    Member mb = new Member();
    FileHandler fh = new FileHandler();

    //Kollar så att programmet klarar av att söka i objektlistan och hitta personer.
    @Test
    public void findMemberObjectTest() {
        String test1 = "Alhambra Aromes";
        String test2 = "7907281234";
        String test3 = "Thompa Tång";
        List<Member> ObjectMemberList = fh.convertToObjectList();

        //test1
        Member memberObject = mb.findMemberObject(test1, ObjectMemberList);
        assertNotNull(memberObject);
        assertEquals("Alhambra Aromes", memberObject.getNamePerson(), "Fel i test1 - namn");
        assertEquals("2023-07-01", memberObject.getDateMembership(), "Fel i test1 - datum");
        assertEquals("7703021234", memberObject.getSocialSecurityNumber(), "Fel i test1 - personnummer");

        //test2
        Member memberObject2 = mb.findMemberObject(test2, ObjectMemberList);
        assertNotNull(memberObject2);
        assertEquals("Mitsuko Mayotte", memberObject2.getNamePerson(), "Fel i test2 - namn");
        assertEquals("2019-12-22", memberObject2.getDateMembership(), "Fel i test2 - datum");
        assertEquals("7907281234", memberObject2.getSocialSecurityNumber(), "Fel i test2 - personnummer");

        //test3
        Member memberObject3 = mb.findMemberObject(test3, ObjectMemberList);
        assertNull(memberObject3); //Om den är null, finns ej listan.
    }

    //Kollar om medlemskapet är inom 1 år. Jämför med dagens datum. Returnerar medlem eller tidigare medlem
    @Test
    public void compareDate() {
        LocalDate currentDate1 = LocalDate.now();
        LocalDate DateSixMonthsAgo = currentDate1.minusMonths(6);
        String Test1 = DateSixMonthsAgo.toString();
        System.out.println(Test1);

        LocalDate currentDate2 = LocalDate.now();
        LocalDate DateTwentyMonthsAgo = currentDate2.minusMonths(20);
        String Test2 = DateTwentyMonthsAgo.toString();

        assert (mb.compareDate(Test1).equals(Membership.CURRENTMEMBER));
        assert (mb.compareDate(Test2).equals(Membership.PREVIOUSMEMBER));
    }
}
