package com.sunbeam.service;

import com.sunbeam.dto.SignInRequest;
import com.sunbeam.dto.SignInResponse;

public interface UserService {
//sign in
	SignInResponse authenticateUser(SignInRequest request);

}
