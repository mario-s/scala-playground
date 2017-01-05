package org.mario

import java.awt.{Dimension, Graphics2D}

import scala.swing._


class FractalComponent(width: Int, height: Int) extends Component {
  require(width != 0)
  require(height != 0)

  def this(dim: Dimension) = this(dim.width, dim.height)

  var painter: FractalPainter = Appleman(width, height)

  def paintFractal(fractalType: String) {
    println("type : " + fractalType)
    painter = typeMatch(fractalType)
    repaint()
  }

  private def typeMatch(fractalType: String): FractalPainter = fractalType match {
    case "Julia" => Julia(width, height)
    case _ => Appleman(width, height)
  }

  override def paint(g: Graphics2D): Unit = {
    painter.paint(g)
  }
}



