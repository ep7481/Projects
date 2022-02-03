package state;

import environment.Environment;
import gameplay.TimerObserver;
import lifeform.LifeForm;

public class AiContext implements TimerObserver {
  OutOfAmmoState ammoOut;
  HasWeaponState hasWeapon;
  NoWeaponState noWeapon;
  DeadState dead;
  
  LifeForm lf;
  Environment environment;
  ActionState currentState;
  /**
   * 
   * @param lifeform
   * @param e
   */
  
  public AiContext(LifeForm lifeform, Environment e) {
    lf = lifeform;
    this.environment = e;
    
    ammoOut = new OutOfAmmoState(this);
    hasWeapon = new HasWeaponState(this);
    noWeapon = new NoWeaponState(this);
    dead = new DeadState(this);
    currentState  = noWeapon;
  }
  
  public void execute() {
    currentState.executeAction();
  }
  
  public String getName() {
    return lf.getName();
  }
  
  public ActionState getCurrentState() {
    return currentState;
  }
  
  public DeadState getDeadState() {
    return dead;
  }
  
  public Environment getEnvironment() {
    return environment;
  }
  
  public HasWeaponState getHasWeaponState() {
    return hasWeapon;
  }
  
  public LifeForm getLifeForm() {
    return lf;
  }
  
  public NoWeaponState getNoWeaponState() {
    return noWeapon;
  }
  
  public OutOfAmmoState getOutOfAmmoState() {
    return ammoOut;
  }
  
  public void setCurrentState(ActionState state) {
    currentState = state;
  }
  /**
   * @param time
   */
  
  public void updateTime(int time) {
    this.execute();
    for (int i = 0; i < environment.getNumRows(); i++) {
      for (int j = 0; j < environment.getNumCols(); j++) {
        environment.getGameUi().drawCell(i, j);
      }
    }
  }
}
