package state;

import weapon.Weapon;

public class OutOfAmmoState extends ActionState {
  public OutOfAmmoState(AiContext context) {
    super(context);
  }
  /**
   * executes
   */
  
  public void executeAction() {
    
    if (context.getLifeForm().getCurrentLifePoints() == 0) {
      context.setCurrentState(context.getDeadState());
    }
    Weapon gun = form.dropWeapon();
    gun.reload();
    form.pickUpWeapon(gun);
    context.setCurrentState(context.hasWeapon);
  }
}