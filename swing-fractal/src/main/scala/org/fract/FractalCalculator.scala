package org.fract

/**
  * Trait to calculate the x and y coordinates
  */
private[fract] trait FractalCalculator {
  val lim: Int //limit for a recursion

  def check(reZ_minus1: Double, imZ_minus1: Double, reC: Double, imC: Double, i: Int): Int = {
    val x_n_1: Double = nextX(reZ_minus1, imZ_minus1, reC)
    val y_n_1: Double = nextY(reZ_minus1, imZ_minus1, imC)
    if (isMax(x_n_1, y_n_1)) {
      i
    } else if (i < lim) {
      check(x_n_1, y_n_1, reC, imC, i + 1)
    } else {
      i
    }
  }

  /**
    * the next x coordinate
    */
  def nextX(x_n: Double, y_n: Double, x: Double): Double =
    x_n * x_n - y_n * y_n + x

  /**
    * the next y coordinate
    */
  def nextY(x_n: Double, y_n: Double, y: Double): Double =
    2 * x_n * y_n + y

  /**
    * Returns true if the square product is greater than max
    */
  def isMax(reZ: Double, imZ: Double): Boolean =
    reZ * reZ + imZ * imZ > 4
}
