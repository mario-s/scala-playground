package org.fract

import org.bundle._

case class Type(key: String) {
  
    val bundle = Bundle 
  
    object Bundle extends MessageBundle

    override def toString() = bundle.msg(key)
}
