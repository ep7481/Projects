package lifeform;

import exceptions.WeaponException;
import weapon.GenericWeapon;
import weapon.Weapon;

public abstract class LifeForm {
  String name;
  int points = 0;
  int maxPoints = 0;
  int attack;
  protected Weapon weapon;
  int row;
  int col;
  int currentDirection = 0;
  int maxSpeed = 0;

  /**
   * LifeForms
   * 
   * @param n name of life form
   * @param p points for life form constructor to create the lifeform with a name
   *          and amount of lifepoints.
   */
  public LifeForm(String n, int p) { 
    name = n;
    points = p;
    maxPoints = p;
  }

  /**
   * constructor for lifeForm
   * 
   * @param n
   * @param p
   * @param attack
   */

  public LifeForm(String n, int p, int attack) {
    name = n;
    points = p;
    this.attack = attack;
    maxPoints = p;
  }

  /**
   * sets the location of the LifeForm
   */
  public void setLocation(int rowPos, int colPos) {
    row = rowPos;
    col = colPos;
  }

  /**
   * gets the column of the LifeForm
   */
  public int getCol() {
    return col;
  }

  /**
   * gets the row of the LifeForm
   * 
   * @return
   */
  public int getRow() {
    return row;
  }

  /**
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return direction
   */
  public int getDirection() {
    return currentDirection;
  }

  public int getMaxSpeed() {
    return maxSpeed;
  }

  /**
   * @return 0-3 directions
   */
  public boolean setDirection(int direction) {
    if (direction > -1 && direction < 4) {
      currentDirection = direction;

      return true;
    }
    return false;
  }

  /**
   *
   * @return the points
   */
  public int getCurrentLifePoints() {
    return points;
  }
  
  /**
   * @return max lifepoints
   */
  public int getMaxLifePoints() {
    return maxPoints;
  }

  /**
   * hits an enity
   *
   * @param damage
   */
  public void takeHit(int damage) {
    if (getCurrentLifePoints() - damage < 0) {
      points = 0;
    } else if (points - damage >= 0) {
      points = getCurrentLifePoints() - damage;
    }
  }

  /**
   *
   * @return attack strength
   */
  public int getAttackStrength() {
    return this.attack;
  }

  /**
   * @param entity is the target
   */
  public void attack(LifeForm entity) {
    if (getCurrentLifePoints() > 0) {
      entity.takeHit(getAttackStrength());
    }
  }

  /**
   * attacks
   *
   * @param opponent
   * @throws WeaponException
   */
  public void attack(LifeForm opponent, int distance) throws WeaponException {
    if (points > 0) {
      if (!hasWeapon() || weapon.getCurrentAmmo() <= 0) {
        if (distance <= 5) {
          opponent.takeHit(getAttackStrength());
        }
      } else {
        opponent.takeHit(weapon.fire(distance));
      }
    }
  }

  /**
   * Method allows LifeForm to pick up weapons
   *
   * @param w - weapon intended to be picked up
   */

  public boolean pickUpWeapon(Weapon w) {
    if (weapon == null) {
      weapon = w;
      return true;
    }
    return false;
  }

  /**
   * allows the LifeForm to drop a weapon
   */
  public Weapon dropWeapon() {
    if (hasWeapon()) {
      Weapon oldWeapon = weapon;
      weapon = null;
      return oldWeapon;
    }
    return null;
  }

  /**
   * Returns true if the caller has a weapon
   */
  public boolean hasWeapon() {
    if (weapon == null) {
      return false;
    }
    return true;
  }

  /**
   * 
   * @return weapon type
   */
  public String getWeaponType() {
    if (weapon != null) {
      return weapon.toString();
    }

    return "";
  }

  public Weapon getWeapon() {
    return weapon;
  }

  public abstract String getType();

  /**
   * revives the life form
   */
  public void revive() {
    if (points == 0) {
      points = maxPoints;
    }
  }

}
