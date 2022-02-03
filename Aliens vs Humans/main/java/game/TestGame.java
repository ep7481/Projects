package game;

import environment.Environment;
import exceptions.AttachmentException;
import lifeform.Alien;
import lifeform.Human;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.Scope;
import weapon.Weapon;


public class TestGame {

  /**
   * runner
   * @param args
   * @throws AttachmentException
   */
  public static void main(String[] args) throws AttachmentException {
    

    

    Alien alien2 = new Alien("Mike", 100);
    alien2.setDirection(2);

    Human human2 = new Human("jimmy", 100, 4);
    human2.setDirection(1);

    Human human3 = new Human("idk anymore", 100, 5);
    human3.setDirection(3);

    Human human = new Human("Bob", 100, 4);
    Alien alien = new Alien("Jim", 100);
    
    Environment env = Environment.getEnvironment(6, 6);
    env.addLifeForm(human3, 4, 4);
    env.addLifeForm(human2, 1, 4);
    env.addLifeForm(alien2, 4, 2);
    env.addLifeForm(human, 1, 3);
    env.addLifeForm(alien, 1, 2);

    Weapon w1 = new Pistol();
    Weapon w2 = new ChainGun();
    Weapon w3 = new Scope(new PlasmaCannon());
    
    alien.pickUpWeapon(w3);
    human.pickUpWeapon(w2);

    Weapon w4 = new PlasmaCannon();
    env.addWeapon(w1, 1, 0);
    env.addWeapon(w2, 1, 1);
    env.addWeapon(w4, 1, 2);
    env.addWeapon(w1, 1, 3);
    env.addWeapon(w2, 1, 3);

    Game ui = new Game(env.getNumRows(), env.getNumCols(), env);
    env.setUi(ui);

    Invoker inv = new Invoker(env);
    inv.setGame(ui);

  }

}
