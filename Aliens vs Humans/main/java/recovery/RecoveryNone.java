package recovery;

public class RecoveryNone implements RecoveryBehavior {

  public RecoveryNone() {

  }

  @Override
public int calculateRecovery(int currentLife, int maxLife) {
    return currentLife;

  }
}
