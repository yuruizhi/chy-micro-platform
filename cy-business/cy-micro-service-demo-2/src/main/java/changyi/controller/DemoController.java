package changyi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/demo2")
public class DemoController {

    @RequestMapping("/t1")
    public String home() {
        return "demo2,t1";
    }

}
