package recovery;

public class RecoveryLinear implements RecoveryBehavior {
  int recoveryAmount;

  public RecoveryLinear(int ra) {
    this.recoveryAmount = ra;
  }

  /**
   * calculation of the linear recovery
   */
  @Override
public int calculateRecovery(int currentLife, int maxLifePts) {
    if (currentLife <= 0) {
      return 0;
    }
    currentLife += recoveryAmount;
    if (currentLife >= maxLifePts) {
      return maxLifePts;
    }
    return currentLife;
  }
}
