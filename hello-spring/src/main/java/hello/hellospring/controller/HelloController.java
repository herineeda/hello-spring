package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello"; //resources 폴더의 hello.html을 찾아서 이 파일을 렌더링하라
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //API방식 ResponseBody 문자 반환
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name; //viewresolver 사용하지 않고 http의 body에 문자 내용을 직접 반환한다.
    }
    
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name")String name){
        Hello hello = new Hello(); //객체를 반환한다. Json 스타일로 바꿔서 변경 가능
        hello.setName(name);
        return hello;
    }
    
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
    }
    
}
