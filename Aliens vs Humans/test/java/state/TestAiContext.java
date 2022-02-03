package state;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import environment.Environment;
import lifeform.LifeForm;
import lifeform.MockLifeForm;

public class TestAiContext {

  @Test
  public void testChangeStates() {
    Environment e = Environment.getEnvironment(7,7);
    LifeForm lionKing = new MockLifeForm("Simba", 100, 10);
    AiContext context = new AiContext(lionKing, e);
    
    assertEquals(context.getCurrentState(), context.getNoWeaponState());
    
    HasWeaponState HasWeaponState = new HasWeaponState(context);
    context.setCurrentState(HasWeaponState);
    assertEquals(context.getCurrentState(), HasWeaponState);
    
    NoWeaponState NoWeaponState = new NoWeaponState(context);
    context.setCurrentState(NoWeaponState);
    assertEquals(context.getCurrentState(), NoWeaponState);
    
    OutOfAmmoState OutOfAmmoState = new OutOfAmmoState(context);
    context.setCurrentState(OutOfAmmoState);
    assertEquals(context.getCurrentState(), OutOfAmmoState);
    
    DeadState DeadState = new DeadState(context);
    context.setCurrentState(DeadState);
    assertEquals(context.getCurrentState(), DeadState);
  }

  @Test
  public void testGetState() {
    Environment env = Environment.getEnvironment(7,7);
    LifeForm dude = new MockLifeForm("Chad", 100, 10);
    AiContext context = new AiContext(dude, env);
    
    assertTrue(context.getHasWeaponState() instanceof HasWeaponState);
    assertTrue(context.getOutOfAmmoState() instanceof OutOfAmmoState);   
    assertTrue(context.getNoWeaponState() instanceof NoWeaponState);
    assertTrue(context.getCurrentState() instanceof NoWeaponState);
    assertTrue(context.getDeadState() instanceof DeadState);
  }
}
