package ltweb.entity;

import lombok.Data;

@Data
public class LoginDto {
	private String userOrEmail;
	private String password;
}
