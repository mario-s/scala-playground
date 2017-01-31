import java.awt.Dimension

import org.bundle._
import org.fract._

import scala.swing.BorderPanel.Position._
import scala.swing._
import scala.swing.event._


object FractalGUI extends SimpleSwingApplication with MessageBundle {
  val dim = new Dimension(600, 400)

  def top = new MainFrame {
    title = msg("title")
    resizable = false

    val types = List("", Type("apple"), Type("fern"), Type("julia"))
    val select = new ComboBox(types)
    val comp = new FractalComponent(dim)

    contents = new BorderPanel() {
      layout(select) = North
      layout(comp) = Center
    }

    menuBar = new MenuBar {
      contents += new Menu(msg("file")) {
        contents += new MenuItem(Action(msg("finish")) {
          System.exit(0)
        })
      }
    }

    listenTo(select.selection)
    reactions += {
      case WindowClosing(e) => System.exit(0)
      case SelectionChanged(`select`) => {
        val selected = select.selection.item
        comp.paintFractal(selected)
      }
    }

    size = dim
    centerOnScreen
  }

}
