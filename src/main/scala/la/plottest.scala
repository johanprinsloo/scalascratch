package la

import scalala.tensor.dense._
import scalala.library.Plotting._

object plottest {

  val x = DenseVector.range(0, 100) / 100.0;
  plot.hold = true
  plot(x, x :^ 2)
  plot(x, x :^ 3, '.')
  xlabel("x axis")
  ylabel("y axis")
  saveas("lines.png")

}