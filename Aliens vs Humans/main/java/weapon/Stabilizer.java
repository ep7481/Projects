package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class Stabilizer extends Attachment {

  /**
   * creates a new stabilizer
   *
   * @param base
   */
  public Stabilizer(Weapon base) throws AttachmentException {
    this.base = base;
    if (base.getNumAttachments() == 2) {
      throw new AttachmentException("Attachments cannot exceed 2");
    }
  }

  /*
   * This method reloads the weapon if the shots left are 0 This method also gives
   * the weapon bonus damage per shot taken
   */

  @Override
  public int fire(int distance) throws WeaponException {
    if (distance < 0) {
      throw new WeaponException("Cannot be less than 0");
    }

    if (getShotsLeft() == 0) {
      return 0;
    }

    int newDamage = Double.valueOf(Math.floor(base.fire(distance) * 1.25)).intValue();

    if (base.getCurrentAmmo() == 0) {
      base.reload();
    }

    return newDamage;
  }

  @Override
  public String toString() {
    return base.toString() + " +Stabilizer";
  }
}
