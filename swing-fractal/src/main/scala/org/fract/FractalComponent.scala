package org.fract

import java.awt.{Dimension, Graphics2D}

import com.typesafe.scalalogging.Logger
import scala.swing._


class FractalComponent(width: Int, height: Int) extends Component {
  require(width != 0)
  require(height != 0)

  val logger = Logger(classOf[FractalComponent])

  def this(dim: Dimension) = this(dim.width, dim.height)

  var painter = None: Option[FractalPainter]

  def paintFractal(fractalType: Any) {
    logger.debug(s"type: ${fractalType}")
    painter = typeMatch(fractalType)
    repaint()
  }
  

  private def typeMatch(fractalType: Any): Option[FractalPainter] = fractalType match {
    case Type("apple") => Some(new Appleman(width, height))
    case Type("fern") => Some(new Fern(width, height))
    case Type("julia") => Some(new Julia(width, height))
    case _ => None
  }

  override def paint(g: Graphics2D): Unit = {
    painter match {
      case Some(painter) => painter.paint(g)
      case _ => logger.debug("nothing to show here")
    }
  }
}



