package DesignPatterns.composite

class SubContractorBuilder {
  private var components: Option[Seq[ProductionFlowComponent]] = None

  def withComponents(components: Seq[ProductionFlowComponent]): Unit = {
    this.components = Some(components)
  }

  def getResult: SubContractor = new SubContractor(components.get)
}
