package state;

import static org.junit.Assert.*;
import org.junit.Test;

import environment.Environment;
import exceptions.AttachmentException;
import exceptions.RecoveryRateException;
import game.Game;
import gameplay.SimpleTimer;
import gameplay.Simulator;
import lifeform.Alien;
import lifeform.Human;
import weapon.Weapon;

public class TestSimulator {

  @Test
  public void testInitialization() throws AttachmentException, RecoveryRateException {
    Environment environment = Environment.getEnvironment(10, 10);
    Game ui = new Game(10, 10, environment);
    environment.setUi(ui);
    
    environment.clearBoard();
    SimpleTimer timer = new SimpleTimer();
    Simulator sim = new Simulator(environment, timer, 10, 10);
    int weapons = 0;
    int humans = 0;
    int aliens = 0; 
    
    for (int i = 0; i < environment.getNumRows() - 1; i++) {
      for (int j = 0; j < environment.getNumCols() - 1; j++) {
        if (environment.getLifeForm(i, j) instanceof Human) {
          humans++;
        } else if (environment.getLifeForm(i, j) instanceof Alien) {
          aliens++;
        }
        Weapon[] gunz = environment.getWeapons(i, j);
        if (gunz[0] != null) {
          weapons++;
        }
        if (gunz[1] != null) {
          weapons++;
        }
      }
    }
    //simulation will have 10 humans and 15 aliens with 20 weapons
    assertEquals(10, humans);
    assertEquals(10, aliens);
    assertEquals(0, weapons);
  }
  
  @Test
  public void testUpdateTime() throws AttachmentException, RecoveryRateException {
    Environment environment = Environment.getEnvironment(10, 10);
    environment.clearBoard();
    
    Game ui = new Game(10, 10, environment);
    environment.setUi(ui);
    SimpleTimer timer = new SimpleTimer();
    Simulator sim = null;
    sim = new Simulator(environment, timer, 10, 10);

    sim.updateTime(5);
    
  }
  
}
