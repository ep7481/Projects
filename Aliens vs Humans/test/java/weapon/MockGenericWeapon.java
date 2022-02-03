package weapon;

import exceptions.WeaponException;

public class MockGenericWeapon extends GenericWeapon {
	public MockGenericWeapon() {
		baseDamage = 20;
		maxRange = 40;
		rateOfFire = 2;
		maxAmmo = 10;
		currentAmmo = maxAmmo;
		shotsLeft = rateOfFire;
	}


	@Override
	public int fire(int distance) throws WeaponException {
		if (distance < 0) {
			throw new WeaponException("Distance is negative");
		} else if (shotsLeft == 0) {
			return 0;
		} else if (distance <= maxRange && currentAmmo > 0) {
			currentAmmo--;
			shotsLeft--;
			return baseDamage;
		} else if (this.currentAmmo > 0) {
			currentAmmo--;
			shotsLeft--;
			return 0;
		} else {
			return 0;
		}
	}


	@Override
	public String toString() {
		return "MockWeapon";
	}
}
