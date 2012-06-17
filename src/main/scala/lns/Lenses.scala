package lns

/**
 * http://sgektor.blogspot.com/2012/06/scala-lenses.html
 *
 * "Lenses are bidirectional transformations between pairs of connected structures. Asymmetric lenses
 * —where one of those two connected structures is taken to be primary —have been extensively studied.
 * Lenses were first proposed to solve the view- update problem of tree-like data structures by Foster et. al
 * and have also been applied to the construction of a relational database query language."
 *  Tony Morris
 */

case class Weapon( name : String, ammo: Long ){}

case class Soldier( name : String, rank : Int , weapon : Weapon ){}

case class Lens[R,F]( get: R => F, set: (R, F) => R) {}


object Lns {

  val rankL = Lens[Soldier,Int](
    get = _.rank,
    set = (s, r) => s.copy(rank = r)
  )

  val weapL = Lens[Soldier,Weapon](
    get = _.weapon,
    set = (s, w) => s.copy(weapon = w)
  )

  def modify[R,F]( l : Lens[R,F])(f : F => F): R => R = r => l set(r, f(l get r))

}
