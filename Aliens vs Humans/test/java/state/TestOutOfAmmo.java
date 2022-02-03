package state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import environment.Environment;
import exceptions.AttachmentException;
import exceptions.RecoveryRateException;
import exceptions.WeaponException;
import game.Game;
import gameplay.SimpleTimer;
import gameplay.Simulator;
import lifeform.Human;
import lifeform.LifeForm;
import weapon.Pistol;
import weapon.Weapon;

public class TestOutOfAmmo {
  @Test
  public void testInitialization() {
    Environment environment = Environment.getEnvironment(10, 10);
    Game ui = new Game(10, 10, environment);
    LifeForm lf = new Human(null, 100, 10);
    environment.setUi(ui);
    environment.clearBoard();
    AiContext context = new AiContext(lf, environment);
    OutOfAmmoState o = new OutOfAmmoState(context);
    context.setCurrentState(o);
    
    assertEquals(o, context.getCurrentState());
  }
  
  @Test
  public void testReload() throws WeaponException {
    Environment environment = Environment.getEnvironment(10, 10);
    Game ui = new Game(10, 10, environment);
    LifeForm lf = new Human(null, 100, 10);
    Weapon pistol = new Pistol();
    lf.pickUpWeapon(pistol);
    environment.setUi(ui);
    environment.clearBoard();
    AiContext context = new AiContext(lf, environment);
    OutOfAmmoState o = new OutOfAmmoState(context);
    context.setCurrentState(o);
    pistol.fire(5);
    context.updateTime(1000);
    pistol.reload();
    assertEquals(10, pistol.getCurrentAmmo());
    assertEquals(context.getHasWeaponState(), context.getCurrentState());
  }
  
  @Test
  public void testState() throws WeaponException {
    Environment environment = Environment.getEnvironment(10, 10);
    Game ui = new Game(10, 10, environment);
    LifeForm lf = new Human(null, 100, 10);
    Weapon pistol = new Pistol();
    lf.pickUpWeapon(pistol);
    environment.setUi(ui);
    environment.clearBoard();
    AiContext context = new AiContext(lf, environment);
    OutOfAmmoState o = new OutOfAmmoState(context);
    context.setCurrentState(o);
    pistol.fire(5);
    context.updateTime(1000);
    pistol.reload();
    assertEquals(10, pistol.getCurrentAmmo());
    assertEquals(context.getHasWeaponState(), context.getCurrentState());
  }
  
  @Test
  public void testDead() throws AttachmentException, RecoveryRateException {
    Environment environment = Environment.getEnvironment(10, 10);
    SimpleTimer timer = new SimpleTimer();
    Game ui = new Game(10, 10, environment);
    LifeForm lf = new Human(null, 1, 1);
    LifeForm lf2 = new Human(null, 1, 1);
    Weapon pistol = new Pistol();
    Weapon pistol2 = new Pistol();
    lf.pickUpWeapon(pistol);
    lf2.pickUpWeapon(pistol2);
    environment.setUi(ui);
    environment.clearBoard();
    AiContext context = new AiContext(lf2, environment);
    OutOfAmmoState o = new OutOfAmmoState(context);
    DeadState d = new DeadState(context);
    context.setCurrentState(o);
    lf.attack(lf2); 
    assertEquals(0, lf2.getCurrentLifePoints());
    assertNotEquals(context.getDeadState(), context.getCurrentState());
  }
}
