import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This program draws a hypotrochoid curve.
 * The Main class receives the values related to the curve from user input and initialises the JFrame that the curve will be drawn on.
 */
public class Main {

  private static double fixedRadius; // The radius of the fixed, bigger circle
  private static double rollingRadius; // The radius of the rolling, smaller circle
  private static double offset; // The offset of the pen from the center of the smaller circle
  private static double amplitude; // The factor that the drawing will be scaled by

  private static InputStreamReader input = new InputStreamReader(System.in);
  private static BufferedReader bufferedInput = new BufferedReader(input);

  public static void main(String[] args) {

    inputValues();

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    JFrame spirographFrame = new JFrame();
    spirographFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    Spirograph spirograph = new Spirograph(fixedRadius, rollingRadius, offset, amplitude);
    spirographFrame.add(spirograph); // Draws the spirograph on the frame
    spirographFrame.setSize(screenSize); // Maximizes the frame size
    spirographFrame.setVisible(true);
  }

  /**
   * This method initialises the values related to the circle that are mentioned above.
   */
  private static void inputValues() {

    try {
      System.out.print("Please enter radius of fixed circle: ");
      fixedRadius = Double.parseDouble(bufferedInput.readLine());
      System.out.println();

      System.out.print("Please enter radius of rolling circle: ");
      rollingRadius = Double.parseDouble(bufferedInput.readLine());
      System.out.println();

      System.out.print("Please enter value of the offset from the centre of the rolling circle: ");
      offset = Double.parseDouble(bufferedInput.readLine());
      System.out.println();

      System.out.print("Please enter the value of the scaling factor: ");
      amplitude = Double.parseDouble(bufferedInput.readLine());
      System.out.println();
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
