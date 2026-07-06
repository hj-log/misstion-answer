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

    static User setupUser(UserService userService) {
        UserCreateRequest request = new UserCreateRequest("woody", "woody@codeit.com", "woody1234");
        User user = userService.create(request, Optional.empty());
        return user;
    }

    static Channel setupChannel(ChannelService channelService) {
        PublicChannelCreateRequest request = new PublicChannelCreateRequest("공지", "공지 채널입니다.");
        Channel channel = channelService.create(request);
        return channel;
    }

    static void messageCreateTest(MessageService messageService, Channel channel, User author) {
        MessageCreateRequest request = new MessageCreateRequest("안녕하세요.", channel.getId(), author.getId());
        Message message = messageService.create(request, new ArrayList<>());
        System.out.println("메시지 생성: " + message.getId());
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(MisstionAnswerApplication.class, args);
        // 서비스 초기화
        UserService userService = context.getBean(UserService.class);
        ChannelService channelService = context.getBean(ChannelService.class);
        MessageService messageService = context.getBean(MessageService.class);

        // 셋업
        User user = setupUser(userService);
        Channel channel = setupChannel(channelService);
        // 테스트
        messageCreateTest(messageService, channel, user);
    }
}
