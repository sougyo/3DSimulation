import com.sun.j3d.utils.universe.SimpleUniverse
import javax.media.j3d.Canvas3D
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior
import javax.media.j3d.BoundingSphere
import javax.vecmath.Point3d
import javax.media.j3d.Node
import javax.media.j3d.BranchGroup
import javax.media.j3d.Group
import javax.media.j3d.PointLight
import javax.vecmath.Point3f
import javax.vecmath.Color3f

class DefaultViewer3d extends Viewer3d {
  private val canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration())
  private val universe = new SimpleUniverse(canvas)
  private val orbit = new OrbitBehavior(canvas, OrbitBehavior.REVERSE_ALL)
  private val scene = new BranchGroup()
  private val light1 = new PointLight(true,
      new Color3f(1.0f, 1.0f, 1.0f),
      new Point3f(2.0f, 2.0f, 2.0f),
      new Point3f(0.8f, 0.0f, 0.0f))  
  private var root:BranchGroup = initRoot()

  universe.getViewingPlatform().setNominalViewingTransform()
  orbit.setSchedulingBounds(new BoundingSphere(new Point3d(0, 0, 0), 100.0))
  universe.getViewingPlatform().setViewPlatformBehavior(orbit)

  scene.setCapability(Group.ALLOW_CHILDREN_WRITE)
  scene.setCapability(Group.ALLOW_CHILDREN_EXTEND)
  scene.addChild(root)

  light1.setInfluencingBounds(new BoundingSphere(new Point3d(), 100.0));
  scene.addChild(light1);

  universe.addBranchGraph(scene);

  private def initRoot():BranchGroup = {
    val root = new BranchGroup()
    root.setCapability(Group.ALLOW_CHILDREN_EXTEND)
    root.setCapability(Group.ALLOW_CHILDREN_WRITE)
    root.setCapability(BranchGroup.ALLOW_DETACH)
    return root
  }
  
  override def addNode(node: Node) {
    if (node == null)
      return
    val b = new BranchGroup()
    b.addChild(node)
    root.addChild(b)
  }
  
  override def addBranchGroup(group: BranchGroup) {
    if (group == null)
      return
    root.addChild(group)
  }
  
  override def clear() {
    root.detach()
    root = initRoot()
    scene.addChild(root)
  }
  
  override def getComponent() = canvas
}