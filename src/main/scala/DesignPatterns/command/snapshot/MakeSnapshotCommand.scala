package DesignPatterns.command.snapshot

import DesignPatterns.command.Command
import DesignPatterns.facade.StateOperationHandler
import DesignPatterns.memento.CarProducerSnapshot
import DesignPatterns.singleton.CarProducer


class MakeSnapshotCommand(implicit operationHandler: StateOperationHandler) extends Command {
  private val actualState: CarProducerSnapshot = CarProducer.getInstance().save()

  override def execute(): Unit = {
    operationHandler.saveState(actualState)
  }

  override def undo(): Unit = {
    operationHandler.removeLastState()
  }

  override def redo(): Unit = execute()

  override def getName: String = "Making snapshot of car producer's state"
}
