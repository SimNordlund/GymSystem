import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

public class FileHandler {
    private Path pathReader = Paths.get("src/memberInfo.txt");
    private Path pathWriter = Paths.get("src/PTinfo.txt");

    //Läser filen memberInfo. Sparar ner allt i en lista, skapar sedan en ny sorterad lista som returneras.
    public List<String> readMembership() {
        List<String> memberList = new ArrayList<>();
        List<String> memberListSorted = new ArrayList<>();
        try {
            BufferedReader buf = new BufferedReader(new
                    FileReader(String.valueOf(pathReader))); //Try-with-resources.
            String temp;
            while ((temp = buf.readLine()) != null) {
                memberList.add(temp);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Filen finns ej");
        } catch (IOException e) {
            System.out.println("I/O fel uppstod");
        } catch (Exception e) {
            System.out.println("Okänd fel inträffat");
        }
        //Gör en ny lista där alla uppgifter sparas på samma rad för varje kund.
        for (int i = 0; i < memberList.size(); i += 2)
            if (i + 1 < memberList.size()) {
                String addLines = memberList.get(i) + ", " + memberList.get(i + 1);
                memberListSorted.add(addLines);
            }
        return memberListSorted;
    }

    //Konverterat den sorterade listan till en lista av objekt av personer som finns med i memberInfo.txt.
    public List<Member> convertToObjectList() {
        List<Member> ObjectMemberList = new ArrayList<>();
        List<String> tempSortedMemberList = readMembership();

        for (String s : tempSortedMemberList) {
            String[] splitData = s.split(",");
            for (int j = 0; j < splitData.length; j++) {
                splitData[j] = splitData[j].trim();
            }
            Member mb = new Member(splitData[0], splitData[1], splitData[2]);
            ObjectMemberList.add(mb);
        }
        return ObjectMemberList;
    }

    //Skriver ut data till PT:ns dokument med uppgifter om betalande medlemmar.
    public void writeMemberToFile(String socialSecurityNumber, String memberName) {
        String payingMember = "Personnummer: " + socialSecurityNumber + ", Namn: " + memberName +
                ". Tränade: " + LocalDate.now() + ".";
        try (BufferedWriter bw = Files.newBufferedWriter(pathWriter, StandardOpenOption.APPEND)) {
            bw.write(payingMember + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("Filen finns ej");
        } catch (IOException e) {
            System.out.println("I/O fel uppstod");
        } catch (Exception e) {
            System.out.println("Okänd fel inträffat");
        }
    }

    //Getters & Setters
    public Path getPathReader() {
        return pathReader;
    }

    public void setPathReader(Path pathReader) {
        this.pathReader = pathReader;
    }

    public Path getPathWriter() {
        return pathWriter;
    }

    public void setPathWriter(Path pathWriter) {
        this.pathWriter = pathWriter;
    }
}
