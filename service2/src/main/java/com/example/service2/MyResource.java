package com.example.service2;

import java.util.Random;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyResource {
  Random random = new Random();

  @GetMapping("/rest")
  public String resttest(){
    System.out.println("/rest called");
    return "resttest service2";
  }

  @GetMapping("/unstable")
  public String interncalltest(){
    System.out.println("/unstable called");
    boolean failing = random.nextInt()%4!=0;
    if (failing) throw new RuntimeException("Failure");
    return "unstable";
  }

  @GetMapping("/failing/{fail}")
  public String failing(@PathVariable long fail){
    System.out.println("/failing called");
    boolean failing = fail==1;
    if (failing) throw new RuntimeException("Failure");
    return "failing";
  }

  @GetMapping("/delayed/{delay}")
  public String delayed(@PathVariable long delay) throws InterruptedException {
    System.out.println("/delayed called");
    Thread.sleep(delay);
    return "delayed";
  }





}
