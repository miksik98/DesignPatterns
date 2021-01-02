package DesignPatterns.command.snapshot

import DesignPatterns.command.Command
import DesignPatterns.facade.StateOperationHandler
import DesignPatterns.memento.CarProducerSnapshot
import DesignPatterns.singleton.CarProducer

class RestoreSnapshotCommand(implicit operationHandler: StateOperationHandler) extends Command {
  private val actualState: CarProducerSnapshot = CarProducer.getInstance().save()

  override def execute(): Unit = {
    operationHandler.restoreState()
  }

  override def undo(): Unit = {
    operationHandler.saveState()
    actualState.restore()
  }

  override def redo(): Unit = execute()

  override def getName: String = "Restoring last saved car producer's snapshot"
}
