package ma.craft.trackntrace.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
	@RequestMapping("/")
	public @ResponseBody String greeting() {
		return "Ops is On dev tools";
	}

	public int multiplication(int a, int b) {
		int c= a*b;
		
		return a * b;

	}

	
	public boolean compare(String o) {
		String h = "e";
		int target = -5;
		int num = 3;

		target =- num;  // Noncompliant; target = -3. Is that really what's meant?
		target =+ num; // Noncompliant; target = 3
		return o.equals(h);	
	}
}
