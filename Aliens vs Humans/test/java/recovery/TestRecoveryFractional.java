package recovery;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestRecoveryFractional {

  @Test
  public void testMaximum() {
    RecoveryFractional rf = new RecoveryFractional(.1);
    int maxLifePts = 110;
    int currentLifePts = 100;
    int result = rf.calculateRecovery(currentLifePts, maxLifePts);
    assertEquals(110, result);
  }

  @Test
  public void testRoundUp() {
    RecoveryFractional rf = new RecoveryFractional(0.1);
    int maxLifePts = 150;
    int currentLifePts = 93;
    int result = rf.calculateRecovery(currentLifePts, maxLifePts);
    assertEquals(103, result);
  }

  @Test
  public void testOverdue() {
    RecoveryFractional rf = new RecoveryFractional(.9);
    int maxLifePts = 100;
    int currentLifePts = 100;
    int result = rf.calculateRecovery(currentLifePts, maxLifePts);
    assertEquals(100, result);
  }

  @Test
  public void testZero() {
    RecoveryFractional rf = new RecoveryFractional(.1);
    int maxLifePts = 100;
    int currentLifePts = 0;
    int result = rf.calculateRecovery(currentLifePts, maxLifePts);
    assertEquals(0, result);
  }

  @Test
  public void testEqual() {
    RecoveryFractional rf = new RecoveryFractional(.1);
    int maxLifePts = 100;
    int currentLifePts = 100;
    int result = rf.calculateRecovery(currentLifePts, maxLifePts);
    assertEquals(100, result);
  }
}
