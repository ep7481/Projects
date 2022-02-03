package weapon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class TestPowerBooster {

	@Test
	public void testInitialization() throws AttachmentException {
		Weapon s = new PowerBooster(new ChainGun());
	}
	@Test
	public void testChainGunDamage() throws AttachmentException, WeaponException {
		Weapon s = new ChainGun();
		assertEquals(6, s.fire(25));
	}
	@Test
	public void testPowerBooster() throws AttachmentException, WeaponException {
		Weapon s = new PowerBooster(new ChainGun());
		assertEquals(12, s.fire(25));
	}
	@Test
	public void testPowerBoosterAndScope() throws AttachmentException, WeaponException {
		Weapon t = new Scope(new ChainGun());
		assertEquals(9, t.fire(25));
		Weapon s = new PowerBooster(t);
		assertEquals(17, s.fire(25));
	}
	@Test
	public void testPowerBoosterAndPowerBooster() throws AttachmentException, WeaponException {
		Weapon s = new PowerBooster(new PowerBooster(new ChainGun()));
		assertEquals(24, s.fire(25));
	}
	@Test
	public void testPowerBoosterAndStabilizer() throws AttachmentException, WeaponException {
		Weapon s = new PowerBooster(new Stabilizer(new ChainGun()));
		assertEquals(14, s.fire(25));
	}
}
