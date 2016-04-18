import javax.swing.JPanel
import javax.swing.JFrame
import com.sun.j3d.utils.geometry.Primitive
import com.sun.j3d.utils.geometry.Sphere
import javax.vecmath.Point3d
import javax.vecmath.Color3f

object SwingMain {
  def main(args: Array[String]) {
    val frame = new JFrame()
    val viewer:Viewer3d = new DefaultViewer3d()

    frame.getContentPane().add(viewer.getComponent())
    
    frame.setLocation(100, 100)
    frame.setSize(200, 200)
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    frame.setVisible(true)
    
    val task = new PendulumAnimationTimerTask
    viewer.addNode(task.pendulum.getComponent())
    viewer.addNode(task.lineManager1.node.node)
    viewer.addNode(task.lineManager2.node.node)
    viewer.addNode(task.lineManager3.node.node)

    
    val timer = new AnimationTimer(50, 3, task)
    viewer.addNode(timer)
    val color = new Color3f(1.0f, 1.0f, 1.0f)
    viewer.addNode(new TransparentSphere(0.7f, 1f, 50).sphere)
    viewer.addNode((new ConfigurationSpace).rootBranchGroup)
  }
}