package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        /**
         * 컨트롤러에서 리턴 값 문자를 viewResolver가 화면을 찾아서 처리한다.
         * 스프링 부트 템플릿 엔진 기본 viewName매핑
         * resources:templates/ + {ViewName} +.html
         */
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) { // required = true (default)
        model.addAttribute("name", name);
        return "hello-template";
    }

    /**
     * @ResponseBody를 사용하면 viewResolver를 사용하지 않는다.
     * HTTP의 BODY에 문자 내용을 직접 반환함.
     */
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }


    /**
     * @ResponseBody를 사용하고, 객채를 반환하면 객체가 JSON으로 변환된다.
     */
    @GetMapping("hello-api")
    @ResponseBody
    public  Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
