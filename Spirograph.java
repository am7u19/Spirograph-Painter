import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

/**
 * This is a class that draws a hypotrochoid curve, similar to the ones drawn by a Spirograph.
 */
public class Spirograph extends JPanel {

  private double fixedRadius;
  private double rollingRadius;
  private double offset;
  private double amplitude;

  /**
   * A constructor that sets the values required to draw the spirograph.
   *
   * @param fixedRadius The radius of the fixed, bigger circle
   * @param rollingRadius The radius of the rolling, smaller circle
   * @param offset The offset of the pen from the center of the smaller circle
   * @param amplitude The factor that the drawing will be scaled by
   */
  public Spirograph(double fixedRadius, double rollingRadius, double offset, double amplitude) {

    this.fixedRadius = fixedRadius;
    this.rollingRadius = rollingRadius;
    this.offset = offset;
    this.amplitude = amplitude;
  }

  /**
   * A method that calculates the greatest common divisor (GCD) of two numbers using Euclid's algorithm.
   *
   * @param ceilFixedRadius The result of applying the ceiling function on the fixedRadius.
   * @param ceilRollingRadius The result of applying the ceiling function on the rollingRadius.
   * @return The greatest common divisor of the inputs. This will be used for calculating the maximum value for the angle value in the parametric equation of the curve.
   */
  private int gcd(int ceilFixedRadius, int ceilRollingRadius) {

    int remainder;
    remainder = ceilFixedRadius % ceilRollingRadius;

    while (remainder != 0) {

      ceilFixedRadius = ceilRollingRadius;
      ceilRollingRadius = remainder;
      remainder = ceilFixedRadius % ceilRollingRadius;
    }

    return ceilRollingRadius;
  }

  /**
   * This method draws the curve using parametric equations.
   *
   * @param g Represents the "painting tool" that the method works with.
   */
  public void paintComponent(Graphics g) {

    Graphics2D spirograph = (Graphics2D) g;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double xCentre = screenSize.getWidth() / 2;
    double yCentre = screenSize.getHeight() / 2;
    double x1;
    double y1;

    int ceilFixedRadius = (int) Math.ceil(fixedRadius);
    int ceilRollingRadius = (int) Math.ceil(rollingRadius);
    int lcm = (ceilFixedRadius * ceilRollingRadius) / gcd(ceilFixedRadius, ceilRollingRadius);
    double max = (2 * Math.PI * lcm) / fixedRadius; // Calculates the maximum value of the angleValue

    double radiusRatio = fixedRadius / rollingRadius;

    spirograph.translate(xCentre, yCentre); // Centers drawing on screen

    int numberOfPoints = 20000;

    int i = 0;

    double angleValue = i * max / (double) numberOfPoints;
    double x2 = amplitude * ((fixedRadius - rollingRadius) * Math.cos(angleValue) +
        offset * Math.cos((radiusRatio - 1) * angleValue));
    double y2 = amplitude * ((fixedRadius - rollingRadius) * Math.sin(angleValue) -
        offset * Math.sin((radiusRatio - 1) * angleValue));

    x1 = x2;
    y1 = y2;

    // The above lines of code initialise the first point in the drawing

    for (i = 1; i <= numberOfPoints; i++) {

      angleValue = i * max / (double) numberOfPoints;
      x2 = amplitude * ((fixedRadius - rollingRadius) * Math.cos(angleValue) +
          offset * Math.cos((radiusRatio - 1) * angleValue));
      y2 = amplitude * ((fixedRadius - rollingRadius) * Math.sin(angleValue) -
          offset * Math.sin((radiusRatio - 1) * angleValue));

      Shape line = new Line2D.Double(x1, y1, x2, y2);
      spirograph.draw(line);

      x1 = x2;
      y1 = y2;
    } // Draws the curve using its parametric equations
  }
}
