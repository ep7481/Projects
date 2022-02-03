package recovery;

public class RecoveryFractional implements RecoveryBehavior {
  double recoveryAmount;

  public RecoveryFractional(double recoveryFraction) {
    this.recoveryAmount = recoveryFraction;
  }

  @Override
  public int calculateRecovery(int currentLife, int maxLife) {
    if (currentLife == 0) {
      return 0;
    }
    if (currentLife + Math.ceil(recoveryAmount * currentLife) > maxLife) {
      return maxLife;
    }
    currentLife += Math.ceil(recoveryAmount * currentLife);
    return currentLife;
  }

}
