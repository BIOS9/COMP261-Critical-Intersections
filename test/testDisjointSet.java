import minspanningtree.DisjointSet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class testDisjointSet {

    @Test
    public void testDJSetFindUnionBasic() {
        DisjointSet<String> djSet = new DisjointSet<>();
        assertFalse(djSet.find("A", "B"));
        djSet.union("A","B");
        assertTrue(djSet.find("A", "B"));
    }

    @Test
    public void testDJSetFindUnionExtended() {
        DisjointSet<String> djSet = new DisjointSet<>();
        assertFalse(djSet.find("A", "B"));
        djSet.union("A","B");
        assertTrue(djSet.find("A", "B"));

        assertFalse(djSet.find("C", "D"));
        djSet.union("C","D");
        assertTrue(djSet.find("C", "D"));

        assertFalse(djSet.find("A", "C"));
        assertFalse(djSet.find("A", "D"));
        assertFalse(djSet.find("B", "C"));
        assertFalse(djSet.find("B", "D"));

        djSet.union("A","D");

        assertTrue(djSet.find("A", "C"));
        assertTrue(djSet.find("A", "D"));
        assertTrue(djSet.find("B", "C"));
        assertTrue(djSet.find("B", "D"));
    }

}
