package commands;

import exceptions.EnvironmentException;
import exceptions.WeaponException;

public interface Commands {
  public void execute() throws WeaponException, EnvironmentException;
}
