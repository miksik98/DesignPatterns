package DesignPatterns.command

trait Command {
  def execute(): Unit
  def undo(): Unit
  def redo(): Unit
  def getName: String
}
