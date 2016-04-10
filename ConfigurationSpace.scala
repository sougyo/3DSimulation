

import javax.media.j3d.BranchGroup
import javax.vecmath.Color3f
import javax.vecmath.Point3d

class ConfigurationSpace {
  val rootBranchGroup = new BranchGroup;
  private val color = new Color3f(1.0f, 1.0f, 1.0f)
  
  rootBranchGroup.addChild(makeFloorMesh(-1.5))
  rootBranchGroup.addChild(makeSideMesh(-1.5))
  
  private def makeSideMesh(y: Double) = {
    val group = new BranchGroup
    for (t <- -1.0 to 1.01 by 0.2) {
      group.addChild(LineFactory.makeLine(
          Array((new Point3d(t, y, -1), color),
                (new Point3d(t, y,  1), color))))
      group.addChild(LineFactory.makeLine(
          Array((new Point3d(-1, y, t), color),
                (new Point3d( 1, y, t), color))))
    }
    group
    
  }
  
  private def makeFloorMesh(z: Double) = {
    val group = new BranchGroup
    for (t <- -1.0 to 1.01 by 0.2) {
      group.addChild(LineFactory.makeLine(
          Array((new Point3d(t, -1, z), color),
                (new Point3d(t,  1, z), color))))
      group.addChild(LineFactory.makeLine(
          Array((new Point3d(-1, t, z), color),
                (new Point3d( 1, t, z), color))))
    }
    group
  }
}