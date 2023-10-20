import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    Member mb = new Member();

    //Kollar så att metoden kan ta emot en sträng eller om null returneras.
    //Görs mer kontroller av "mocking-names" i findMemberObjectTest som kontrollerar olika input av användaren.
    @Test
    public void askNameOrSSNumberTest() {
        int isTest1 = 1;
        String inputUser = mb.askNameOrSSNumber(isTest1);
        assertNotNull(inputUser); //Kollar så att en string returneras.
        assertEquals(inputUser, "Samuel Testsson");

        int isTest2 = 2;
        String inputNull = mb.askNameOrSSNumber(isTest2);
        assertNull(inputNull); //Kollar så att null returneras.
    }
}


