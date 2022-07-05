package spirograph

import cats.effect.unsafe.implicits.global
import doodle.core._
import doodle.image._
import doodle.image.syntax.all._
import doodle.java2d._
import doodle.syntax.all._

object Spirograph extends App {

  /** Create a circle with the given radius */
  def circle(r: Double): Angle => Vec =
    angle => Vec(r, angle)

  /** Multiply the angle by the given speed, which determines how rapidly the
    * curve rotates.
    */
  def speed(speed: Double): Angle => Angle =
    angle => angle * speed

  /** Shift the phase---the angle---by the given amount.
    */
  def phase(p: Angle): Angle => Angle =
    angle => p - angle

  /** Reverse the rolling direction of the curve. */
  val reverse: Angle => Angle = angle => -angle

  /** Add together two curves, creating a new curve */
  def add(c1: Angle => Vec, c2: Angle => Vec): Angle => Vec =
    angle => c1(angle) + c2(angle)

  /** Make an Image from a curve. */
  def makeImage(
      curve: Angle => Vec,
      start: Angle = 0.degrees,
      stop: Angle = 720.degrees,
      steps: Int = 1000
  ): Image = {
    val increment = (stop - start) / steps.toDouble
    def loop(step: Int): List[Point] =
      if (step == 0) Nil
      else curve(increment * step + start).toPoint :: loop(step - 1)

    Image.interpolatingSpline(loop(steps))
  }

  // An example curve
  val curve = add(
    speed(1.0).andThen(circle(200)),
    speed(40.0).andThen(circle(-100))
  )

  val picture =
    makeImage(curve, stop = 60.degrees, steps = 100)
      .strokeColor(Color.magenta)
      .on(
        makeImage(curve, start = 60.degrees, stop = 120.degrees, steps = 100)
          .strokeColor(Color.cyan)
      )
      .on(
        makeImage(curve, start = 120.degrees, stop = 180.degrees, steps = 100)
          .strokeColor(Color.cornflowerBlue)
      )
      .on(
        makeImage(curve, start = 180.degrees, stop = 240.degrees, steps = 100)
          .strokeColor(Color.deepPink)
      )
      .on(
        makeImage(curve, start = 240.degrees, stop = 300.degrees, steps = 100)
          .strokeColor(Color.violet)
      )
      .on(
        makeImage(curve, start = 300.degrees, stop = 360.degrees, steps = 100)
          .strokeColor(Color.deepSkyBlue)
      )
      .strokeWidth(3.0)

  import doodle.effect.Writer._
  picture.draw()
  picture.write[Png]("curve.png")
}
