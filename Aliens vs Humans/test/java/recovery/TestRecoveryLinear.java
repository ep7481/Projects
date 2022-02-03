package recovery;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestRecoveryLinear {

  @Test
  public void noRecoveryWhenNotHurt() {
    RecoveryLinear rl = new RecoveryLinear(3);
    int maxLifePts = 30;
    int result = rl.calculateRecovery(maxLifePts, maxLifePts);
    assertEquals(maxLifePts, result);
  }

  @Test
  public void fullNoMax() {
    RecoveryLinear rl = new RecoveryLinear(3);
    int maxLifePts = 15;
    int currentLife = 10;
    int result = rl.calculateRecovery(currentLife, maxLifePts);
    assertEquals(13, result);
  }

  @Test
  public void recoverTooMuch() {
    RecoveryLinear rl = new RecoveryLinear(50);
    int maxLifePts = 50;
    int currentLife = 25;
    int result = rl.calculateRecovery(currentLife, maxLifePts);
    assertEquals(50, result);
  }

  @Test
  public void noRecover() {
    RecoveryLinear rl = new RecoveryLinear(200);
    int maxLifePts = 50;
    int currentLife = 0;
    int result = rl.calculateRecovery(currentLife, maxLifePts);
    assertEquals(0, result);
  }

  @Test
  public void testEqual() {
    RecoveryLinear rl = new RecoveryLinear(50);
    int maxLifePts = 50;
    int currentLife = 50;
    int result = rl.calculateRecovery(currentLife, maxLifePts);
    assertEquals(50, result);
  }
}
