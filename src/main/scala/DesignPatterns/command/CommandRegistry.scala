package DesignPatterns.command

import scala.collection.mutable

object CommandRegistry {

  private val commandStack: mutable.Stack[Command] = mutable.Stack.empty

  private val undoCommandStack: mutable.Stack[Command] = mutable.Stack.empty


  def executeCommand(command: Command): Unit = {
    command.execute()
    System.out.println(command.getName)
    commandStack.push(command);
    undoCommandStack.clear();
  }

  def redo(): Unit = {
    if (undoCommandStack.nonEmpty) {
      val lastCommand = undoCommandStack.pop()
      lastCommand.redo()
      System.out.println("Redo command: \"" + lastCommand.getName + "\"")
      commandStack.push(lastCommand)
    }
  }

  def undo(): Unit = {
    if (commandStack.nonEmpty) {
      val lastCommand = commandStack.pop()
      lastCommand.undo()
      System.out.println("Undo command: \"" + lastCommand.getName + "\"")
      undoCommandStack.push(lastCommand)
    }
  }
}
