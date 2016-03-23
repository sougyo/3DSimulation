import javafx.application.Application
import javafx.scene.Group
import javafx.scene.PerspectiveCamera
import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.scene.paint.PhongMaterial
import javafx.scene.shape.Sphere
import javafx.scene.transform.Rotate
import javafx.scene.transform.Translate
import javafx.stage.Stage

class MyApplication extends Application {
  override def start(stage: Stage) = {
    val root = new Group()
    val earth = new Sphere(100)
    val camera = new PerspectiveCamera(true)
    val scene = new Scene(root, 800, 600, Color.BLACK)

    root.getChildren().add(earth)
    camera.setFieldOfView(45.0)
    camera.getTransforms().addAll(new Translate(0, 0, -180))
    scene.setCamera(camera)
    stage.setScene(scene)
    stage.setTitle("hello javafx 3d")
    stage.show()
  }
}

object Main {
  def main(args: Array[String]) = {
    Application.launch(classOf[MyApplication], args: _*)
  }
}
