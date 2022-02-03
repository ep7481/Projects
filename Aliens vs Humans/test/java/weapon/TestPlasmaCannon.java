//Taylor Fernandez
package weapon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exceptions.WeaponException;


public class TestPlasmaCannon {

  /**
   * Checks the plasma cannon class when distance = 10
   * @throws WeaponException
   */
  @Test
  public void testPlasmaCannonWhenDistanceIs10() throws WeaponException {
    PlasmaCannon pm = new PlasmaCannon();

    int damageDone = pm.fire(10);

    assertEquals(damageDone, 50);
  }

  /**
   * checks plasma cannon when distance is 30
   * @throws WeaponException
   */
  @Test
  public void testPlasmaCannonWhenDistanceIs30() throws WeaponException {
    PlasmaCannon pm = new PlasmaCannon();

    int damageDone = pm.fire(30);

    assertEquals(damageDone, 50);
  }

  /**
   * checks plasma cannon when distance is greater than maxRange
   * @throws WeaponException
   */
   @Test
   public void testPlasmaCannonWhenDistanceIs60() throws WeaponException {
     PlasmaCannon pm = new PlasmaCannon();

     int damageDone = pm.fire(60);

     assertEquals(damageDone, 0);
   }

   /**
    * checks plasma cannon when distance is less than 0
    */
   @Test(expected = WeaponException.class)
   public void testPlasmaCannonWhenDistanceIsLessThan0() throws WeaponException {
     PlasmaCannon pm = new PlasmaCannon();

     pm.fire(-5);
   }
}
