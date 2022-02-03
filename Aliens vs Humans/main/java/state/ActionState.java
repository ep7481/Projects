package state;

import environment.Environment;
import lifeform.LifeForm;

public abstract class ActionState {
  AiContext context;
  LifeForm form;
  Environment environment;
  
  /**
   * 
   * @param context
   */
  public ActionState(AiContext context) {
    this.context = context;
    this.form = context.getLifeForm();
    this.environment = context.getEnvironment();
  }
  
  abstract void executeAction();

}
