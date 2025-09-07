
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

public class PersonTest {

    Person person;

    @BeforeEach
    void setUp() {
        person = new Person("001", "John", "Doe", "Mr.", 1990);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("001", person.getID());
        assertEquals("John", person.getFirstName());
        assertEquals("Doe", person.getLastName());
        assertEquals("Mr.", person.getTitle());
        assertEquals(1990, person.getYOB());
    }

    @Test
    void testSetters() {
        person.setFirstName("Jane");
        person.setLastName("Smith");
        person.setTitle("Dr.");
        person.setYOB(1985);

        assertEquals("Jane", person.getFirstName());
        assertEquals("Smith", person.getLastName());
        assertEquals("Dr.", person.getTitle());
        assertEquals(1985, person.getYOB());
    }

    @Test
    void testFullName() {
        assertEquals("John Doe", person.fullName());
    }

    @Test
    void testFormalName() {
        assertEquals("Mr. John Doe", person.formalName());
    }

    @Test
    void testGetAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        assertEquals(currentYear - 1990, person.getAge());
        assertEquals(2000 - 1990, person.getAge(2000));
    }

    @Test
    void testToCSV() {
        assertEquals("001, John, Doe, Mr., 1990", person.toCSV());
    }

    @Test
    void testToJSON() {
        String expected = "{\"ID\":\"001\",\"firstName\":\"John\",\"lastName\":\"Doe\",\"title\":\"Mr.\",\"YOB\":1990}";
        assertEquals(expected, person.toJSON());
    }

    @Test
    void testToXML() {
        String expected = "<Person><ID>001</ID><FirstName>John</FirstName><LastName>Doe</LastName><Title>Mr.</Title><YOB>1990</YOB></Person>";
        assertEquals(expected, person.toXML());
    }

    @Test
    void testEquals() {
        Person p2 = new Person("001", "John", "Doe", "Mr.", 1990);
        Person p3 = new Person("002", "Jane", "Smith", "Dr.", 1985);
        assertEquals(person, p2);
        assertNotEquals(person, p3);
    }

    @Test
    void testToString() {
        assertEquals("Mr. John Doe (001), born 1990", person.toString());
    }
}
