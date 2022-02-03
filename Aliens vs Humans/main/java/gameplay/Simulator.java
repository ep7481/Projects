package gameplay;

import environment.Environment;
import exceptions.AttachmentException;
import exceptions.RecoveryRateException;
import game.Game;
import lifeform.Alien;
import lifeform.Human;
import recovery.RecoveryBehavior;
import recovery.RecoveryFractional;
import recovery.RecoveryLinear;
import recovery.RecoveryNone;
import state.AiContext;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.PowerBooster;
import weapon.Scope;
import weapon.Stabilizer;
import weapon.Weapon;

import java.util.Random;

public class Simulator implements TimerObserver {
  static Environment e;
  SimpleTimer timer;
  int numAliens;
  int numHumans;

  /**
   * 
   * @param e
   * @param timer
   * @param numHumans
   * @param numAliens
   * @throws AttachmentException
   * @throws RecoveryRateException
   */
  public Simulator(Environment e, SimpleTimer timer, int numHumans, int numAliens) 
      throws AttachmentException, RecoveryRateException {
    this.e = e;
    this.timer = timer;
    this.numHumans = numHumans;
    this.numAliens = numAliens;
    Random var = new Random();
    RecoveryBehavior rb = null;
    RecoveryLinear rl;
    RecoveryFractional rf;
    RecoveryNone rn = new RecoveryNone();
    Weapon wp1 = new Pistol();
    Weapon wp2 = new Scope(new Pistol());
    Weapon wp3 = new Scope(new Scope(new Pistol()));
    Weapon wp4 = new ChainGun();
    Weapon wp5 = new PowerBooster(new ChainGun());
    Weapon wp6 = new Stabilizer(new PowerBooster(new ChainGun()));
    Weapon wp7 = new PlasmaCannon();
    Weapon wp8 = new Stabilizer(new PlasmaCannon());
    Weapon wp9 = new PowerBooster(new PlasmaCannon());
    Weapon wp10 = new Scope(new PlasmaCannon());
    Weapon choice = null;
    for (int i = 0; i < numAliens; i++) {
      rl = new RecoveryLinear(var.nextInt(5));
      rf = new RecoveryFractional(var.nextInt(5));
      switch (var.nextInt(2)) {
        case 0: rb = rn; 
           break;
        case 1: rb = rf; 
          break;
        case 2: rb = rl; 
          break;
        default:
          System.out.println("switch statement error");
      }
      int row = var.nextInt(e.getNumRows() - 1);
      int col = var.nextInt(e.getNumCols() - 1);
      int z = i;
      while (e.getCell(row, col).getLifeForm() != null) {
        row = var.nextInt(e.getNumRows() - 1);
        col = var.nextInt(e.getNumCols() - 1);
      }
      e.addLifeForm(new Alien("T-Money" + z, 20, rb), row, col);
      timer.addTimeObserver(new AiContext(e.getLifeForm(row, col), e));
    }
    for (int i = 0; i < numHumans; i++) {
      int row = var.nextInt(e.getNumRows() - 1);
      int col = var.nextInt(e.getNumCols() - 1);
      int z = i;
      while (e.getCell(row, col).getLifeForm() != null) {
        row = var.nextInt(e.getNumRows() - 1);
        col = var.nextInt(e.getNumCols() - 1);
      }
      Human human = new Human("Taylor" + z, 20, var.nextInt(9));
      e.addLifeForm(human, row, col);
      
      timer.addTimeObserver(new AiContext(human, e));
      
    }
    for (int i = 0; i < numAliens + numHumans; i++) {
      int row = var.nextInt(e.getNumRows() - 1);
      int col = var.nextInt(e.getNumCols() - 1);
      while (e.getCell(row, col).getWeaponsCount() == 2) {
        row = var.nextInt(e.getNumRows() - 1);
        col = var.nextInt(e.getNumCols() - 1);
      }
      switch (var.nextInt(3)) {
        case 0: choice = wp1; 
          break;
        case 1: choice = wp8; 
          break;        
        case 2: choice = wp5; 
          break;
        case 3: choice = wp2; 
          break;
        default:
          System.out.println("switch statement error");
      }
      e.addWeapon(choice, row, col);
    }
    for (int i = 0; i < e.getNumRows(); i++) {
      for (int j = 0; j < e.getNumCols(); j++) {
        e.getGameUi().drawCell(i, j);
      }
    }
  }
  
  /**
   * 
   * @param args
   * @throws RecoveryRateException
   * @throws AttachmentException
   */
  public static void main(String[] args) throws RecoveryRateException, AttachmentException {
    Environment e = Environment.getEnvironment(10, 10);
    Game gui = new Game(10,10, e); 
    e.setUi(gui);
    
    
    //simulator starts here for AI vs AI play 
    SimpleTimer timer = new SimpleTimer(1000);  
  
    //15 humans 
    //10 Aliens 
    Simulator sim = new Simulator(e, timer, 10, 10);  
  
    //start the timer 
    timer.start(); 
  }

  @Override
  public void updateTime(int time) {
    time++;
    
  }

}
