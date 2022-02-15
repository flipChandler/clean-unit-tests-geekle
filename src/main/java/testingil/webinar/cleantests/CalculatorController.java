package testingil.webinar.cleantests;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(Consts.ROOT)
public class CalculatorController {

	@PostMapping(Consts.CALCULATE)
    String calculate(@RequestBody Calculator calculator) {
        String result ="";
        
        switch (calculator.getOp()) {
        case PLUS:
        	result = Integer.toString(calculator.getFirst() + calculator.getSecond());
        	break;
        case MINUS:
        	result = Integer.toString(calculator.getFirst() - calculator.getSecond());
        	break;
		default:
			result = "Error";
			break;
        	
        }
		return (result);
    }
}
