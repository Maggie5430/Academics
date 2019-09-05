package lab4;

public class TestCompileTime {

    public TestCompileTime() {
    }

    public static void main(String args[]) {
        if (Debug.dmode) {
            System.err.println("Debug is turned on");
            TestCompileTime t = new TestCompileTime();
            int[] numbers = new int[5];
            for (int i = 0; i < 5; i++) {
                numbers[i] = i;
                System.out.println("DEBUG_STATEMENT: number[" + i + "]=" + i);

            }

            for (int i = 0; i < 5; i++) {
                int element = numbers[i];
                System.out.print(element + " ");
            }
            System.out.println();
        } else {
            System.out.println("Debug is turned off");
        }
    }
}
