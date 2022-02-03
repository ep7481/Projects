package lifeform;

import exceptions.RecoveryRateException;
import gameplay.TimerObserver;
import recovery.RecoveryBehavior;

public class Alien extends LifeForm implements TimerObserver {
  int maxLifePoints;
  int recoveryRate;
  int currentLifePoints;
  RecoveryBehavior xd;

  /**
   * 
   * @param name
   * @param maxHp
   */
  
  public Alien(String name, int maxHp) {
    super(name, maxHp, 10);
    maxLifePoints = maxHp;
    maxSpeed = 2;
  }

  /**
   * 
   * @param name
   * @param maxHp
   * @param behavior
   * @throws RecoveryRateException
   */
  
  public Alien(String name, int maxHp, RecoveryBehavior behavior) throws RecoveryRateException {
    this(name, maxHp);
    xd = behavior;
    maxSpeed = 3;
  }

  /**
   * constuctor for alien
   *
   * @param name
   * @param maxHp
   * @param behavior
   * @param recoveryRate
   * @throws RecoveryRateException
   */
  public Alien(String name, int maxHp, RecoveryBehavior behavior, 
      int recoveryRate) throws RecoveryRateException {
    this(name, maxHp);
    xd = behavior;
    maxSpeed = 3;
    maxLifePoints = maxHp;
    this.recoveryRate = recoveryRate;
    if (recoveryRate <= 0) {
      throw new RecoveryRateException("Recovery Rate is zero");
    } else {
      this.recoveryRate = recoveryRate;
    }

  }

  public int getMaxLifePoints() {
    return this.maxLifePoints;
  }

  public int getRecoveryRate() {
    return this.recoveryRate;
  }

  protected void recover() {
    if (points >= 0) {
      points = xd.calculateRecovery(points, maxLifePoints);
    }
  }

  public String getType() {
    return "Alien";
  }

  @Override
  public void updateTime(int time) {
    if (time % recoveryRate == 0) {
      recover();
    }
  }
}
