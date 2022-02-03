package weapon;

import exceptions.WeaponException;
import gameplay.TimerObserver;

public class Pistol extends GenericWeapon implements TimerObserver, Weapon {
  /**
   * Pistol
   */
  public Pistol() {
    baseDamage = 10;
    maxRange = 50;
    rateOfFire = 2;
    maxAmmo = 10;
    shotsLeft = rateOfFire;
    currentAmmo = maxAmmo;
  }

  @Override
  public int fire(int distance) throws WeaponException {

    if (distance < 0) {
      throw new WeaponException("negative Fire Distance");
    }

    // Check to see if the weapon can be fired again.
    if (shotsLeft <= 0 || currentAmmo <= 0) {
      return 0;
    }

    if (distance <= maxRange) {
      double temp = baseDamage * (((double) maxRange - (double) distance + 10) / maxRange);
      currentAmmo--;
      shotsLeft--;
      return (int) Math.floor(temp);
    } else {
      currentAmmo--;
      shotsLeft--;
      return 0;

    }
  }

  @Override
  public String toString() {
    return "Pistol";
  }
}
