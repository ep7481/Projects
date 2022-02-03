package recovery;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestRecoveryNone {

  @Test
  public void testMaxLp() {
    RecoveryNone none = new RecoveryNone();
  }

  @Test
  public void testCurrentLessMax() {
    RecoveryNone none = new RecoveryNone();
    assertEquals(100, none.calculateRecovery(100, 100));
  }
}
