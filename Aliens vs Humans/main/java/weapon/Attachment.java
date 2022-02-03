package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public abstract class Attachment implements Weapon {
  protected Weapon base;

  public Attachment() {
  }

  /**
   * Atachment constructor
   *
   * @param baseWeapon - weapon attachment is being added to
   */
  public Attachment(Weapon baseWeapon) {
    base = baseWeapon;
  }

  /**
   * Fires the weapon. Accounts for changes to damage done by attachments
   *
   * @param distance - distance between weapon and target
   * @throws WeaponException
   */
  @Override
  public abstract int fire(int distance) throws WeaponException;

  @Override
  public int getBaseDamage() {
    return base.getBaseDamage();
  }

  /**
   * gets the current ammo of the weapon
   */
  @Override
  public int getCurrentAmmo() {
    return base.getCurrentAmmo();
  }

  /**
   * gets the maximum ammo the base weapon can hold
   */

  @Override
  public int getMaxAmmo() {
    return base.getMaxAmmo();
  }

  /**
   * Gets the maximum range the base weapon can shoot
   */

  @Override
  public int getMaxRange() {
    return base.getMaxRange();
  }

  /**
   * Gets the number of attachments the base weapon has
   */

  @Override
  public int getNumAttachments() throws AttachmentException {
    if (base.getNumAttachments() == 2) {
      throw new AttachmentException("Attachments cannot exceed 2");
    }
    return base.getNumAttachments() + 1;
  }

  /**
   * Gets the rate of fire of the base weapon
   */

  @Override
  public int getRateOfFire() {
    return base.getRateOfFire();
  }

  /**
   * gets the shots the weapon can shoot in the current round
   */

  @Override
  public int getShotsLeft() {
    return base.getShotsLeft();
  }

  /**
   * reloads the weapon
   */

  @Override
  public void reload() {
    base.reload();
  }

  /**
   * notifies the weapon/attachments when a round has gone by
   *
   * @param time - current round
   */

  @Override
  public void updateTime(int time) {
    base.updateTime(time);
  }
}
