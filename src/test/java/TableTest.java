import hussarexchange.javafx.model.Table;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TableTest {

    @Test
    void testCanStepInto() {
        assertEquals(true, Table.canStepInto(0,0,1,2));
        assertEquals(false, Table.canStepInto(0,1,2,2));
        assertEquals(false, Table.canStepInto(0,0,1,1));
        assertEquals(false, Table.canStepInto(1,0,0,2));

    }

    @Test
    void testGoodStep() {
        assertEquals(true, Table.GoodStep(0,0,1,2));
        assertEquals(true, Table.GoodStep(0,1,2,2));
        assertEquals(false, Table.GoodStep(0,0,1,1));
        assertEquals(true, Table.GoodStep(1,0,0,2));

    }

}
