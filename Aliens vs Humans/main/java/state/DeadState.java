package state;

import java.util.Random;

import weapon.Weapon;

public class DeadState extends ActionState {
  Random rigby = new Random();
  
  
  public DeadState(AiContext context) {
    super(context);
  }
  /**
   * executes
   */
  
  public void executeAction() {
    int newRowW = rigby.nextInt(super.environment.getNumRows());
    int newColW = rigby.nextInt(super.environment.getNumRows());
    
    while (super.environment.getCell(newRowW, newColW).getWeaponsCount() == 2) {
      newRowW = rigby.nextInt(super.environment.getNumRows());
      newColW = rigby.nextInt(super.environment.getNumCols());
    }
 
    
    super.context.getLifeForm().revive();
    if (super.context.getLifeForm().hasWeapon() == true) {
      Weapon w = super.context.getLifeForm().dropWeapon();
      super.environment.addWeapon(w, newRowW, newColW);
    }
 
    context.getEnvironment().getGameUi().drawCell(newRowW, newColW);
    context.setCurrentState(context.getNoWeaponState());
    
  }

}
