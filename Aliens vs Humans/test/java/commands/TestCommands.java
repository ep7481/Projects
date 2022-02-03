package commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


import org.junit.Test;

import environment.Environment;
import exceptions.AttachmentException;
import exceptions.EnvironmentException;
import exceptions.WeaponException;
import game.Game;
import lifeform.Human;
import lifeform.LifeForm;
import lifeform.MockLifeForm;
import weapon.ChainGun;
import weapon.MockGenericWeapon;
import weapon.Pistol;
import weapon.PowerBooster;
import weapon.Weapon;

public class TestCommands {

	@Test
	public void testAttackWest() throws WeaponException, EnvironmentException, AttachmentException {
		Environment environment = Environment.getEnvironment(10, 10);
		LifeForm lf = new MockLifeForm("Bob", 20);
		LifeForm lf2 = new MockLifeForm("John", 40);
		LifeForm lf3 = new MockLifeForm("bobby", 40);
		environment.addLifeForm(lf, 1, 2);
		environment.addLifeForm(lf2, 1, 3);
		environment.addLifeForm(lf3, 1, 4);
		Pistol gun = new Pistol();
		lf.pickUpWeapon(gun);
		environment.addWeapon(gun, 1, 2);
		lf.setDirection(1);
		environment.changeSelectedCell(1, 2);
		AttackCommand a = new AttackCommand(environment);
		a.execute();
		assertEquals(29, lf2.getCurrentLifePoints());
		assertEquals(40, lf3.getCurrentLifePoints());
	}

	@Test
	public void testAttackNorth() throws WeaponException, EnvironmentException, AttachmentException {
		Environment environment = Environment.getEnvironment(10, 10);
		LifeForm lf = new MockLifeForm("Bob", 20);
		LifeForm lf2 = new MockLifeForm("John", 40);
		LifeForm lf3 = new MockLifeForm("bobby", 40);
		environment.addLifeForm(lf, 4, 2);
		environment.addLifeForm(lf2, 3, 2);
		environment.addLifeForm(lf3, 1, 4);
		Pistol gun = new Pistol();
		lf.pickUpWeapon(gun);
		environment.addWeapon(gun, 4, 2);
		lf.setDirection(0);
		environment.changeSelectedCell(4, 2);
		AttackCommand a = new AttackCommand(environment);
		a.execute();
		assertEquals(29, lf2.getCurrentLifePoints());
		assertEquals(40, lf3.getCurrentLifePoints());
	}

	@Test
	public void testAttackSouth() throws WeaponException, EnvironmentException, AttachmentException {
		Environment environment = Environment.getEnvironment(10, 10);
		LifeForm lf = new MockLifeForm("Bob", 20);
		LifeForm lf2 = new MockLifeForm("John", 40);
		LifeForm lf3 = new MockLifeForm("bobby", 40);
		environment.addLifeForm(lf, 1, 7);
		environment.addLifeForm(lf2, 4, 7);
		environment.addLifeForm(lf3, 1, 4);
		Pistol gun = new Pistol();
		lf.pickUpWeapon(gun);
		environment.addWeapon(gun, 1, 7);
		lf.setDirection(2);
		environment.changeSelectedCell(1, 7);
		AttackCommand a = new AttackCommand(environment);
		a.execute();
		assertEquals(31, lf2.getCurrentLifePoints());
		assertEquals(40, lf3.getCurrentLifePoints());

		// good
	}

	@Test
	public void testAttackEast() throws WeaponException, EnvironmentException, AttachmentException {
		Environment environment = Environment.getEnvironment(10, 10);
		LifeForm lf = new MockLifeForm("Bob", 20);
		LifeForm lf2 = new MockLifeForm("John", 40);
		LifeForm lf3 = new MockLifeForm("bobby", 40);
		environment.addLifeForm(lf, 6, 2);
		environment.addLifeForm(lf2, 6, 4);
		environment.addLifeForm(lf3, 1, 4);
		Pistol gun = new Pistol();
		lf.pickUpWeapon(gun);
		environment.addWeapon(gun, 6, 2);
		lf.setDirection(1);
		environment.changeSelectedCell(6, 2);
		AttackCommand a = new AttackCommand(environment);
		a.execute();
		assertEquals(30, lf2.getCurrentLifePoints());
		assertEquals(40, lf3.getCurrentLifePoints());

		// good
	}

	@Test
  public void testMove() {
    Environment environment = Environment.getEnvironment(10, 10);
    environment.clearBoard();
    Human lf = new Human("Dan", 100, 10);
    environment.addLifeForm(lf, 4, 4);
    environment.changeSelectedCell(4, 4);
    MoveCommand move = new MoveCommand(environment);
    move.execute();
    assertEquals(lf, environment.getLifeForm(1, 4));
    environment.changeSelectedCell(1, 4); 
    lf.setDirection(3);
    move.execute();
    assertEquals(lf, environment.getLifeForm(1, 1));
    environment.changeSelectedCell(1, 1);
  }
	
	@Test
	public void testReload() throws WeaponException, EnvironmentException {
		Environment environment = Environment.getEnvironment(10, 10);
		environment.clearBoard();
		environment = Environment.getEnvironment(10,10);
		
		LifeForm lf = new MockLifeForm("Bob", 20);
		LifeForm lf2 = new MockLifeForm("John", 40);
		LifeForm lf3 = new MockLifeForm("bobby", 40);
		environment.addLifeForm(lf, 6, 2);
		environment.addLifeForm(lf2, 6, 4);
		environment.addLifeForm(lf3, 1, 4);
		Pistol gun = new Pistol();
		lf.pickUpWeapon(gun);
		environment.addWeapon(gun, 6, 2);
		lf.setDirection(1);
		environment.changeSelectedCell(6, 2);
		AttackCommand a = new AttackCommand(environment);
		a.execute();
		assertEquals(9, environment.getCell(6, 2).getWeapon1().getCurrentAmmo());
		ReloadCommand r = new ReloadCommand(environment);
		r.execute();
		assertEquals(10, environment.getCell(6, 2).getWeapon1().getCurrentAmmo());
	}

	@Test
	public void testNorthDir() {
		Environment environment = Environment.getEnvironment(10, 10);
		LifeForm lf4 = new MockLifeForm("Bob", 20);
		environment.addLifeForm(lf4, 1, 1);
		lf4.setDirection(0);
		assertEquals(0, lf4.getDirection());
	}
	
	@Test
	public void testSouthDir() {
		Environment environment = Environment.getEnvironment(10, 10);
		LifeForm lf4 = new MockLifeForm("Bob", 20);
		environment.addLifeForm(lf4, 1, 1);
		lf4.setDirection(2);
		assertEquals(2, lf4.getDirection());
	}
	
	@Test
	public void testEastDir() {
		Environment environment = Environment.getEnvironment(10, 10);
		LifeForm lf4 = new MockLifeForm("Bob", 20);
		environment.addLifeForm(lf4, 1, 1);
		lf4.setDirection(1);
		assertEquals(1, lf4.getDirection());
	}
	
	@Test
	public void testWestDir() {
		Environment environment = Environment.getEnvironment(10, 10);
		LifeForm lf4 = new MockLifeForm("Bob", 20);
		environment.addLifeForm(lf4, 1, 1);
		lf4.setDirection(3);
		assertEquals(3, lf4.getDirection());
	}
	
	@Test
  public void testDropWeapon() {
    Environment environment = Environment.getEnvironment(10, 10);
    LifeForm bob = new MockLifeForm("Bobby", 100, 10);
    Pistol pistol = new Pistol();
    
    bob.pickUpWeapon(pistol);
    environment.addLifeForm(bob, 4, 1);
    environment.changeSelectedCell(4, 1);
    
    assertEquals(pistol, bob.dropWeapon());
  }
	
	 @Test
	  public void testDropWeaponNoSpace() {
	    Environment environment = Environment.getEnvironment(10, 10);
	    environment.clearBoard();
	    environment = Environment.getEnvironment(10, 10);
	    LifeForm bobby = new MockLifeForm("Bobby", 100, 10);
	    Pistol pistol1 = new Pistol();
	    Pistol pistol2 = new Pistol();
	    Pistol pistol3 = new Pistol();
	    
	    environment.addWeapon(pistol2, 4, 1);
	    environment.addWeapon(pistol3, 4, 1);
	    
	    bobby.pickUpWeapon(pistol1);
	    environment.addLifeForm(bobby, 4, 1);
	    environment.changeSelectedCell(4, 1);
	    bobby.dropWeapon();
	    
	    assertNull(bobby.getWeapon());
	  }
	
	@Test
  public void testPickUpWeapon() {
	  
    Environment environment = Environment.getEnvironment(10, 10);
    environment.clearBoard();
    environment = Environment.getEnvironment(10, 10);
    
    LifeForm bob = new MockLifeForm("Bobby", 100, 10);
    environment.addLifeForm(bob, 4, 1);
    environment.changeSelectedCell(4, 1);
    
    Pistol pistol = new Pistol();
    ChainGun chainGun = new ChainGun();
    environment.addWeapon(pistol, 4, 1);
    environment.addWeapon(chainGun, 4, 1);
    
    Weapon[] weapons = environment.getWeapons(4, 1);
    
    //pick up pistol
    PickWeaponOneCommand pickUp1 = new PickWeaponOneCommand(environment);
    pickUp1.execute();

    
    //pick up chainGun
    PickWeaponTwoCommand pickUp2 = new PickWeaponTwoCommand(environment);
    pickUp2.execute();
    
    assertEquals(environment.getCell(4, 1).getWeapon1().getClass(), Pistol.class);

    assertEquals(bob.getWeapon().getClass(), ChainGun.class);

    assertEquals(chainGun, bob.dropWeapon());
    
    //No weapons will be picked up
    LifeForm monkey = new MockLifeForm("tommy", 100, 10);
    environment.addLifeForm(monkey, 5, 5);
    environment.changeSelectedCell(5, 5);
    assertFalse(monkey.hasWeapon());
    pickUp2.execute();
    assertFalse(monkey.hasWeapon());
	}
	
	@Test
	public void testWeaponFull() {
		LifeForm bob = new MockLifeForm("Bobby", 100, 10);
		Environment environment = Environment.getEnvironment(10, 10);
		environment.addLifeForm(bob, 4, 1);
		environment.changeSelectedCell(4, 1);
		 Pistol pistol = new Pistol();
		 ChainGun chainGun = new ChainGun();
		 environment.addWeapon(chainGun, 4, 1);
		 environment.addWeapon(pistol, 4, 1);
		 bob.pickUpWeapon(chainGun);
		 assertNotNull(bob.getWeapon());
		 PickWeaponOneCommand pickUp1 = new PickWeaponOneCommand(environment);
		 pickUp1.execute();
		 assertEquals("ChainGun", bob.getWeapon().toString());
		 
		
	}
}
