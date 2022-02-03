package state;

import weapon.Weapon;
import java.util.Random;

public class NoWeaponState extends ActionState {
  public NoWeaponState(AiContext context) {
    super(context);

  }

  /**
   * executes
   */
  
  public void executeAction() {
    Random r = new Random();
    if (context.getLifeForm().getCurrentLifePoints() == 0) {
      context.setCurrentState(context.getDeadState());
    } else {
      int row = this.form.getRow();
      int col = this.form.getCol();
      Weapon gun;

      if (context.getEnvironment().getCell(row, col).getWeaponsCount() > 0) {
        if (context.getEnvironment().getCell(row, col).getWeapon1() == null
            && context.getEnvironment().getCell(row, col).getWeapon2() != null) {
          gun = context.getEnvironment().getCell(row, col).getWeapon2();
          form.pickUpWeapon(gun);
          context.getEnvironment().getCell(row, col).removeWeapon(gun);
        } else if (context.getEnvironment().getCell(row, col).getWeapon2() == null
            && context.getEnvironment().getCell(row, col).getWeapon1() != null) {
          gun = context.getEnvironment().getCell(row, col).getWeapon1();
          form.pickUpWeapon(gun);
          context.getEnvironment().getCell(row, col).removeWeapon(gun);
        } else {
          gun = context.getEnvironment().getCell(row, col).getWeapon1();
          form.pickUpWeapon(gun);
          context.getEnvironment().getCell(row, col).removeWeapon(gun);
        }
        if (context.getEnvironment().getLifeForm(row, col).getWeapon() != null) {
          context.setCurrentState(context.getHasWeaponState());
        }
      } else {
        int dir = (int) Math.floor(Math.random() * (3 - 0 + 1) + 0);
        while (dir == context.getLifeForm().getDirection()) {
          dir = r.nextInt(3);
        }
        context.getLifeForm().setDirection(dir);
        super.environment.move(row, col);
      }
    }
  }
}
