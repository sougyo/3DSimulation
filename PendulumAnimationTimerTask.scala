

class PendulumAnimationTimerTask extends AnimationTimerTask {
  val pendulum = new Pendulum()
  val sol = EmspSolutionFactory.create(SpParam(1d, 1d, 1d), EmParam(2.01, 0.001, 0.01, 0))
  var t = 0d
  def run(): Unit = {
    val c = sol(t)
    pendulum.setPosition(c.getX(), c.getY(), c.z)
    t += 0.01
  }
  
  def isActive(): Boolean = {
    true
  }
}