package ltweb.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ltweb.entity.Customer;
import ltweb.entity.UserInfo;
import ltweb.service.UserService;



@RestController
@EnableMethodSecurity
@RequestMapping("/user")
@RequiredArgsConstructor
public class CustomerController {
	final private UserService userService;
	final private List<Customer> customers = List.of(Customer.builder().id("001").name("Kiet").email("kiet@gmail.com").build(),
			Customer.builder().id("002").name("Sang").email("sang@gmail.com").build());
	@GetMapping("/new")
	public String addUser(@RequestBody UserInfo userInfo) {
		return userService.addUser(userInfo);
	}
	@GetMapping("/hello")
	public ResponseEntity<String> hello(){
		return ResponseEntity.ok("hello is Guest");
	}
	@GetMapping("/customer/all")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<Customer>> getCustomerList(){
		List<Customer> list = this.customers;
		return ResponseEntity.ok(list);
	}
	@GetMapping("/customer/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Customer> getCustomerList(@PathVariable("id") String id) {
		List<Customer> customers = this.customers.stream().filter(customer -> customer.getId().equals(id)).toList();
		return ResponseEntity.ok(customers.get(0));
	}
	
}
