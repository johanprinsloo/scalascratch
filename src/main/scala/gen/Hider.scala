package org.scratch.gen

class Hider {
  private[this] var xHidden: Int = 0
  def x = xHidden
  private def x_=(x0: Int) { xHidden = x0 }
}