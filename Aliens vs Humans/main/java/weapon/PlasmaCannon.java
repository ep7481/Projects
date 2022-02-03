//Taylor Fernandez

package weapon;

import exceptions.WeaponException;
import gameplay.TimerObserver;

public class PlasmaCannon extends GenericWeapon implements TimerObserver, Weapon {

  /**
   * constructor for the plasma cannon
   */
  public PlasmaCannon() {
    baseDamage = 50;
    maxRange = 40;
    rateOfFire = 1;
    maxAmmo = 4;
    shotsLeft = rateOfFire;
    currentAmmo = maxAmmo;
  }

  /**
   * method that calculated damage dealt to a LifeForm. Calculates damage. Damage
   * decreases as currentAmmo decreases. returns 0 when distance is greater than
   * maxRange and throws an exception when there is a negative distance
   *
   * @param distance - distance between the shooter and target
   */

  @Override
  public int fire(int distance) throws WeaponException {
    if (distance < 0) {
      throw new WeaponException("Negative Shooting Distance");
    }

    if (shotsLeft == 0 || currentAmmo == 0) {
      return 0;
    }

    if (distance <= maxRange) {
      double temp = baseDamage * ((double) currentAmmo / (double) maxAmmo);
      shotsLeft--;
      currentAmmo--;
      int damage = (int) Math.floor(temp);
      return damage;
    }

    shotsLeft--;
    currentAmmo--;
    return 0;
  }

  /**
   * returns the weapon and its attachments as a string
   */

  @Override
  public String toString() {
    return "PlasmaCannon";
  }

}
