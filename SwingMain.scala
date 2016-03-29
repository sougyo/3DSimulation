import javax.swing.JPanel
import javax.swing.JFrame
import com.sun.j3d.utils.geometry.Primitive

object SwingMain {
  def main(args: Array[String]) {
    val frame = new JFrame()
    val viewer:Viewer3d = new DefaultViewer3d()

    frame.getContentPane().add(viewer.getComponent())
    
    frame.setLocation(100, 100)
    frame.setSize(200, 200)
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    frame.setVisible(true)
    
    val pendulum = new Pendulum
    viewer.addNode(pendulum.getComponent())
    
    //pendulum.setPosition(5, 3, 0)
  }
}