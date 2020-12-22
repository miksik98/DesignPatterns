package DesignPatterns.composite

import DesignPatterns.factory.Generators.Generator
import DesignPatterns.factory.Generators._

object SubContractorGenerator extends Generator[SubContractor] {
  private var iterations: Int = 0

  override def generate(): SubContractor = {
    iterations = iterations + 1
    val levels = rand.nextInt(3)

    val builder = new SubContractorBuilder

    val subContractors = Seq.fill(levels)(generate())
    if (subContractors.isEmpty || iterations > 100) {
      builder.withComponents(Seq(FinalProduct(CarGenerator.generate())))
      builder.getResult
    } else {
      builder.withComponents(subContractors)
      builder.getResult
    }
  }

  def main(args: Array[String]): Unit = {
    val subContractor = generate()
    subContractor.printTree()
  }
}
