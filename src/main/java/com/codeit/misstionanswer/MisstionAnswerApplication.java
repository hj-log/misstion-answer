package com.codeit.misstionanswer;

import com.codeit.misstionanswer.dto.request.*;
import com.codeit.misstionanswer.entity.*;
import com.codeit.misstionanswer.repository.*;
import com.codeit.misstionanswer.repository.file.*;
import com.codeit.misstionanswer.service.*;
import com.codeit.misstionanswer.service.basic.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.*;

import java.util.*;

@SpringBootApplication
public class MisstionAnswerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MisstionAnswerApplication.class, args);
    }
}
