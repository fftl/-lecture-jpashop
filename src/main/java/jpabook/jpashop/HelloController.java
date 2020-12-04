package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){ //좌측의 Model model 에 데이터를 받아온다(View 에서)
        model.addAttribute("data", "hello!!");
        return "hello";
    }
}
