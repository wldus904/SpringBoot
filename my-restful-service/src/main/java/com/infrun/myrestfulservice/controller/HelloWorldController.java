package com.infrun.myrestfulservice.controller;

import com.infrun.myrestfulservice.bean.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController // = @Controller + @ResponseBody
public class HelloWorldController {
    // GET
    // URI - /hello-world
    // @RequestMapping(method=RequestMethod.GET, path="/hello-world")
    @GetMapping(path="/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path="/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        // Object인 bean을 return
        // @RestController 때문에 자동으로 reponse body로 변환 -> json형태로 변환됨
        return new HelloWorldBean("Hello World!");
    }

    @GetMapping(path="/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello, world, %s", name));
    }
}
