package state;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import environment.Environment;
import game.Game;
import lifeform.Human;
import lifeform.LifeForm;
import weapon.Pistol;
import weapon.Weapon;

public class TestDeadState {

  @Test
  public void testWithWeapon() {
    Environment environment = Environment.getEnvironment(10, 10);
    environment.clearBoard();
    Game game = new Game(10, 10, environment);
    environment.setUi(game);
    LifeForm lf = new Human("Garry", 200, 0);
    Pistol pistol = new Pistol();
    lf.pickUpWeapon(pistol);
    lf.takeHit(200);
    AiContext context = new AiContext(lf, environment);
    boolean hastheweapon = false;
    Weapon[] temp;
    
    environment.addLifeForm(lf, 5, 5);
    context.setCurrentState(context.getDeadState());
    assertEquals(context.getCurrentState(), context.getDeadState());
    assertEquals(lf.getCurrentLifePoints(), 0);  
    
    context.updateTime(1);
    
    assertEquals(lf.getCurrentLifePoints(), lf.getMaxLifePoints());
    assertEquals(lf.hasWeapon(), false);
    
    int row = lf.getRow(), col = lf.getCol();
    assertTrue(environment.getLifeForm(row, col) != null);

    for (row = 0; row < environment.getNumRows(); row++) {
      for (col = 0; col < environment.getNumCols(); col++) {
        temp = environment.getWeapons(row, col);
        
        if (temp[0] == pistol || temp[1] == pistol) {
          hastheweapon = true;
          break;
        } 
      }
      if (hastheweapon) {
        break;
      }
    }
  }
  
  @Test
  public void testWithoutWeapon() {
    Environment environment = Environment.getEnvironment(10, 10);
    environment.clearBoard();
    Game game = new Game(10, 10, environment);
    environment.setUi(game);
    
    LifeForm lf = new Human("Garry", 200, 0);
    AiContext context = new AiContext(lf, environment);
    
    lf.takeHit(200);
    environment.addLifeForm(lf, 4, 4);
    context.setCurrentState(context.getDeadState());
    assertEquals(context.getCurrentState(), context.getDeadState());
    assertEquals(lf.getCurrentLifePoints(), 0);
    
    context.updateTime(1);
    
    assertEquals(lf.getCurrentLifePoints(), lf.getMaxLifePoints());
    
    int row = lf.getRow(), col = lf.getCol();
    assertTrue(environment.getLifeForm(row, col) != null);
  }
}
