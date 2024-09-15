package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WorkoutLogTest {
/*
    private static void checkCalc(WorkoutLog calc, double... operands) {
        Assertions.assertEquals(operands.length, calc.getOperandCount(), "Wrong operand count");
        for (int i = 0; i < operands.length; i++) {
            Assertions.assertEquals(operands[i], calc.peekOperand(i), "Wrong value at #" + i + " of operand stack");
        }
    }

    @Test
    public void testCalc() {
        checkCalc(new WorkoutLog());
        checkCalc(new WorkoutLog(1.0), 1.0);
        checkCalc(new WorkoutLog(3.14, 1.0), 1.0, 3.14);
    }

    @Test
    public void testPushOperand() {
        WorkoutLog calc = new WorkoutLog();
        calc.pushOperand(1.0);
        checkCalc(calc, 1.0);
        calc.pushOperand(3.14);
        checkCalc(calc, 3.14, 1.0);
    }

    @Test
    public void testPeekOperand() {
        WorkoutLog calc = new WorkoutLog(1.0, 3.14);
        Assertions.assertEquals(3.14, calc.peekOperand());
        Assertions.assertThrows(IllegalArgumentException.class, () -> new WorkoutLog().peekOperand());
    }

    @Test
    public void testPeekOperandN() {
        WorkoutLog calc = new WorkoutLog(1.0, 3.14);
        Assertions.assertEquals(3.14, calc.peekOperand(0));
        Assertions.assertEquals(1.0, calc.peekOperand(1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> calc.peekOperand(2));
    }

    @Test
    public void testPopOperand() {
        WorkoutLog calc = new WorkoutLog(1.0, 3.14);
        Assertions.assertEquals(3.14, calc.popOperand());
        checkCalc(calc, 1.0);
        Assertions.assertEquals(1.0, calc.popOperand());
        checkCalc(calc);
    }

    @Test
    public void testPopOperand_emptyStack() {
        Assertions.assertThrows(IllegalStateException.class, () -> new WorkoutLog().popOperand());
    }

    @Test
    public void testPerformOperation1() {
        WorkoutLog calc = new WorkoutLog(1.0);
        Assertions.assertEquals(-1.0, calc.performOperation(n -> -n));
        checkCalc(calc, -1.0);
    }

    @Test
    public void testPerformOperation1_emptyOperandStack() {
        Assertions.assertThrows(IllegalStateException.class, () -> new WorkoutLog().performOperation(n -> -n));
    }


    @Test
    public void testPerformOperation2() {
        WorkoutLog calc = new WorkoutLog(1.0, 3.0);
        Assertions.assertEquals(-2.0, calc.performOperation((n1, n2) -> n1 - n2));
        checkCalc(calc, -2.0);
    }

    @Test
    public void testPerformOperation2_lessThanTwoOperands() {
        Assertions.assertThrows(IllegalStateException.class, () -> new WorkoutLog(1.0).performOperation((n1, n2) -> n1 - n2));
        Assertions.assertThrows(IllegalStateException.class, () -> new WorkoutLog().performOperation((n1, n2) -> n1 - n2));
    }

    @Test
    public void testSwap() {
        WorkoutLog calc = new WorkoutLog(1.0, 3.14);
        checkCalc(calc, 3.14, 1.0);
        calc.swap();
        checkCalc(calc, 1.0, 3.14);
        calc.swap();
        checkCalc(calc, 3.14, 1.0);
    }

    @Test
    public void testSwap_lessThanTwoOperands() {
        Assertions.assertThrows(IllegalStateException.class, () -> new WorkoutLog(1.0).swap());
        Assertions.assertThrows(IllegalStateException.class, () -> new WorkoutLog().swap());
    }

    @Test
    public void testDup() {
        WorkoutLog calc = new WorkoutLog(1.0, 3.14);
        Assertions.assertEquals(3.14, calc.popOperand());
        checkCalc(calc, 1.0);
        Assertions.assertEquals(1.0, calc.popOperand());
        checkCalc(calc);
    }

    @Test
    public void testDup_emptyOperandStack() {
        Assertions.assertThrows(IllegalStateException.class, () -> new WorkoutLog().dup());
    }*/
}
