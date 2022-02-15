package testingil.webinar.cleantests.ex6.abstraction;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import testingil.webinar.cleantests.Calculator;
import testingil.webinar.cleantests.Consts;
import testingil.webinar.cleantests.Ops;

class WithAPICallWrapper {

	private final String LOCAL_URL = "http://localhost:8888";
	private String url;
	private Calculator calculator;
	private CalculatorBuilder calculatorBuilder;

	@BeforeEach
	public void setup() {
		url = LOCAL_URL + Consts.ROOT + Consts.CALCULATE;
	    calculatorBuilder = new CalculatorBuilder();
	}
	
	@Test
	void add_two_numbers_and_calculate_result() throws Exception {
		calculator = calculatorBuilder.withFirst(3)
				.withOps(Ops.PLUS)
				.withSecond(4)
				.build();
		
	    String result = callCalculate();
		assertThat(result, is("7"));
	}


	@Test
	void add_two_negative_numbers_and_calculate_result() throws Exception {
		calculator = calculatorBuilder.withFirst(-5).withSecond(-4).build();
		
	    String result = callCalculate();
		assertThat(result, is("-9"));
		
	}
	
	@Test
	void subtract_two_numbers() throws Exception {
		calculator = calculatorBuilder.withFirst(20)
								.withSecond(4)
								.withOps(Ops.MINUS)
								.build();
		
	    String result = callCalculate();
		assertThat(result, is("16"));
	}

	public String callCalculate() throws Exception {
		APICallWrapper apiCall = new APICallWrapper();
		String result = apiCall.postWithBody(url, calculator);
		return result;
	}
}
