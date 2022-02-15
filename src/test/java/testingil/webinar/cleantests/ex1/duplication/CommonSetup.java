package testingil.webinar.cleantests.ex1.duplication;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import testingil.webinar.cleantests.Calculator;
import testingil.webinar.cleantests.Ops;

class CommonSetup {

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
	
	// Check that we can add two numbers 
	// and return the right result as a string
	@Test
	void test_add() throws Exception {
		calculator.setFirst(3);
		calculator.setSecond(4);
	    calculator.setOp(Ops.PLUS);
		
	    String json = mapper.writeValueAsString(calculator);
	    HttpEntity<String> request = 
			      new HttpEntity<String>(json, headers);

	    String result = restTemplate.postForObject("http://localhost:8888/root/calculate", 
				request, String.class);

		assertThat(result, is("7"));
	}

	// Check that we can add two numbers 
	// and return the right result as a string
	@Test
	void test_add_minus() throws Exception {
		calculator.setFirst(-5);
		calculator.setSecond(-4);
	    calculator.setOp(Ops.PLUS);
		
	    String json = mapper.writeValueAsString(calculator);
	    HttpEntity<String> request = 
			      new HttpEntity<String>(json, headers);

	    String result = restTemplate.postForObject("http://localhost:8888/root/calculate", 
				request, String.class);

		assertThat(result, is("-9"));
	}

	@Test
	void test_minus() throws Exception {
		calculator.setFirst(20);
		calculator.setSecond(4);
		calculator.setOp(Ops.MINUS);

	    String json = mapper.writeValueAsString(calculator);
	    HttpEntity<String> request = 
			      new HttpEntity<String>(json, headers);

	    String result = restTemplate.postForObject("http://localhost:8888/root/calculate", 
				request, String.class);

		assertThat(result, is("16"));
	}
}
