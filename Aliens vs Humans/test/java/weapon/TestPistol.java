//Luke Henry

package weapon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exceptions.WeaponException;

/**
 * @author lukeh
 *
 */
public class TestPistol {


	/**
	 * tests the ability to create a pistol
	 */
	@Test
	public void testInitialization() {
		Pistol p = new Pistol();
	}

	/**
	 * tests the damage of a pistol at a distance of 5
	 * @throws WeaponException
	 */
	@Test
	public void testDamageBasedOnRange() throws WeaponException {
		Pistol p = new Pistol();
		int damage = p.fire(5);
		assertEquals(11, damage);
	}
	/**
	 * Test the damage a pistol can do when attacking
	 * at a range beyond its max range
	 * @throws WeaponException
	 * should be 0
	 */
	@Test
	public void testOverRange() throws WeaponException {
		Pistol p = new Pistol();
		int damage = p.fire(51);
		assertEquals(0, damage);
	}
	@Test
	public void testNoShotsLeft() throws WeaponException {
		Pistol p = new Pistol();
		int damage = p.fire(5);
		assertEquals(11, damage);
		damage = p.fire(5);
		assertEquals(11, damage);
		damage = p.fire(5);
		assertEquals(0, damage);
		assertEquals(0, p.getShotsLeft());
	}

	//i can't test current ammo all the way to zero because
	//shots left reaches zero first and returns zero
	@Test
	public void testNoCurrentAmmo() throws WeaponException {
		Pistol p = new Pistol();
		p.currentAmmo = 0;
		int damage = p.fire(5);
		assertEquals(0, damage);
	}

	/**
	 * Test that the exception is thrown with
	 * a negative attack range
	 * @throws WeaponException
	 */
	@Test (expected = WeaponException.class)
	public void testNegativeDistance() throws WeaponException {
		Pistol p = new Pistol();
		int damage = p.fire(-10);
	}

	@Test
	public void testUpdateAmmo() throws WeaponException {
		Pistol p = new Pistol();
		assertEquals(11, p.fire(5));
		assertEquals(9, p.getCurrentAmmo());
		assertEquals(0, p.fire(51));
		assertEquals(8, p.getCurrentAmmo());
	}
	@Test
	public void testReload() throws WeaponException {
		Pistol p = new Pistol();
		p.fire(5);
		p.fire(5);
		assertEquals(8, p.getCurrentAmmo());
		p.reload();
		assertEquals(10, p.getCurrentAmmo());
	}
}
