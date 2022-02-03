package lifeform;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import exceptions.RecoveryRateException;
import exceptions.WeaponException;
import gameplay.SimpleTimer;
import recovery.RecoveryLinear;
import recovery.RecoveryNone;

public class TestAlien {

  @Test
  public void testInitialization() {
    Alien allen;
    allen = new Alien("Allen", 25);
    assertEquals("Allen", allen.getName());
    assertEquals(25, allen.getMaxLifePoints());
  }

  @Test
  public void testHurtAlien() throws RecoveryRateException {
    Alien allen;
    allen = new Alien("Allen", 10, new RecoveryLinear(3));
    allen.takeHit(5);
    allen.recover();
    assertEquals(8, allen.getCurrentLifePoints());
  }

  @Test
  public void testAttackRecover() throws RecoveryRateException, WeaponException {
    SimpleTimer st = new SimpleTimer(1000);
    Alien allen = new Alien("Allen", 10, new RecoveryLinear(3));
    Alien alex = new Alien("Alex", 12, new RecoveryLinear(3));
    allen.attack(alex, 5);
    alex.recover();
    assertEquals(5, alex.getCurrentLifePoints());
  }

  @Test
  public void testCombatRecovery() throws RecoveryRateException {
    SimpleTimer st = new SimpleTimer(1000);
    Alien allen = new Alien("Allen", 10, new RecoveryLinear(2), 2);
    st.addTimeObserver(allen);
    allen.takeHit(8);
    assertEquals(2, allen.getCurrentLifePoints());
    st.timeChanged();
    assertEquals(2, allen.getCurrentLifePoints());
    st.timeChanged();
    assertEquals(4, allen.getCurrentLifePoints());
    st.timeChanged();
    assertEquals(4, allen.getCurrentLifePoints());
    st.timeChanged();
    assertEquals(6, allen.getCurrentLifePoints());
  }

  @Test
  public void testCombatRecovery1() throws RecoveryRateException {
    SimpleTimer st = new SimpleTimer(1000);
    Alien allen = new Alien("Allen", 10, new RecoveryLinear(2), 2);
    st.addTimeObserver(allen);
    allen.takeHit(8);
    assertEquals(2, allen.getCurrentLifePoints());
    st.timeChanged();
    assertEquals(2, allen.getCurrentLifePoints());
    st.timeChanged();
    assertEquals(4, allen.getCurrentLifePoints());
    st.timeChanged();
    assertEquals(4, allen.getCurrentLifePoints());
    st.timeChanged();
    assertEquals(6, allen.getCurrentLifePoints());
  }

  @Test
  public void testCombatRecovery2() throws RecoveryRateException {
    SimpleTimer st = new SimpleTimer(1000);
    Alien allen = new Alien("Allen", 10, new RecoveryLinear(3), 2); // recovered by, rounds
    st.addTimeObserver(allen);
    allen.takeHit(8);
    assertEquals(2, allen.getCurrentLifePoints());
    st.timeChanged();
    assertEquals(2, allen.getCurrentLifePoints());
    st.timeChanged();
    assertEquals(5, allen.getCurrentLifePoints());
  }

  @Test
  public void testCombatRecovery3() throws RecoveryRateException {
    SimpleTimer st = new SimpleTimer(1000);
    Alien allen = new Alien("Allen", 10, new RecoveryLinear(4), 5);
    st.addTimeObserver(allen);
    allen.takeHit(8);
    assertEquals(2, allen.getCurrentLifePoints());
    st.timeChanged();
    assertEquals(2, allen.getCurrentLifePoints());
    st.timeChanged();
    assertEquals(2, allen.getCurrentLifePoints());
    st.timeChanged();
    assertEquals(2, allen.getCurrentLifePoints());
    st.timeChanged();
    assertEquals(2, allen.getCurrentLifePoints());
    st.timeChanged();
    assertEquals(6, allen.getCurrentLifePoints());
  }

  @Test
  public void testCombatRecovery0() throws RecoveryRateException {
    SimpleTimer st = new SimpleTimer(1000);
    Alien allen = new Alien("Allen", 10, new RecoveryNone(), 2); // recovered by, rounds
    st.addTimeObserver(allen);
    allen.takeHit(8);
    assertEquals(2, allen.getCurrentLifePoints());
    st.timeChanged();
    assertEquals(2, allen.getCurrentLifePoints());
    st.timeChanged();
    assertEquals(2, allen.getCurrentLifePoints());
  }

  @Test
  public void testCombatRecoveryDead() throws RecoveryRateException {
    SimpleTimer st = new SimpleTimer(1000);
    Alien allen = new Alien("Allen", 10, new RecoveryLinear(0), 2); // recovered by, rounds
    st.addTimeObserver(allen);
    allen.takeHit(100);
    assertEquals(0, allen.getCurrentLifePoints());
    st.timeChanged();
    assertEquals(0, allen.getCurrentLifePoints());
    st.timeChanged();
    assertEquals(0, allen.getCurrentLifePoints());
  }

  @Test
  public void testException() throws RecoveryRateException {
    SimpleTimer st = new SimpleTimer(1000);
    Alien allen = new Alien("Allen", 10, new RecoveryLinear(2), 1); // recovered by, rounds
    st.addTimeObserver(allen);
    allen.takeHit(100);
    assertEquals(0, allen.getCurrentLifePoints());
    st.timeChanged();
    assertEquals(0, allen.getCurrentLifePoints());
    st.timeChanged();
    assertEquals(0, allen.getCurrentLifePoints());
  }
  @Test
  public void testRemove() throws RecoveryRateException {
    SimpleTimer st = new SimpleTimer(1000);
    Alien allen = new Alien("Allen", 10, new RecoveryLinear(2), 2);
    st.addTimeObserver(allen);
    allen.takeHit(8);
    assertEquals(2, allen.getCurrentLifePoints());
    st.timeChanged();
    assertEquals(2, allen.getCurrentLifePoints());
    st.timeChanged();
    st.removerTimeObserver(allen);
    assertEquals(4, allen.getCurrentLifePoints());
    st.timeChanged();
    assertEquals(4, allen.getCurrentLifePoints());
    st.timeChanged();
    assertEquals(4, allen.getCurrentLifePoints());
  }


  @Ignore
  public void test1() throws RecoveryRateException {
    Alien allen = new Alien("Allen", 10, new RecoveryLinear(2), -2);
    assertEquals("hi", allen.getRecoveryRate());
  }
  
  //Lab 6 Tests
  @Test 
  public void testMaxSpeed() {
    Alien alien = new Alien("drowsy", 10);
    
    assertEquals(alien.getMaxSpeed(), 2);
  }
}
