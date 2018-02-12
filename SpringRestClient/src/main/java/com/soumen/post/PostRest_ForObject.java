package com.soumen.post;

import com.soumen.get.SimpleGetExample;
import com.soumen.model.Employee;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class PostRest_ForObject {

    static final String URL_CREATE_EMPLOYEE = "http://localhost:8080/employee";

    public static void main(String[] args) {
        postForObject();
        SimpleGetExample.getPOJOWithHeader();
    }

    private static void postForObject() {
        Employee newEmployee = new Employee("E10", "Soumen", "Developer");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Employee> requestBody = new HttpEntity<>(newEmployee, headers);
        Employee employee = restTemplate.postForObject(URL_CREATE_EMPLOYEE, requestBody, Employee.class);
        if (employee != null && employee.getEmpNo() != null) {
            System.out.println("Employee created: " + employee.getEmpNo());
        } else {
            System.out.println("Something error!");
        }


    }
}
