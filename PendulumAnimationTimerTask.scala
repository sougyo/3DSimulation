

import javax.vecmath.Color3f
import javax.vecmath.Point3d

class PendulumAnimationTimerTask extends AnimationTimerTask {
  val pendulum = new Pendulum
  val lineManager1 = new RefreshLineManager(1000)
  val lineManager2 = new RefreshLineManager(1000)
  val lineManager3 = new RefreshLineManager(1000)
  val color = new Color3f(1f, 1f, 1f)
  val sol = EmspSolutionFactory.create(SpParam(1d, 1d, 1d), EmParam(0.9, 0.1, 0.01, 0))
  var t = 0d
  def run(): Unit = {
    val c = sol(t)
    pendulum.setPosition(c.getX(), c.getY(), c.z)
    lineManager1.add((new Point3d(c.getX(), c.getY(),  c.z), color))
    lineManager2.add((new Point3d(c.getX(), -1.5    ,  c.z), color))
    lineManager3.add((new Point3d(c.getX(), c.getY(), -1.5), color))
    t += 0.05
  }
  
  def isActive(): Boolean = {
    true
  }
}