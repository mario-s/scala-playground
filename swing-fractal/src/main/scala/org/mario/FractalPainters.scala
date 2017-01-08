package org.mario

import java.awt.{Color, Dimension, Graphics2D}

/**
  * A common component for fractal graphics
  */
private[mario] abstract class FractalPainter(width: Int, height: Int) {
  require(width != 0)
  require(height != 0)

  def this(dim: Dimension) = this(dim.width, dim.height)

  //graphic color
  val color: Color = new Color(0, 0, 200);

  //a pixel = 0.0065
  val pixel: Double = 0.00625

  //top border
  val topBorder: Double

  //left border
  val leftBorder: Double

  //abstract method
  def isLimit(reZ_minus1: Double, imZ_minus1: Double): Boolean

  def paint(g: Graphics2D) {
    g.setColor(color)

    var im: Double = topBorder
    for (y <- 0 to height) {
      var re: Double = leftBorder
      for (x <- 0 to width) {
        if (isLimit(re, im)) {
          g.drawLine(x, y, x, y)
        }
        re = re + pixel //next column
      }
      im = im + pixel //next row
    }

  }
}

//concrete implementation for a Julia graphic
private[mario] case class Julia(width: Int, height: Int) extends FractalPainter(width: Int, height: Int) with FractalCalculator {
  val lim: Int = 47
  val topBorder: Double = -0.96
  val leftBorder: Double = -1.5

  def isLimit(reZ_minus1: Double, imZ_minus1: Double): Boolean = {
    val reC: Double = -0.65175
    val imC: Double = 0.4185
    check(reZ_minus1, imZ_minus1, reC, imC, 0) == lim
  }
}

//concrete implementation for a Appleman
private[mario] case class Appleman(width: Int, height: Int) extends FractalPainter(width: Int, height: Int) with FractalCalculator {
  val lim: Int = 30
  val topBorder: Double = -1.1
  val leftBorder: Double = -2.1

  def isLimit(reC: Double, imC: Double) =  check(0, 0, reC, imC, 0) == lim

}


