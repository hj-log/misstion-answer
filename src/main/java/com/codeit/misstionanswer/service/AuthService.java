package com.codeit.misstionanswer.service;

import com.codeit.misstionanswer.dto.request.*;
import com.codeit.misstionanswer.entity.*;

public interface AuthService {
    User login(LoginRequest loginRequest);
}
