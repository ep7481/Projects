package environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import lifeform.LifeForm;
import lifeform.MockLifeForm;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.Weapon;

/**
 * The test cases for the Cell class
 *
 * @author lh2565
 *
 */
public class TestCell {
  /**
   * As initialization, the Cell should be empty and not contain a LifeForm.
   */
  @Test
  public void testInitialization() {
    Cell cell = new Cell();
    assertNull(cell.getLifeForm());
    assertEquals(cell.getWeaponsCount(), 0);
  }

  @Test
  public void testAddLifeForm() {
    LifeForm bob = new MockLifeForm("Bob", 40);
    LifeForm fred = new MockLifeForm("Fred", 40);
    Cell cell = new Cell();
    // The cell is empty so this should work.
    boolean success = cell.addLifeForm(bob);
    assertTrue(success);
    assertEquals(bob, cell.getLifeForm());
    // The cell is not empt so this hould fail.
    success = cell.addLifeForm(fred);
    assertFalse(success);
    assertEquals(bob, cell.getLifeForm());
  }

  @Test
  public void testRemoveLifeForm() {
    LifeForm remove = new MockLifeForm("Lenny", 40);
    Cell cell = new Cell();
    boolean fail = cell.addLifeForm(remove);
    assertTrue(fail);
    cell.removeLifeForm();
    assertEquals(null, cell.getLifeForm());
    assertTrue(fail);
  }

  @Test
  public void testNoAdd() {
    LifeForm bob = new MockLifeForm("Bob", 40);
    LifeForm fred = new MockLifeForm("Fred", 40);
    Cell cell = new Cell();
    boolean success = cell.addLifeForm(bob);
    assertTrue(success);
    assertEquals(bob, cell.getLifeForm());
    success = cell.addLifeForm(fred);
    assertFalse(success);
    assertEquals(bob, cell.getLifeForm());
  }

  @Test
  public void testWeaponFunction(){
    Cell c1 = new Cell();

    Weapon w1 = new PlasmaCannon();

    Weapon w2 = new ChainGun();

    Weapon w3 = new Pistol();

    assertTrue(c1.addWeapon(w1));
    assertEquals(c1.getWeaponsCount(), 1);


    assertTrue(c1.addWeapon(w2));
    assertEquals(c1.getWeaponsCount(), 2);

    assertFalse(c1.addWeapon(w3));

    c1.removeWeapon(w1);
    assertEquals(c1.getWeaponsCount(), 1);

  }
}
