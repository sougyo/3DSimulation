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
    
    val timer = new AnimationTimer(10, 3, task)
    viewer.addNode(timer)
    val color = new Color3f(1.0f, 1.0f, 1.0f)
    viewer.addNode(LineFactory.makeLine(Array(
        (new Point3d(0, 0, 0), color),
        (new Point3d(0, 0, 2), color))))
    viewer.addNode(new TransparentSphere(0.7f, 1f, 50).sphere)
    viewer.addNode((new ConfigurationSpace).rootBranchGroup)
  }
}