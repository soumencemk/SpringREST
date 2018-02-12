package com.soumen.get;

import com.soumen.model.Employee;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class SimpleGetExample {

    static final String URL_EMPLOYEES = "http://localhost:8080/employees";

    public static void main(String[] args) {
        //simpleGet();
        //GetWithHeader();
        //getPOJO();
        getPOJOWithHeader();
    }

    public static void simpleGet() {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(URL_EMPLOYEES, String.class);
        System.out.println(result);
    }

    public static void GetWithHeader() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.set("my_other_key", "my_other_value");

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(URL_EMPLOYEES, HttpMethod.GET, entity, String.class);
        System.out.println("FULL RESPONSE :" + response.toString());
        System.out.println("RESPONSE BODY :" + response.getBody());
    }

    public static void getPOJO() {
        RestTemplate restTemplate = new RestTemplate();
        Employee[] list = restTemplate.getForObject(URL_EMPLOYEES, Employee[].class);
        if (list != null) {
            for (Employee e : list) {
                System.out.println("Employee: " + e.getEmpNo() + " - " + e.getEmpName());
            }
        }

    }

    public static void getPOJOWithHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.set("my_other_key", "my_other_value");

        HttpEntity<String> entity = new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee[]> response = restTemplate.exchange(URL_EMPLOYEES, HttpMethod.GET, entity, Employee[].class);
        if (response.getStatusCode() == HttpStatus.OK) {

            if (response.getBody() != null) {
                for (Employee e : response.getBody()) {
                    System.out.println("Employee: " + e.getEmpNo() + " - " + e.getEmpName());
                }
            }
        }

    }
}
