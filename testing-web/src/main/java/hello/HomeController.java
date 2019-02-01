package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @RequestMapping("/")
    // @RequestMapping maps all HTTP operations by default, GET, PUT, ...
    public @ResponseBody
    String greetings() {
        return "Hello World";
    }
}