package lifeform;

public class Human extends LifeForm {
  int armor;

  /**
   * constructor for human
   *
   * @param n
   * @param p
   * @param a
   */
  public Human(String n, int p, int a) {
    super(n, p, 5);
    armor = a;
    maxSpeed = 3;
  }

  /**
   * sets the armor
   *
   * @param extra
   */
  public void setArmorPoints(int extra) {
    if (extra < 0) {
      armor = 0;
    } else {
      armor = extra;
    }
  }

  /**
   *
   * @return armor rating
   */
  public int getArmorPoints() {
    if (armor < 0) {
      return 0;
    }
    return armor;
  }

  public String getType() {
    return "Human";
  }

  @Override
  public void takeHit(int damage) {
    int i = 0;
    int temp = damage;
    if (armor <= 0) {
      if (points - damage >= 0) {
        points -= damage;
      }
    } else if (armor - damage < 0) {
      points = points - (damage - armor);
      armor = 0;

    } else if (armor > 0) {
      armor -= damage;
    }
    if (points <= 0) {
      points = 0;
    }
  }
}
