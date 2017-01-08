import java.awt.Dimension

import org.mario._

import scala.swing.BorderPanel.Position._
import scala.swing._
import scala.swing.event._


object FractalGUI extends SimpleSwingApplication{
	val dim = new Dimension(600,400)
  
	def top = new MainFrame{
		title = "Fraktale"
		resizable = false


		val select = new ComboBox(List("","Apfel", "Farn", "Julia"))
		val comp = new FractalComponent(dim)

	  contents = new BorderPanel() {
			layout(select) = North
			layout(comp) = Center
		}

		menuBar  = new MenuBar{
			contents += new Menu("Datei") {
				contents += new MenuItem(Action("Beenden") {
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
