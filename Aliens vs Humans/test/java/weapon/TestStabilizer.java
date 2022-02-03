package weapon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class TestStabilizer {

	@Test
	public void testInitialization() throws AttachmentException {
		Weapon s = new Stabilizer(new PlasmaCannon());
	}
	@Test
	public void testPlasmaDamage() throws AttachmentException, WeaponException {
		Weapon s = new PlasmaCannon();
		assertEquals(50, s.fire(25));
	}
	@Test
	public void testStabilizer() throws AttachmentException, WeaponException {
		Weapon s = new Stabilizer(new PlasmaCannon());
		assertEquals(62, s.fire(25));
	}
	@Test
	public void testStabilizerAndScope() throws AttachmentException, WeaponException {
		//Stabilizer s = new Stabilizer(new Scope(new PlasmaCannon()));
		Weapon t = new Scope(new PlasmaCannon());
		assertEquals(75, t.fire(25));
		Weapon s = new Stabilizer(t);
		assertEquals(0, s.fire(25));
	}
	@Test
	public void testStabilizerAndPowerBooster() throws AttachmentException, WeaponException {
		Weapon s = new Stabilizer(new PowerBooster(new PlasmaCannon()));
		assertEquals(125, s.fire(25));
	}
	@Test
	public void testStabilizerAndStabilizer() throws AttachmentException, WeaponException {
		Weapon s = new Stabilizer(new Stabilizer(new PlasmaCannon()));
		assertEquals(77, s.fire(25));
	}
}
