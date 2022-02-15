package testingil.webinar.cleantests.ex4.literals;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import testingil.webinar.cleantests.Calculator;
import testingil.webinar.cleantests.Ops;

class LiteralsAsConsts {

	private static final String LOCAL_URL = 
			"http://localhost:8888/root/calculate";
	private Calculator calculator;
	private HttpHeaders headers;
	private RestTemplate restTemplate;
	private ObjectMapper mapper;

	@BeforeEach
	public void setup() {
		calculator = new Calculator();
		headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    restTemplate = new RestTemplate();
	    mapper = new ObjectMapper();
	}
	
	@Test
	void add_two_numbers_and_calculate_result() throws JsonProcessingException {
		calculator.setFirst(3);
		calculator.setSecond(4);
	    calculator.setOp(Ops.PLUS);
		
	    String result = callCalculate();
		assertThat(result, is("7"));
	}


	@Test
	void add_two_negative_numbers_and_calculate_result() throws Exception {
		calculator.setFirst(-5);
		calculator.setSecond(-4);
	    calculator.setOp(Ops.PLUS);
		
	    String result = callCalculate();
		assertThat(result, is("-9"));
	}

	@Test
	void subtract_two_numbers_and_calculate_result() throws Exception {
		calculator.setFirst(20);
		calculator.setSecond(4);
		calculator.setOp(Ops.MINUS);
		
	    String result = callCalculate();
		assertThat(result, is("16"));
	}
	
	private String callCalculate() throws JsonProcessingException {
		String json = mapper.writeValueAsString(calculator);
		HttpEntity<String> request = new HttpEntity<String>(json, headers);
		
		String result = restTemplate.postForObject(LOCAL_URL, 
				request, String.class);
		return result;
	}
}
