package lifeform;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exceptions.RecoveryRateException;
import recovery.RecoveryLinear;

public class TestHuman {

  @Test
  public void testInitialization() {
    Human doug;
    doug = new Human("Doug", 50, 0);
    assertEquals("Doug", doug.getName());
  }

  @Test
  public void testSetArmor() {
    Human doug;
    doug = new Human("Doug", 50, 0);
    doug.setArmorPoints(100);
    assertEquals(100, doug.getArmorPoints());
  }

  @Test
  public void testGetArmor() {
    Human doug;
    doug = new Human("Doug", 50, 0);
    doug.setArmorPoints(100);
    assertEquals(100, doug.getArmorPoints());
  }

  @Test
  public void testNegativeArmor() {
    Human doug;
    doug = new Human("Doug", 50, 0);
    doug.setArmorPoints(-10);
    assertEquals(0, doug.getArmorPoints());
  }

  @Test
  public void testAttack() throws RecoveryRateException {
    Human luke = new Human("Lenny", 50, 0);
    Human tommy = new Human("Tom", 50, 0);
    Alien allen = new Alien("Allen", 10, new RecoveryLinear(3));
    luke.attack(tommy);
    luke.attack(allen);
    allen.recover();
    assertEquals(45, tommy.getCurrentLifePoints());
    assertEquals(8, allen.getCurrentLifePoints());
  }

  @Test
  public void testArmorDamage() {
    Human luke = new Human("Lenny", 50, 10);
    luke.takeHit(10);
    assertEquals(0, luke.getArmorPoints());
    assertEquals(50, luke.getCurrentLifePoints());
  }

  @Test
  public void testArmorDamage2() {
    Human luke = new Human("Lenny", 50, 10);
    luke.takeHit(15);
    assertEquals(0, luke.getArmorPoints());
    assertEquals(45, luke.getCurrentLifePoints());
  }

  @Test
  public void testArmorDamage3() {
    Human luke = new Human("Lenny", 50, 0);
    luke.setArmorPoints(10);
    luke.takeHit(5);
    assertEquals(5, luke.getArmorPoints());
    assertEquals(50, luke.getCurrentLifePoints());
  }
  
//Lab 6 Tests
  @Test 
  public void testMaxSpeed() {
    Human human = new Human("drowsy", 10, 4);
    
    assertEquals(human.getMaxSpeed(), 3);
  }
}
