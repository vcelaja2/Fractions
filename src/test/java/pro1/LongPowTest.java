package pro1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class LongPowTest {
    @Test
    void test01()
    {
        assertEquals(
                1,
                Utils.longPow(7, 0)

        );
    }

    @Test
    void test02()
    {
        assertEquals(
                125,
                Utils.longPow(5, 3)

        );
    }
    @Test
    void test03()
    {
        assertEquals(
                16807,
                Utils.longPow(7, 5)

        );
    }

    @Test
    void test04()
    {
        assertEquals(
                1024,
                Utils.longPow(2, 10)

        );
    }
}
