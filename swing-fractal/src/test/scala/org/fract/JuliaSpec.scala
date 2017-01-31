package org.fract

import org.scalatest._

class JuliaSpec extends FlatSpec {

  "A Julia component" should "have a limit" in {
    val julia = new Julia(400, 300)
    val map = Map(0.11199999999999281 -> -0.9080000000000004,
      0.10549999999999281 -> -0.9015000000000004,
      0.0989999999999928 -> -0.8950000000000005,
      0.10549999999999281 -> -0.8950000000000005)

    map foreach ((tuple) => assert(julia.isLimit(tuple._1, tuple._2)))
  }

}
