package log

/**
 *  Using http://software.clapper.org/grizzled-slf4j
 *  Deps:
 *    val logback = "ch.qos.logback" % "logback-classic" % "0.9.28"
 *    val grizzled = "org.clapper" %% "grizzled-slf4j" % "0.5"
 *
 *    config helper http://wizardforge.org/pc?action=displayFlowchartVersionPublic&id=42
 */

import grizzled.slf4j._


class grizzledlog {
  val logger = Logger("log.grizzledlog")
  def one = {
     logger.info("one")
  }

  def two = {
     logger.info("two")
  }

  def three = {
     logger.info("three")
  }

  def err = {
    logger.error("error")
  }
}

class grizzledlogT extends Logging {

  def one = {
     info("one")
  }

  def two = {
     info("two")
  }

  def three = {
     info("three")
  }

  def err = {
    error("error")
  }
}