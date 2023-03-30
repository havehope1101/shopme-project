package com.shopme.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.shopme.common.entity.Customer;

@Controller
public class CustomerController {
	@Autowired private CustomerService service;

	@GetMapping("/register")
	public String showRegisterForm(Model model) {

		model.addAttribute("pageTitle", "Customer Registration");
		model.addAttribute("customer", new Customer());

		return "register/register_form";
	}
	
	@PostMapping("/create_customer")
	public String newCustomer(){
		return "register/register_success";
	}
	
	
}