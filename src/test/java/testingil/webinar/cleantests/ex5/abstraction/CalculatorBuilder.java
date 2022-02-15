package testingil.webinar.cleantests.ex5.abstraction;

import testingil.webinar.cleantests.Calculator;
import testingil.webinar.cleantests.Ops;

public class CalculatorBuilder {
	
	private int second = 0;
	private int first = 0;
	private Ops op = Ops.PLUS;

	public CalculatorBuilder withFirst(int first) {
		this.first = first;
		return this;
	}
	
	public CalculatorBuilder withSecond(int second) {
		this.second = second;
		return this;
	}
	
	public CalculatorBuilder withOps(Ops op) {
		this.op = op;
		return this;
	}
	
	public Calculator build() {
		Calculator calculator = new Calculator();
		calculator.setFirst(first);
		calculator.setSecond(second);
		calculator.setOp(op);
		return calculator;
	}
}
