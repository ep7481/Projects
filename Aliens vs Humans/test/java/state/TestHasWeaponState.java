package state;

import static org.junit.Assert.*;

import org.junit.Test;

import environment.Environment;
import exceptions.WeaponException;
import game.Game;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import weapon.Pistol;
import weapon.PlasmaCannon;

public class TestHasWeaponState {

  @Test
  public void testNoTarget() {
    Environment environment = Environment.getEnvironment(10, 10);
    environment.clearBoard();
    Game game = new Game(10, 10, environment);
    environment.setUi(game);
    LifeForm lf = new Human("Donkey", 500, 1);
    lf.pickUpWeapon(new Pistol());
    AiContext context = new AiContext(lf, environment);
    environment.addLifeForm(lf, 3, 3);
    context.setCurrentState(context.getHasWeaponState());
    context.updateTime(500);
    
    // Check if the lifeform is not facing north
    assertNotEquals(lf.getDirection(), 0);
  }
 
  @Test
  public void testTargetSameType() {
    Environment environment = Environment.getEnvironment(10, 10);
    environment.clearBoard();
    Game game = new Game(10, 10, environment);
    environment.setUi(game);
    LifeForm lf = new Human("Donkey", 100, 5);
    LifeForm lf2 = new Human("dave", 5, 5);
    lf.pickUpWeapon(new PlasmaCannon());
    AiContext context = new AiContext(lf, environment);
    environment.addLifeForm(lf, 2, 0);
    environment.addLifeForm(lf2, 1, 0);
    context.setCurrentState(context.getHasWeaponState());
    context.updateTime(500);

    assertNotEquals(lf.getDirection(), 0);
    assertEquals(lf2.getCurrentLifePoints(), 5);
  }
  
  @Test
  public void testTargetDifferentType() {
    Environment environment = Environment.getEnvironment(10, 10);
    environment.clearBoard();
    Game game = new Game(10, 10, environment);
    environment.setUi(game);
    LifeForm lf = new Human("Donkey", 100, 5);
    LifeForm lf2 = new Alien("dave", 100);
    lf.pickUpWeapon(new PlasmaCannon());
    AiContext context = new AiContext(lf, environment);
    environment.addLifeForm(lf, 2, 0);
    environment.addLifeForm(lf2, 1, 0);
    lf.setDirection(0);
    context.setCurrentState(context.getHasWeaponState());
    context.updateTime(500);
    
    //The LifeForm should shoot
   // assertEquals(lf.getDirection(), 0);
    assertEquals(lf2.getCurrentLifePoints(), lf2.getMaxLifePoints());
  }
  
  @Test
  public void testValidTargetOneShotLeft() throws WeaponException {
    Environment environment = Environment.getEnvironment(10, 10);
    environment.clearBoard();
    Game game = new Game(10, 10, environment);
    environment.setUi(game);
    LifeForm lf = new Human("Donkey", 100, 5);
    LifeForm lf2 = new Alien("dave", 100);
    AiContext context = new AiContext(lf, environment);
    PlasmaCannon plasmaCannon = new PlasmaCannon();
    for (int i = 0; i < 3; i++) {
      try {
        plasmaCannon.fire(10);
        plasmaCannon.updateTime(1); 
      } catch (WeaponException e) {
        e.printStackTrace();
      }
    }
    lf.pickUpWeapon(plasmaCannon);
    environment.addLifeForm(lf, 2, 0);
    environment.addLifeForm(lf2, 1, 0);
    plasmaCannon.fire(5);
    context.setCurrentState(context.getHasWeaponState());
    context.updateTime(500); 
     
    assertEquals(plasmaCannon.getCurrentAmmo(), 0);
    //assertNotEquals(lf2.getCurrentLifePoints(), lf2.getMaxLifePoints());
    assertEquals(context.getCurrentState(), context.getOutOfAmmoState());
  } 
  
  @Test
  public void testTargetOutOfRange() {
    Environment environment = Environment.getEnvironment(10, 10);
    environment.clearBoard();
    Game game = new Game(10, 10, environment);
    environment.setUi(game);
    LifeForm lf = new Human("Donkey", 100, 5);
    LifeForm lf2 = new Alien("dave", 100);
    AiContext context = new AiContext(lf, environment);
    PlasmaCannon plasmaCannon = new PlasmaCannon();
    lf.pickUpWeapon(plasmaCannon);
    environment.addLifeForm(lf, 0, 1);
    environment.addLifeForm(lf2, 0, 9);
    lf.setDirection(1);
    context.setCurrentState(context.getHasWeaponState());
    context.updateTime(500);
    
    assertEquals(plasmaCannon.getCurrentAmmo(), plasmaCannon.getMaxAmmo());
    assertEquals(lf2.getCurrentLifePoints(), lf2.getMaxLifePoints());
    assertNotEquals(lf.getDirection(), 1);
  }
   
  @Test
  public void testTargetDead() {
    Environment environment = Environment.getEnvironment(10, 10);
    environment.clearBoard();
    Game game = new Game(10, 10, environment);
    environment.setUi(game);
    LifeForm lf = new Human("Donkey", 100, 5);
    LifeForm lf2 = new Alien("dave", 100);
    AiContext context = new AiContext(lf, environment);
    PlasmaCannon plasmaCannon = new PlasmaCannon();
    lf.pickUpWeapon(plasmaCannon);
    lf2.takeHit(100);
    environment.addLifeForm(lf, 2, 0);
    environment.addLifeForm(lf2, 1, 0);
    context.setCurrentState(context.getHasWeaponState());
    context.updateTime(500);
    
    assertEquals(plasmaCannon.getCurrentAmmo(), plasmaCannon.getMaxAmmo());
    assertEquals(lf2.getCurrentLifePoints(), 0);
    assertNotEquals(lf.getDirection(), 0); 
  }
}
