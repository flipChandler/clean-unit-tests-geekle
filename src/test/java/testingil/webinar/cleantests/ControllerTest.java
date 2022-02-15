package testingil.webinar.cleantests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class ControllerTest {

	MockMvc mvc;
	
	@Test
	void test() throws Exception {
		this.mvc = MockMvcBuilders.standaloneSetup(
				new CalculatorController()).build();
		
		Calculator calculator = new Calculator();
		calculator.setFirst(1);
		calculator.setSecond(2);
		calculator.setOp(Ops.PLUS);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(calculator);

		MvcResult result = mvc.perform(post("/root/calculate")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isOk())
				.andReturn();
		assertThat(result.getResponse().getContentAsString(), is("3"));
	}

}
