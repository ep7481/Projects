//Luke Henry
package weapon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exceptions.WeaponException;

/**
 * @author lukeh
 *
 */
public class TestChainGun {

	@Test
	public void testInitialization() {
		ChainGun g = new ChainGun();
	}

	/**
	 * test that hte gun can do damage within range
	 *
	 * @throws WeaponException
	 */
	@Test
	public void testDamageWithinRange() throws WeaponException {
		ChainGun g = new ChainGun();
		assertEquals(1, g.fire(5), 0.1);
	}

	/**
	 * test that the gun will do zero damage if the target is out of range
	 *
	 * @throws WeaponException
	 */
	@Test
	public void testOverRange() throws WeaponException {
		ChainGun g = new ChainGun();
		assertEquals(0, g.fire(65), 0.1);
	}

	/**
	 * Test that the gun will do zero damage if there are no shots left
	 *
	 * @throws WeaponException
	 */
	@Test
	public void testNoShotsLeft() throws WeaponException {
		ChainGun g = new ChainGun();
		assertEquals(1, g.fire(5), 0.1);
		assertEquals(1, g.fire(5), 0.1);
		assertEquals(1, g.fire(5), 0.1);
		assertEquals(1, g.fire(5), 0.1);
		assertEquals(0, g.fire(5), 0.1);
	}

	/**
	 * test that the gun will do zero damage if there are no shots left
	 *
	 * @throws WeaponException
	 */
	@Test
	public void testNoCurrentAmmo() throws WeaponException {
		ChainGun g = new ChainGun();
		g.currentAmmo = 0;
		assertEquals(0, g.fire(5), 0.1);
	}

	/**
	 * Test that the exception will be thrown if the range is negative
	 *
	 * @throws WeaponException
	 */
	@Test(expected = WeaponException.class)
	public void testNegativeDistance() throws WeaponException {
		ChainGun g = new ChainGun();
		int damage = g.fire(-10);
	}

	@Test
	public void testUpdateAmmo() throws WeaponException {
		ChainGun g = new ChainGun();
		g.fire(5);
		assertEquals(39, g.getCurrentAmmo());
		g.fire(65);
		assertEquals(38, g.getCurrentAmmo());
	}

	//i can't go below 36 rounds of ammo becasue it taps into
	//shotsLeft being zero, thus returning zero and not
	//decreasing the shots by one
	@Test
	public void testReload() throws WeaponException {
		ChainGun g = new ChainGun();
		g.fire(5);
		g.fire(5);
		g.fire(5);
		assertEquals(37, g.getCurrentAmmo());
		g.reload();
		assertEquals(40, g.getCurrentAmmo());
	}
}
