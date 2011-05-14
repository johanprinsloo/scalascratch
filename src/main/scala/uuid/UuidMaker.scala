package uuid

import java.util.UUID

class UuidMaker  {
  def getUuidString : String = UUID.randomUUID.toString
  def getUuid : UUID = UUID.randomUUID
}