package weapon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exceptions.WeaponException;

public class TestGenericWeapon {

	@Test
	public void testInitialization() {
		MockGenericWeapon gun = new MockGenericWeapon();
	}
	@Test
	public void testAmmoConsumption() throws WeaponException {
		MockGenericWeapon gun = new MockGenericWeapon();
		gun.fire(5);
		assertEquals(9, gun.getCurrentAmmo());
	}
	@Test
	public void testReload() throws WeaponException {
		MockGenericWeapon gun = new MockGenericWeapon();
		gun.fire(5);
		gun.fire(5);
		assertEquals(8, gun.getCurrentAmmo());
		gun.reload();
		assertEquals(10, gun.getCurrentAmmo());
	}
	@Test
	public void testDamage() throws WeaponException {
		MockGenericWeapon gun = new MockGenericWeapon();
		assertEquals(20, gun.fire(5));
	}
	@Test
	public void testNoDamage() throws WeaponException {
		MockGenericWeapon gun = new MockGenericWeapon();
		assertEquals(0, gun.fire(41));
	}
	@Test
	public void testShotsLeft() throws WeaponException {
		MockGenericWeapon gun = new MockGenericWeapon();
		assertEquals(20, gun.fire(5));
		assertEquals(20, gun.fire(5));
		assertEquals(0, gun.fire(5));
	}
	@Test
	public void testUpdateTime() throws WeaponException {
		MockGenericWeapon gun = new MockGenericWeapon();
		assertEquals(20, gun.fire(5));
		assertEquals(20, gun.fire(5));
		assertEquals(0, gun.fire(5));
		gun.updateTime(10);
		assertEquals(20, gun.fire(5));
	}
}
