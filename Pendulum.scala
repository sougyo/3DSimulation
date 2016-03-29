import javax.media.j3d.Transform3D
import javax.media.j3d.TransformGroup
import javax.media.j3d.Node
import javax.vecmath.Vector3d
import com.sun.j3d.utils.geometry.Sphere
import com.sun.j3d.utils.geometry.Primitive
import javax.vecmath.Color3f
import javax.vecmath.Point3d
import java.lang.Double

class Pendulum {
  private val (rotationY, rotationY_transformGroup) = transformPair()
  private val (rotationZ, rotationZ_transformGroup) = transformPair()

  rotationY_transformGroup.addChild(makeRod())
  rotationY_transformGroup.addChild(makeSphere())
  rotationZ_transformGroup.addChild(rotationY_transformGroup)
  
  private def transformPair(): (Transform3D, TransformGroup) = {
    val transform = new Transform3D()
    val transformGroup = new TransformGroup()
    transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE)
    transformGroup.setTransform(transform)
    (transform, transformGroup)
  }
  
  private def makeRod(): Node = {
    val color = new Color3f(1.0f, 1.0f, 1.0f)
    LineFactory.makeLine(Array(
        (new Point3d(0, 0, 0), color),
        (new Point3d(0, 0, 1), color)))
  }
  
  private def makeSphere(): Node = {
    val (translation, transformGroup) = transformPair()
    translation.set(new Vector3d(0.0, 0.0, 1.0))
    transformGroup.addChild(new Sphere(0.07f, Primitive.GENERATE_NORMALS, 50))
    return transformGroup
  }
  
  def setPosition(x: Double, y: Double, z:Double) = {
    rotationY.rotY(Math.acos(z))
    rotationZ.rotZ(Math.atan2(y, x))
    rotationY_transformGroup.setTransform(rotationY)
    rotationZ_transformGroup.setTransform(rotationZ)
  }
  
  def getComponent() = rotationZ_transformGroup
}