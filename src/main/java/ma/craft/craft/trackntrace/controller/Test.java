package ma.craft.craft.trackntrace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test {
	 @RequestMapping("/")
	   public @ResponseBody String greeting() {
	        return "Ops is On dev tools";
	    }

}
