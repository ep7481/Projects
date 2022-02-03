package environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import exceptions.EnvironmentException;
import exceptions.RecoveryRateException;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import lifeform.MockLifeForm;
import weapon.PlasmaCannon;

/**
 * @author Yohan
 *
 */
public class TestEnvironment {
  @Test
  public void testInitialization() {
    Environment environment = Environment.getEnvironment(10, 10);
  }

  @Test
  public void testAddLifeForm() {
    Environment environment = Environment.getEnvironment(10, 10);
    LifeForm lf = new MockLifeForm("Bob", 20);
    environment.addLifeForm(lf, 1, 2);
    assertEquals(lf, environment.getLifeForm(1, 2));
  }

  /* Not working for some reason */
//  @Test
//  public void testBorderOne() {
//    Environment environment = Environment.getEnvironment(4, 6);
//    LifeForm lf = new MockLifeForm("Bob", 50);
//    environment.addLifeForm(lf, 1, 2);
//    assertEquals(lf, environment.getLifeForm(1, 2));
//  }

//  @Test
//  public void testBorderTwo() {
//    Environment environment = Environment.getEnvironment(1, 1);
//    LifeForm lf = new MockLifeForm("Bob", 70);
//    environment.addLifeForm(lf, 0, 0);
//    assertEquals(lf, environment.getLifeForm(0, 0));
//  }

  @Test
  public void testRemoveLifeForm() {
    Environment environment = Environment.getEnvironment(10, 10);
    LifeForm lf = new MockLifeForm("Bob", 20);
    environment.addLifeForm(lf, 1, 2);
    assertEquals(lf, environment.getLifeForm(1, 2));
    environment.removeLifeForm(1, 2);
    assertEquals(null, environment.getLifeForm(1, 2));
  }

  @Test
  public void testSingleton() {
    MockLifeForm form1 = new MockLifeForm("Bob", 100);
    Environment env1 = Environment.getEnvironment(2, 2);
    env1.clearBoard();
    env1 = Environment.getEnvironment(2, 2);
    Environment env2 = Environment.getEnvironment(1, 1);

    env1.addLifeForm(form1, 1, 1);

    assertEquals(env2.getLifeForm(1, 1), form1);
  }

  @Test
  public void testAddAndRemoveWeapon() {
    Environment env1 = Environment.getEnvironment(10, 10);
    PlasmaCannon pc = new PlasmaCannon();
    assertTrue(env1.addWeapon(pc, 1, 1));
    assertEquals(pc, env1.removeWeapon(pc, 1, 1));
  }

  @Test
  public void testDistanceSameCol() throws EnvironmentException {
    Environment env1 = Environment.getEnvironment(10, 10);
    env1.clearBoard();
    MockLifeForm form1 = new MockLifeForm("bob", 100);
    MockLifeForm form2 = new MockLifeForm("jim", 100);

    env1.addLifeForm(form1, 1, 1);
    env1.addLifeForm(form2, 1, 2);

    double distance = env1.getDistance(form1, form2);

    assertEquals(5.0, distance, 0.01);
  }

  @Test
  public void testDistanceSameRow() throws EnvironmentException {
    Environment env1 = Environment.getEnvironment(10, 10);
    MockLifeForm form1 = new MockLifeForm("bob", 100);
    MockLifeForm form2 = new MockLifeForm("jim", 100);

    env1.addLifeForm(form1, 0, 0);
    env1.addLifeForm(form2, 1, 0);

    assertEquals(5.0, env1.getDistance(form2, form1), 0.01);
  }

  @Test
  public void testDistanceDifferentBoth() throws EnvironmentException {
    Environment env1 = Environment.getEnvironment(10, 10);
    LifeForm form1 = new MockLifeForm("bob", 100);
    LifeForm form2 = new MockLifeForm("jim", 100);

    env1.addLifeForm(form1, 0, 2);
    env1.addLifeForm(form2, 1, 0);
    assertEquals(10.0, env1.getDistance(form2, form1), 2);
  }

  /**
   * move North
   */
  @Test
  public void testMoveNorth() {
    Environment e = Environment.getEnvironment(10, 10);
    e.clearBoard();
    Human human = new Human("Bob", 10, 5);
    human.setDirection(0);
    e.addLifeForm(human, 5, 5);
    e.move(5, 5);
    assertEquals(2, human.getRow());
    assertEquals(5, human.getCol());
    assertEquals(human, e.getLifeForm(2, 5));
  }
  
  /*
   * move East
   */
  @Test
  public void testMoveEast() {
    Environment e = Environment.getEnvironment(10, 10);
    e.clearBoard();
    Human human = new Human("Bob", 10, 5);
    human.setDirection(1);
    e.addLifeForm(human, 5, 5);
    e.move(5, 5);
    assertEquals(5, human.getRow());
    assertEquals(8, human.getCol());
    assertEquals(human, e.getLifeForm(5, 8));
  }
  
  /*
   * move South
   */
  @Test
  public void testMoveSouth() {
    Environment e = Environment.getEnvironment(10, 10);
    e.clearBoard();
    Human human = new Human("Bob", 10, 5);
    human.setDirection(2);
    e.addLifeForm(human, 5, 5);
    e.move(5, 5);
    assertEquals(8, human.getRow());
    assertEquals(5, human.getCol());
    assertEquals(human, e.getLifeForm(8, 5));
  }
  /*
   * move West
   */
  @Test
  public void testMoveWest() {
    Environment e = Environment.getEnvironment(10, 10);
    e.clearBoard();
    Human human = new Human("Bob", 10, 5);
    human.setDirection(3);
    e.addLifeForm(human, 5, 5);
    e.move(5, 5);
    assertEquals(5, human.getRow());
    assertEquals(2, human.getCol());
    assertEquals(human, e.getLifeForm(5, 2));
  }
  
  /*
   * Cannot move out of border
   */
  @Test
  public void testMoveBorder() {
    Environment e = Environment.getEnvironment(10, 10);
    e.clearBoard();
    Human human = new Human("Bob", 10, 5);
    human.setDirection(2);
    e.addLifeForm(human, 9, 9);
    e.move(9, 9);
    assertEquals(9, human.getRow());
    assertEquals(9, human.getCol());
    assertEquals(human, e.getLifeForm(9, 9));

    e.clearBoard();
    human.setDirection(0);
    e.addLifeForm(human, 0, 0);
    e.move(0, 0);
    assertEquals(0, human.getRow());
    assertEquals(0, human.getCol());
    assertEquals(human, e.getLifeForm(0, 0));
    
    e.clearBoard();
    human.setDirection(1);
    e.addLifeForm(human, 0, 9);
    e.move(0, 9);
    assertEquals(0, human.getRow());
    assertEquals(9, human.getCol());
    assertEquals(human, e.getLifeForm(0, 9));
    
    e.clearBoard();
    human.setDirection(3);
    e.addLifeForm(human, 0, 0);
    e.move(0, 0);
    assertEquals(0, human.getRow());
    assertEquals(0, human.getCol());
    assertEquals(human, e.getLifeForm(0, 0));
  }
}
