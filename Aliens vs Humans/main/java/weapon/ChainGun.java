package weapon;
//extra commit

import exceptions.WeaponException;
import gameplay.TimerObserver;

public class ChainGun extends GenericWeapon implements TimerObserver, Weapon {

  /**
   * creates a ChainGun
   */
  public ChainGun() {
    baseDamage = 15;
    maxRange = 60;
    rateOfFire = 4;
    maxAmmo = 40;
    shotsLeft = rateOfFire;
    currentAmmo = maxAmmo;
  }

  @Override
  public int fire(int distance) throws WeaponException {
    if (distance < 0) {
      throw new WeaponException("Negative Distance");
    }
    if (shotsLeft <= 0 || currentAmmo <= 0) {
      return 0;
    }
    double temp = baseDamage * ((double) distance / (double) maxRange);
    int damage = (int) Math.floor(temp);
    currentAmmo--;
    shotsLeft--;
    if (distance <= maxRange) {
      return damage;
    } else {
      return 0;
    }
  }

  @Override
  public String toString() {
    return "ChainGun";
  }

}
