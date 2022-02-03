package weapon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class TestScope {

	@Test
	public void testInitialization() throws AttachmentException {
		Weapon p = new Scope(new Pistol());
		Weapon s = new Pistol();
	}

	@Test
	public void testScope() throws AttachmentException, WeaponException {
		Weapon p = new Scope(new Pistol());
		assertEquals(60, p.getMaxRange());
		assertEquals(21, p.fire(5)); // testing damage
		assertEquals(7, p.fire(55));
		assertEquals(0, p.fire(65));
		assertEquals(0, p.fire(50));
	}

	@Test
	public void testDoubleScope() throws AttachmentException, WeaponException {
		Weapon p = new Scope(new Scope(new Pistol()));
		assertEquals(70, p.getMaxRange()); // testing range is updated with a second scope
		assertEquals(40, p.fire(5));
		assertEquals(8, p.fire(55));
		assertEquals(0, p.fire(65));

	}

	@Test
	public void testScopeAndStabalizer() throws AttachmentException, WeaponException {
		Weapon p = new Scope(new Stabilizer(new Pistol()));
		assertEquals(24, p.fire(5));
		assertEquals(7, p.fire(55));
		assertEquals(0, p.fire(65));

	}

	@Test
	public void testScopeAndPowerBooster() throws AttachmentException, WeaponException {
		Weapon s = new Scope(new PowerBooster(new Pistol()));
		assertEquals(4, s.fire(50));
		assertEquals(38, s.fire(5));
		assertEquals(0, s.fire(55));
	}

}
