import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    Product product;

    @BeforeEach
    void setUp() {
        product = new Product("P001", "Laptop", "Gaming laptop", 1299.99);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("P001", product.getID());
        assertEquals("Laptop", product.getName());
        assertEquals("Gaming laptop", product.getDescription());
        assertEquals(1299.99, product.getCost());
    }

    @Test
    void testSetters() {
        product.setName("Desktop");
        product.setDescription("Office desktop");
        product.setCost(799.99);

        assertEquals("Desktop", product.getName());
        assertEquals("Office desktop", product.getDescription());
        assertEquals(799.99, product.getCost());
    }

    @Test
    void testToCSV() {
        assertEquals("P001, Laptop, Gaming laptop, 1299.99", product.toCSV());
    }

    @Test
    void testToJSON() {
        String expected = "{\"ID\":\"P001\",\"name\":\"Laptop\",\"description\":\"Gaming laptop\",\"cost\":1299.99}";
        assertEquals(expected, product.toJSON());
    }

    @Test
    void testToXML() {
        String expected = "<Product><ID>P001</ID><Name>Laptop</Name><Description>Gaming laptop</Description><Cost>1299.99</Cost></Product>";
        assertEquals(expected, product.toXML());
    }

    @Test
    void testEquals() {
        Product p2 = new Product("P001", "Laptop", "Gaming laptop", 1299.99);
        Product p3 = new Product("P002", "Monitor", "4K Monitor", 399.99);
        assertEquals(product, p2);
        assertNotEquals(product, p3);
    }

    @Test
    void testToString() {
        assertEquals("Laptop (P001): $1299.99", product.toString());
    }
}
