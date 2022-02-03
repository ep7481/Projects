package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;
import gameplay.TimerObserver;

public interface Weapon extends TimerObserver {

  int fire(int distance) throws WeaponException;

  int getBaseDamage();

  int getCurrentAmmo();

  int getMaxAmmo();

  int getMaxRange();

  int getNumAttachments() throws AttachmentException;

  int getRateOfFire();

  int getShotsLeft();

  void reload();

  java.lang.String toString();
}
