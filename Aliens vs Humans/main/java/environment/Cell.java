package environment;

import lifeform.LifeForm;
import weapon.Weapon;

/**
 * A Cell that can hold a LifeForm
 *
 * @author lh2565
 *
 */
public class Cell {
  Weapon weapon1;
  Weapon weapon2;
  LifeForm being;
  boolean bool;

  /**
   * @return the LifeForm in this Cell.
   */
  public LifeForm getLifeForm() {
    return being;
  }

  /**
   * @return true if weapon added.
   */
  public boolean addWeapon(Weapon w) {
    if (weapon1 == null && weapon2 != w) {
      weapon1 = w;
      return true;
    } else if (weapon2 == null && weapon1 != w) {
      weapon2 = w;
      return true;
    }
    return false;
  }

  /**
   * gets the first weapon in the ArrayList
   *
   * @returns firstWeapon
   */
  public Weapon getWeapon1() {
    return weapon1;
  }

  /**
   * gets the second weapon in the ArrayList
   *
   * @return second Weapon
   */

  public Weapon getWeapon2() {
    return weapon2;
  }

  /**
   * gets the number of weapons in the cell
   *
   * @return number of Weapons
   */

  public int getWeaponsCount() {
    int count = 0;
    if (weapon1 != null) {
      count++;
    }
    if (weapon2 != null) {
      count++;
    }
    return count;
  }

  /**
   * removes weapon.
   *
   * @return removed weapon.
   */
  public Weapon removeWeapon(Weapon w) {
    if (weapon1 == w) {
      weapon1 = null;
      return w;
    } else if (weapon2 == w) {
      weapon2 = null;
      return w;
    }
    return null;
  }

  /**
   * Tries to add the LifeForm to the Cell. Will not add if a LifeFrom is already
   * present.
   *
   * @param entity
   * @return true if the LifeForm was added to the Cell, false otherwise
   */
  public boolean addLifeForm(LifeForm entity) {
    if (being == null) {
      being = entity;
      bool = true;
    } else {
      bool = false;
    }
    return bool;
  }

  public void removeLifeForm() {
    being = null;
    bool = false;
  }
}
