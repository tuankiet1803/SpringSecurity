package ltweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ltweb.entity.UserInfo;
import ltweb.repository.UserInfoRepository;

@Service
public record UserService(UserInfoRepository repository, PasswordEncoder passwordEncoder) {
	@Autowired
	public String addUser(UserInfo userinfo) {
		userinfo.setPassword(passwordEncoder.encode(userinfo.getPassword()));
		repository.save(userinfo);
		return "Them thanh cong";
	}
}
