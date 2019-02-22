package com.example.service1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyResource {

  private static final String SERVICE2HOST = "http://service2";
//  private static final String SERVICE2HOST = "http://localhost:8082";

  @GetMapping("/rest")
  public String resttest(){
    System.out.println("/rest called");
    return "resttest";
  }

  @GetMapping("/callintern")
  public String interncalltest(){
    System.out.println("/callintern called");
    String result = callRest(SERVICE2HOST + "/rest");
    return "interncalltest -> "+ result;
  }

  @GetMapping("/callextern")
  public String callextern(){
    System.out.println("/callextern called");
    String result = callRest("https://web-bedrijfsinfo-service-tst.web.liander.nl/34303072");
    return "callextern -> "+ result;
  }

  @GetMapping("/callsecureextern")
  public String callsecureextern(){
    System.out.println("/callsecureextern called");
    // TODO: find streamserve (of other) call
    String result = callRest("https://web-bedrijfsinfo-service-tst.web.liander.nl/34303072");
    return "callsecureextern -> "+ result;
  }

  @GetMapping("/retry")
  public String retry(){
    System.out.println("/retry called");
    String result = callRest(SERVICE2HOST + "/unstable");
    return "retry -> "+ result;
  }

  @GetMapping("/circuitbreaker/{fail}")
  public String circuitbreaker(@PathVariable long fail){
    System.out.println("/circuitbreaker called");
    String result = callRest(SERVICE2HOST + "/failing/" +fail);
    return "circuitbreaker -> "+ result;
  }

  @GetMapping("/logtrace")
  public String logtrace(){
    System.out.println("/logtrace called");
    String result1 = callRest(SERVICE2HOST + "/delayed/1000");
    String result2 = callRest(SERVICE2HOST + "/delayed/1000");
    return "logtrace -> "+ result1+" "+ result2;
  }

  private String callRest(String url) {
    RestTemplate restTemplate = new RestTemplate();
    System.out.println("calling "+url);
    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
    System.out.println("result status: "+response.getStatusCode());
    return response.getBody();
  }

}
