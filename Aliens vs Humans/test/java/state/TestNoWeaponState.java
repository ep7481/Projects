package state;

import static org.junit.Assert.*;

import org.junit.Test;

import environment.Environment;
import game.Game;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import weapon.Pistol;
import weapon.PlasmaCannon;

public class TestNoWeaponState {

//	@Test
//	public void testWeaponInCell() {
//		Environment environment = Environment.getEnvironment(10, 10);
//	    environment.clearBoard();
//	    Game game = new Game(10, 10, environment);
//	    environment.setUi(game);
//	    LifeForm lf = new Human("Guy", 20, 1);
//	    Pistol p = new Pistol();
//	    AiContext context = new AiContext(lf, environment);
//	    environment.addLifeForm(lf, 2, 2);
//	    environment.addWeapon(p, 2, 2);
//	    context.setCurrentState(context.getNoWeaponState());
//	    context.updateTime(500);
//	 
//	    assertEquals(context.environment.getCell(2, 2).getWeaponsCount() , 1);
//	    assertEquals(context.getCurrentState(), context.getNoWeaponState());
//	    
//	}
	 
	@Test
	public void testNoWeaponInCell() {
		Environment environment = Environment.getEnvironment(10, 10);
	    environment.clearBoard(); 
	    Game game = new Game(10, 10, environment);
	    environment.setUi(game);
	    LifeForm lf = new Human("Guy", 20, 1);
	    environment.addLifeForm(lf, 2, 2);
	    AiContext context = new AiContext(lf, environment);
	    context.setCurrentState(context.getNoWeaponState());
	    context.updateTime(500);
	    
	    assertEquals(context.environment.getCell(2, 2).getWeaponsCount(), 0);
	    assertEquals(context.getCurrentState(), context.getNoWeaponState());
	}
	 
	@Test
	public void testIfDead() {
		  Environment environment = Environment.getEnvironment(10, 10);
		    environment.clearBoard();
		    LifeForm lf = new Human("Donkey", 100, 5);
		    LifeForm lf2 = new Alien("dave", 100);
		    AiContext context = new AiContext(lf, environment);
		    PlasmaCannon plasmaCannon = new PlasmaCannon();
		    lf.pickUpWeapon(plasmaCannon);
		    lf2.takeHit(100);
		    environment.addLifeForm(lf, 2, 0);
		    environment.addLifeForm(lf2, 1, 0);
		    context.setCurrentState(context.getNoWeaponState());
		    context.updateTime(500);
		    
		    assertEquals(lf2.getCurrentLifePoints(), 0); 
		  }
	}


