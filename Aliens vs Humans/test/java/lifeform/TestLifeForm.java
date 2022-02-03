package lifeform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import exceptions.WeaponException;
import weapon.PlasmaCannon;
import weapon.Weapon;

/**
 * Tests that functionality provided by the LifefForm class
 *
 * @author lh2565
 *
 */
public class TestLifeForm {
  /**
   * When a LifeForm is created, it should know its name and how many life points
   * it has.
   */
  @Test
  public void testInitialization() {
    LifeForm entity;
    entity = new MockLifeForm("Bob", 40);
    assertEquals("Bob", entity.getName());
    assertEquals(40, entity.getCurrentLifePoints());
  }

  @Test
  public void testTakeHit() {
    LifeForm entity;
    entity = new MockLifeForm("Bob", 40);
    assertEquals("Bob", entity.getName());
    entity.takeHit(10);
    assertEquals(30, entity.getCurrentLifePoints());
  }

  @Test
  public void testTakeHitAgain() {
    LifeForm entity;
    entity = new MockLifeForm("Bob", 40);
    assertEquals("Bob", entity.getName());
    entity.takeHit(10);
    assertEquals(30, entity.getCurrentLifePoints());
    entity.takeHit(10);
    assertEquals(20, entity.getCurrentLifePoints());
  }

  @Test
  public void testAttack() throws WeaponException {
    LifeForm luke = new MockLifeForm("Luke", 50, 30);
    LifeForm tommy = new MockLifeForm("Tom", 50, 25);
    luke.attack(tommy, 2);
    assertEquals(20, tommy.getCurrentLifePoints());
  }

  @Test
  public void testDeadAttack() throws WeaponException {
    LifeForm luke = new MockLifeForm("Luke", 0, 30);
    LifeForm tommy = new MockLifeForm("Tom", 50, 25);
    luke.attack(tommy, 2);
    assertEquals(50, tommy.getCurrentLifePoints());
  }

  @Test
  public void testUsingWeapon() throws WeaponException {
    Weapon w = new PlasmaCannon();
    Weapon x = new PlasmaCannon();
    MockLifeForm form = new MockLifeForm("Bob", 100);

    assertTrue(form.pickUpWeapon(w));

    assertFalse(form.pickUpWeapon(x));

    w.fire(3);

    assertEquals(w.getCurrentAmmo(), 3);

    w.reload();

    assertEquals(w.getCurrentAmmo(), 4);


    assertEquals(form.dropWeapon(), w);
  }

  @Test
  public void testMeleeWithWeapon() throws WeaponException {
    Weapon w = new PlasmaCannon();
    MockLifeForm form = new MockLifeForm("Bob", 100, 3);
    MockLifeForm form2 = new MockLifeForm("Fred", 100);

    form.pickUpWeapon(w);

    form.attack(form2, 3);

    assertEquals(form2.getCurrentLifePoints(), 50);
  }

  @Test
  public void storeNums() {
	  MockLifeForm form = new MockLifeForm("Bob", 100, 3);
	  form.setLocation(1, 1);
	  assertEquals(1, form.getRow());
	  assertEquals(1, form.getCol());
  }

  @Test
  public void storeNegNums() {
	  MockLifeForm form = new MockLifeForm("Bob", 100, 3);
	  form.setLocation(-1, -1);
	  assertEquals(-1, form.getRow());
	  assertEquals(-1, form.getCol());
  }

  @Test
  public void storeNegNums2() {
	  MockLifeForm form = new MockLifeForm("Bob", 100, 3);
	  form.setLocation(-4, -5);
	  assertEquals(-4, form.getRow());
	  assertEquals(-5, form.getCol());
  }
  
  //Lab 6 tests
  
  @Test
  public void defaultDirection() {
	  MockLifeForm form = new MockLifeForm("Bob", 100, 3);
	  assertEquals(0, form.getDirection());
  }
  
  @Test
  public void changeDirection() {
	  MockLifeForm form = new MockLifeForm("Bob", 100, 3);
	  form.setDirection(1);
	  assertEquals(1, form.getDirection());
  }


}
