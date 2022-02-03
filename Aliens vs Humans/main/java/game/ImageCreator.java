package game;

import javax.swing.ImageIcon;

import java.awt.image.BufferedImage;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicGraphicsUtils;

import environment.Environment;
import lifeform.Alien;
import lifeform.Human;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.PlasmaCannon;

public class ImageCreator {
  BufferedImage image = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);
  Graphics drawer = image.getGraphics();

  /**draws elements**/
  
  public void drawElements(JButton[][] buttons, Environment environ) {
    JButton[][] buttonArray = buttons;
    for (int i = 0; i < buttonArray.length; i++) {
      for (int j = 0; j < buttonArray[i].length; j++) {
        drawCell(i, j, environ, buttons[i][j]);
      }
    }
  }
  
  /**draws cell**/
  
  public void drawCell(int i, int j, Environment environ, JButton button) {
    BufferedImage image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
    Graphics drawer = image.getGraphics();

    // creates the environment of the square
    drawer.setColor(new Color(60, 102, 0));
    drawer.fillRect(0, 0, 50, 50);

    // sets the lifeform in the grid
    if (environ.getCell(i, j).getLifeForm() != null) {
      if (environ.getCell(i, j).getLifeForm().getClass() == Human.class) {
        drawer.setColor(new Color(255, 253, 208));
        drawer.fillOval(20, 20, 10, 10);
      } else if (environ.getCell(i, j).getLifeForm().getClass() == Alien.class) {
        drawer.setColor(new Color(0, 50, 0));
        drawer.fillOval(20, 20, 10, 10);
      } else if (environ.getCell(i, j).getLifeForm().getCurrentLifePoints() == 0) {
        drawer.setColor(new Color(255, 0, 0));
        drawer.fillOval(20, 20, 10, 10);
      }
    }

    // sets the first weapon on the groujnd
    if (environ.getCell(i, j).getWeaponsCount() 
        != 0 && environ.getCell(i, j).getWeapon1() != null) {
      if (environ.getCell(i, j).getWeapon1().toString().contains("PlasmaCannon") == true) {
        drawer.setColor(new Color(0, 0, 255));
        drawer.fillRect(40, 40, 10, 10);
      } else if (environ.getCell(i, j).getWeapon1().toString().contains("ChainGun") == true) {
        drawer.setColor(new Color(255, 255, 0));
        drawer.fillRect(0, 40, 10, 10);
      } else if (environ.getCell(i, j).getWeapon1().toString().contains("Pistol") == true) {
        // Pistol
        drawer.setColor(new Color(255, 0, 0));
        drawer.fillRect(40, 0, 10, 10);
      }
    }

    // sets the second weapon on the ground
    if (environ.getCell(i, j).getWeapon2() != null) {
      if (environ.getCell(i, j).getWeapon2()
          .getClass().toString().contains("PlasmaCannon") == true) {
        drawer.setColor(new Color(0, 0, 255));
        drawer.fillRect(40, 40, 10, 10);
      } else if (environ.getCell(i, j).getWeapon2().toString().contains("ChainGun") == true) {
        drawer.setColor(new Color(255, 255, 0));
        drawer.fillRect(0, 40, 10, 10);
      } else if (environ.getCell(i, j).getWeapon2().toString().contains("Pistol") == true) {
        // Pistol
        drawer.setColor(new Color(255, 0, 0));
        drawer.fillRect(40, 0, 10, 10);
      }
    }
    // puts the direction the lifeform is facing on the ground
    if (environ.getCell(i, j).getLifeForm() != null) {
      switch (environ.getCell(i, j).getLifeForm().getDirection()) {
        case 0:
          drawer.setColor(new Color(255, 0, 0));
          drawer.fillRect(20, 10, 10, 1);
          break;
        case 1:
          drawer.setColor(new Color(255, 0, 0));
          drawer.fillRect(40, 20, 1, 10);
          break;
        case 2:
          drawer.setColor(new Color(255, 0, 0));
          drawer.fillRect(20, 40, 10, 1);
          break;
        case 3:
          drawer.setColor(new Color(255, 0, 0));
          drawer.fillRect(10, 20, 1, 10);
          break;
          
        default:
           
      }
    }

    button.setIcon(new ImageIcon(image));
  }
  
  /**
   * 
   * highligts player
   */
  
  public ImageIcon highlightPlayer() {
    BufferedImage newImage = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);

    Graphics draw = newImage.getGraphics();
    draw.setColor(new Color(255, 255, 0));
    draw.fillRect(0, 0, 50, 50);
    return new ImageIcon(newImage);
  }
  
  /**
   * 
   * @return
   */
  
  public ImageIcon highlightPrediction() {
    BufferedImage newImage = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);

    Graphics draw = newImage.getGraphics();

    draw.setColor(new Color(255, 255, 0));
    draw.fillRect(0, 0, 50, 50);

    draw.setColor(new Color(255, 255, 0));
    draw.fillOval(20, 20, 10, 10);

    return new ImageIcon(newImage);
  }

  /**
   * 
   * @return
   */
  
  public ImageIcon createEnvironment() {
    BufferedImage newImage = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);

    Graphics draw = newImage.getGraphics();
    draw.setColor(new Color(60, 102, 0));
    draw.fillRect(0, 0, 50, 50);
    return new ImageIcon(newImage);
  }

  /**
   * 
   * @return
   */
  
  public ImageIcon createHuman() {
    BufferedImage newImage = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);

    Graphics draw = newImage.getGraphics();
    draw.setColor(new Color(60, 102, 0));
    draw.fillRect(0, 0, 50, 50);

    drawer.setColor(new Color(0, 255, 0));
    drawer.fillOval(20, 20, 10, 10);

    return new ImageIcon(newImage);
  }

  /**
   * 
   * @return
   */
  
  public ImageIcon createAlien() {
    BufferedImage newImage = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);

    Graphics draw = newImage.getGraphics();
    draw.setColor(new Color(60, 102, 0));
    draw.fillRect(0, 0, 50, 50);

    drawer.setColor(new Color(200, 240, 100));
    drawer.fillOval(20, 20, 10, 10);

    return new ImageIcon(newImage);
  }

  /**
   * 
   * @return
   */
  
  public ImageIcon createPistol() {
    BufferedImage newImage = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);

    Graphics draw = newImage.getGraphics();
    draw.setColor(new Color(60, 102, 0));
    draw.fillRect(0, 0, 50, 50);

    drawer.setColor(new Color(255, 0, 0));
    drawer.fillRect(40, 0, 10, 10);

    return new ImageIcon(newImage);
  }

  /**
   * 
   * @return
   */
  
  public ImageIcon createChainGun() {
    BufferedImage newImage = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);

    Graphics draw = newImage.getGraphics();
    draw.setColor(new Color(60, 102, 0));
    draw.fillRect(0, 0, 50, 50);

    drawer.setColor(new Color(255, 255, 0));
    drawer.fillRect(0, 40, 10, 10);

    return new ImageIcon(newImage);
  }

  /**
   * 
   * @return
   */
  
  public ImageIcon createPlasmaCannon() {
    BufferedImage newImage = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);

    Graphics draw = newImage.getGraphics();
    draw.setColor(new Color(60, 102, 0));
    draw.fillRect(0, 0, 50, 50);

    drawer.setColor(new Color(0, 0, 255));
    drawer.fillRect(40, 40, 10, 10);

    return new ImageIcon(newImage);
  }
}
