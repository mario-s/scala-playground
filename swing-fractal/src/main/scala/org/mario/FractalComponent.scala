package org.mario

import java.awt.{Dimension, Graphics2D}

import scala.swing._


class FractalComponent(width: Int, height: Int) extends Component {
  require(width != 0)
  require(height != 0)

  def this(dim: Dimension) = this(dim.width, dim.height)

  var painter = None: Option[FractalPainter]

  def paintFractal(fractalType: String) {
    println("type : " + fractalType)
    painter = typeMatch(fractalType)
    repaint()
  }

  private def typeMatch(fractalType: String): Option[FractalPainter] = fractalType match {
    case "Apfel" => Some(Appleman(width, height))
    case "Julia" => Some(Julia(width, height))
    case _ => None
  }

  override def paint(g: Graphics2D): Unit = {
    painter match {
      case Some(painter) => painter.paint(g)
      case _ => println("nothing to show here")
    }
  }
}



