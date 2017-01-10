package org.mario

import java.awt.{Color, Dimension, Graphics2D}

import scala.util.Random

/**
  * A common component for fractal graphics
  */
private[mario] abstract class FractalPainter(width: Int, height: Int) {
  require(width != 0)
  require(height != 0)

  def this(dim: Dimension) = this(dim.width, dim.height)

  //graphic color
  val color: Color

  def doPaint(g: Graphics2D)

  def paint(g: Graphics2D) {
    g.setColor(color)
    doPaint(g)
  }
}

private[mario] abstract class LimitFractalPainter(width: Int, height: Int) extends FractalPainter(width: Int, height: Int) {
  val color: Color = new Color(0, 0, 200);

  //a pixel = 0.0065
  val pixel: Double = 0.00625

  //top border
  val topBorder: Double

  //left border
  val leftBorder: Double

  //abstract method
  def isLimit(reZ_minus1: Double, imZ_minus1: Double): Boolean

  def doPaint(g: Graphics2D) {

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
private[mario] class Julia(width: Int, height: Int) extends LimitFractalPainter(width: Int, height: Int) with FractalCalculator {
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
private[mario] class Appleman(width: Int, height: Int) extends LimitFractalPainter(width: Int, height: Int) with FractalCalculator {
  val lim: Int = 30
  val topBorder: Double = -1.1
  val leftBorder: Double = -2.1

  def isLimit(reC: Double, imC: Double) =  check(0, 0, reC, imC, 0) == lim

}

private[mario] class Fern(width: Int, height: Int) extends FractalPainter(width: Int, height: Int) {

  val color: Color = new Color(0, 255, 0);

  val transformFunction = TransformFunction

  def doPaint(g: Graphics2D) = {
    g.drawLine(height / 2, width / 2, height / 2, width / 2)
    drawFern((0, 1), 20000, g)
  }

  private def rnd = Random.nextInt(100)

  private def drawFern(p: (Double, Double), max: Int, g: Graphics2D) {
    paintPoint(p, g)
    if (max != 0)
      drawFern(transformFunction(p._1, p._2), max - 1, g)
  }

  private def paintPoint(p: (Double, Double), g: Graphics2D) = {
    val scale = height / 11
    val y = (height - 25) - (scale * p._2)
    val x = (width / 2) + (scale * p._1)
    g.drawLine(x.toInt, y.toInt, x.toInt, y.toInt)
  }

  object TransformFunction extends ((Double, Double) => (Double, Double)) {

    def transformPoint(p: (Double, Double), a: Double, b: Double, c: Double, d: Double, s: Double): (Double, Double) =
      ((a * p._1) + (b * p._2), ((c * p._1) + (d * p._2) + s))

    def apply(x: Double, y: Double) = {
      rnd match {
        case n if n <= 1 => transformPoint((x, y), 0.0, 0.0, 0.0, 0.16, 0.0)
        case n if n <= 7 => transformPoint((x, y), 0.2, -0.26, 0.23, 0.22, 1.6)
        case n if n <= 14 => transformPoint((x, y), -0.15, 0.28, 0.26, 0.24, 0.44)
        case n if n <= 100 => transformPoint((x, y), 0.85, 0.04, -0.04, 0.85, 1.6)
      }
    }
  }
}


