package org.mario;

import junit.framework._
import Assert._


object ComponentsTest {
    def suite: Test = {
        val suite = new TestSuite(classOf[ComponentsTest])
        suite
    }

    def main(args : Array[String]) {
        junit.textui.TestRunner.run(suite)
    }
}

/**
 * Unit test for simple App.
 */
class ComponentsTest extends TestCase("app") {

    
    
    def test47(): Unit = {
      val c: FractalPainter = new Julia(400,300)
      val map47 = Map(0.11199999999999281 -> -0.9080000000000004,
      	0.10549999999999281 -> -0.9015000000000004,
      	0.0989999999999928 -> -0.8950000000000005,
      	0.10549999999999281 -> -0.8950000000000005)
      
      map47 foreach ((m) => assertTrue(c.isLimit(m._1, m._2)))
    }
    

}
