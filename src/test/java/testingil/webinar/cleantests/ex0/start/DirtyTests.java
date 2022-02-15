package testingil.webinar.cleantests.ex0.start;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import testingil.webinar.cleantests.Calculator;
import testingil.webinar.cleantests.Ops;


class DirtyTests {

	// Check that we can add two numbers 
	// and return the right result as a string
	@Test
	void test_add() throws Exception {
		Calculator calculator =new Calculator();
		calculator.setFirst(3);
		calculator.setSecond(4);
		calculator.setOp(Ops.PLUS);
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(APPLICATION_JSON);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(calculator);
		
	    HttpEntity<String> request = 
			      new HttpEntity<String>(json, headers);
			    
		RestTemplate restTemplate  = new RestTemplate();
		String result = restTemplate.postForObject("http://localhost:8888/root/calculate", 
				request, String.class);

		assertThat(result, is("7"));
	}

	// Check that we can add two numbers 
	// and return the right result as a string
	@Test
	void test_add_minus() throws Exception {
		Calculator calculator = new Calculator();
		
		calculator.setFirst(-5);
		calculator.setSecond(-4);
		calculator.setOp(Ops.PLUS);
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(APPLICATION_JSON);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(calculator);

		HttpEntity<String> request = 
			      new HttpEntity<String>(json, headers);
			    
		RestTemplate restTemplate  = new RestTemplate();
		String result = restTemplate.postForObject("http://localhost:8888/root/calculate", 
				request, String.class);

		assertThat(result, is("-9"));
		
	}

	// subtract numbers
	@Test
	void test_minus() throws Exception {
		Calculator calculator =new Calculator();

		calculator.setFirst(20);
		calculator.setSecond(4);
		calculator.setOp(Ops.MINUS);

		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(APPLICATION_JSON);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(calculator);

		HttpEntity<String> request = 
			      new HttpEntity<String>(json, headers);

		RestTemplate restTemplate  = new RestTemplate();
	    String result = restTemplate.postForObject("http://localhost:8888/root/calculate", 
				request, String.class);

		assertThat(result, is("16"));
	}
}
