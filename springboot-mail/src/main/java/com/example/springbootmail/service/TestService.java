package com.example.springbootmail.service;

import com.example.springbootmail.common.ServerResponse;
import com.example.springbootmail.pojo.Mail;

public interface TestService {

    ServerResponse testIdempotence();

    ServerResponse accessLimit();

    ServerResponse send(Mail mail);
}
