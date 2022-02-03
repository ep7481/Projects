package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;
import gameplay.TimerObserver;

//I think we should remove "extends java.lang.Object
//I don't that it does anything

public abstract class GenericWeapon implements Weapon, TimerObserver {
  protected int baseDamage;
  protected int currentAmmo;
  protected int maxAmmo;
  protected int maxRange;
  protected int rateOfFire;
  protected int shotsLeft;

  public GenericWeapon() {
  }

  @Override
  public abstract int fire(int distance) throws WeaponException;

  @Override
  public abstract String toString();

  @Override
  public int getNumAttachments() throws AttachmentException {
    return 0;
  }

  @Override
  public void reload() {
    currentAmmo = maxAmmo;
  }

  @Override
  public int getBaseDamage() {
    return baseDamage;
  }

  @Override
  public int getMaxRange() {
    return maxRange;
  }

  @Override
  public int getRateOfFire() {
    return rateOfFire;
  }

  @Override
  public int getMaxAmmo() {
    return maxAmmo;
  }

  @Override
  public int getCurrentAmmo() {
    return currentAmmo;
  }

  @Override
  public int getShotsLeft() {
    return shotsLeft;
  }

  @Override
  public void updateTime(int time) {
    shotsLeft = rateOfFire;
  }
}
