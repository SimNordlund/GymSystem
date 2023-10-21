import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {
    FileHandler fh = new FileHandler();

    //Kollar så att vi läser in listan och sorterar den direkt. Kontroll av information i listans index.
    @Test
    public void readMembershipTest() {
        List<String> memberListSorted = fh.readMembership();
        assert (memberListSorted.get(0).equals("7703021234, Alhambra Aromes, 2023-07-01"));
        assert (memberListSorted.get(1).equals("8204021234, Bear Belle, 2019-12-02"));
        assert (!memberListSorted.get(1).equals("8204024321, Öl Belle, 2024-12-02"));
        assert (memberListSorted.get(13).equals("7805211234, Nahema Ninsson, 2023-08-04"));
    }

    //Kollar så att vi konverterat den sorterade listan till en lista av objekt. Använder assertEquals för felmeddelanden.
    @Test
    public void convertToObjectListTest() {
        List<Member> ObjectMemberList = fh.convertToObjectList();
        Member test1 = ObjectMemberList.get(0);
        Member test2 = ObjectMemberList.get(2);
        Member test3 = ObjectMemberList.get(12);

        assertEquals("7703021234", test1.getSocialSecurityNumber(), "Test1 personnummer fail");
        assertEquals("8512021234", test2.getSocialSecurityNumber(), "Test2 personnummer fail");
        assertEquals("Alhambra Aromes", test1.getNamePerson(), "Test1 namn fail");
        assertEquals("Chamade Coriola", test2.getNamePerson(), "Test2 namn fail");
        assertEquals("2023-07-01", test1.getDateMembership(), "Test1 datum fail");
        assertEquals("2018-03-12", test2.getDateMembership(), "Test2 datum fail");
        assertNotEquals("1337-03-33", test3.getNamePerson());
    }

    //Skriver påhittade personer till fil. Läser sedan av filen för att se så att utskrift skett korrekt och i rätt format.
    @Test
    public void writeMemberToFileTest() {
        String tempNumber = "9401021337";
        String tempName = "Sören Salami";
        String tempNumber2 = "9512116666";
        String tempName2 = "Ernst Holm";
        fh.writeMemberToFile(tempNumber, tempName);
        fh.writeMemberToFile(tempNumber2, tempName2);

        String tempTestLine1 = null;
        String tempTestLine2 = null;
        List<String> memberList = new ArrayList<>();

        try {
            BufferedReader buf = new BufferedReader(new
                    FileReader(String.valueOf(fh.getPathWriter())));
            String temp;
            while ((temp = buf.readLine()) != null) {
                memberList.add(temp);
            }
            for (String s : memberList) {
                if (s.contains("Salami")) {
                    tempTestLine1 = s;
                } else if (s.contains("Holm")) {
                    tempTestLine2 = s;
                }
            }
            assert (Objects.equals(tempTestLine1,"Personnummer: 9401021337, Namn: Sören Salami. Tränade: " + LocalDate.now() + ".")); //Hanterar eventuellt NullPointException med Objects.equals.
            assert (Objects.equals(tempTestLine2,"Personnummer: 9512116666, Namn: Ernst Holm. Tränade: " + LocalDate.now() + "."));
        }
        catch (IOException e) {
            System.out.println("I/O Fel");
        }
    }
}
