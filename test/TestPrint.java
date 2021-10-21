import org.junit.jupiter.api.Test;

public class TestPrint {
    @Test
    public void testColorBasic() {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";

        System.out.println(ANSI_RED + "TEST");
        System.out.println("TEST2");
        System.out.println(ANSI_RESET + "TEST3");
    }
}
