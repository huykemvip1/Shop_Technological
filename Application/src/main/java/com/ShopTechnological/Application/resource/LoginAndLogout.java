package com.ShopTechnological.Application.resource;


import javax.persistence.Convert;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ShopTechnological.Application.CovertStringtoDate;
import com.ShopTechnological.Application.domain.Account;
import com.ShopTechnological.Application.domain.Role;
import com.ShopTechnological.Application.exception.UsernameRoleAlreadyExist;
import com.ShopTechnological.Application.service.AccountService;
import com.ShopTechnological.Application.service.MemoriesJwtService;
import com.ShopTechnological.Application.service.SaveRole;

import lombok.AllArgsConstructor;
import lombok.Data;


@RestController
@RequestMapping("/ShopTechnological")
public class LoginAndLogout {
	@Autowired
	private MemoriesJwtService memoriesJwtService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private SaveRole saveRole;
	@GetMapping("/login")
	public ResponseEntity<?> login(HttpServletResponse response){
		if (response.getStatus() != 201 && response.getStatus() != 200) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}else {
			return ResponseEntity.ok().build();
		}
	}
	@GetMapping("/logout")
	public ResponseEntity<?> logout(Authentication authentication){
		if (authentication != null) {
			memoriesJwtService.delete(authentication.getName());
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	@PostMapping("/registry")
	@ResponseBody
	public ResponseEntity<Account> registryUser(@RequestBody LoginAndLogout.AccountNoRole accountNoRole) throws UsernameRoleAlreadyExist{
		Account account=new Account();
		account.setUsername(accountNoRole.getUsername());
		account.setPassword(accountNoRole.getPassword());
		account.setPhonenumber(accountNoRole.getPhonenumber());
		account.setEmail(accountNoRole.getEmail());
		account.setBirthday(accountNoRole.getBirthday());
		account.setSex(accountNoRole.getSex());
		account.setAddress(accountNoRole.getAddress());
		account.setFullname(accountNoRole.getFullname());
		Role role=new Role();
		role.setName("user");
		account.getRoles().add(saveRole.getRole(role.getName()));
		accountService.addAccount(account);
		return ResponseEntity.ok().body(account);
	}
	@Data
	static class AccountNoRole {
		private String username;
		private String password;
		private String fullname;
		private String phonenumber;
		private String birthday;
		private String sex;
		private String email;
		private String address;
		
	}	
}

