package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class PowerBooster extends Attachment {

  /**
   * Adds PowerBooster
   *
   * @param base
   * @throws AttachmentException
   */
  public PowerBooster(Weapon base) throws AttachmentException {
    this.base = base;
    if (this.base.getNumAttachments() == 2) {
      throw new AttachmentException("Attachments cannot exceed 2");
    }
  }

  /*
   * Damage increases depending on the currentAmmo/maxAmmo
   */

  @Override
  public int fire(int distance) throws WeaponException {
    if (distance < 0) {
      throw new WeaponException("Cannot be less than 0");
    }
    if (getShotsLeft() == 0) {
      return 0;
    }
    double powerBoostDamage = 1 + ((double) base.getCurrentAmmo() / base.getMaxAmmo());
    return Double.valueOf(Math.floor(powerBoostDamage * base.fire(distance))).intValue();
  }

  @Override
  public String toString() {
    return base.toString() + " +PowerBooster";
  }
}
