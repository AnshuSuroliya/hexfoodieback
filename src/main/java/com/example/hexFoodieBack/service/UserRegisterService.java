package com.example.hexFoodieBack.service;

import com.example.hexFoodieBack.exception.UserException;
import com.example.hexFoodieBack.exception.ValidationException;
import com.example.hexFoodieBack.request.LoginRequest;
import com.example.hexFoodieBack.request.SignupRequest;
import com.example.hexFoodieBack.response.LoginResponse;
import com.example.hexFoodieBack.response.SignupResponse;

public interface UserRegisterService {
    public SignupResponse signup(SignupRequest signupRequest) throws ValidationException;

    public LoginResponse login(LoginRequest loginRequest) throws UserException;
}