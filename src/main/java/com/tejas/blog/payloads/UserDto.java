package com.tejas.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.tejas.blog.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min =4,message="Usename must be minimum of 4 charcters")
	private String username;
	
	@Email(message="Email is not valid format")
	private String email;
	
	@NotEmpty
	@Size(min=3 , max =10,message="Password must be min 3 char and max of 10 char.")
//	@Pattern(regexp="")
	private String password;
	
	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();
}
