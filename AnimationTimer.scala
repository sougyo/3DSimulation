import javax.media.j3d.Behavior
import javax.media.j3d.BoundingSphere
import javax.media.j3d.WakeupOnElapsedTime
import javax.vecmath.Point3d
import javax.media.j3d.WakeupCondition

class AnimationTimer(sleep: Int, taskPerFrame: Int, task: AnimationTimerTask) extends Behavior {
  val wup = new WakeupOnElapsedTime(sleep)
  setSchedulingBounds(new BoundingSphere(new Point3d(), 100.0))
  
  override def initialize(): Unit = {
    wakeupOn(wup);
  }
  
  override def processStimulus(arg0: java.util.Enumeration[_]): Unit = {
    for (i <- 0 to taskPerFrame)
      task.run()
    if (task.isActive())
      wakeupOn(wup)
  }
}