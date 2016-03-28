import javax.media.j3d.Transform3D
import javax.media.j3d.TransformGroup
import javax.media.j3d.Node
import javax.vecmath.Vector3d
import com.sun.j3d.utils.geometry.Sphere
import com.sun.j3d.utils.geometry.Primitive

class Pendulum {
  private val (rotationY, rotationY_transformGroup) = newTransform()
  private val (rotationZ, rotationZ_transformGroup) = newTransform()
  private val node = rotationZ_transformGroup

  rotationY_transformGroup.addChild(makeRod())
  rotationY_transformGroup.addChild(makeSphere())
  rotationZ_transformGroup.addChild(rotationY_transformGroup)
  
  private def newTransform(): (Transform3D, TransformGroup) = {
    val transform = new Transform3D()
    val transformGroup = new TransformGroup()
    transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE)
    transformGroup.setTransform(transform)
    (transform, transformGroup)
  }
  
  private def makeRod(): Node = {
    return null
  }
  
  private def makeSphere(): Node = {
    val (translation, transformGroup) = newTransform()
    translation.set(new Vector3d(0.0, 0.0, 1.0))
    transformGroup.addChild(new Sphere(0.07f, Primitive.GENERATE_NORMALS, 50))
    return transformGroup
  }
  
  def getComponent() = node
}