package com.codeit.misstionanswer;

import com.codeit.misstionanswer.entity.*;
import com.codeit.misstionanswer.service.*;
import com.codeit.misstionanswer.service.jcf.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class MisstionAnswerApplication {

    static void userCRUDTest(UserService userService) {
        // 생성
        User user = userService.create("woody", "woody@codeit.com", "woody1234");
        System.out.println("유저 생성: " + user.getId());

        // 조회
        User foundUser = userService.find(user.getId());
        System.out.println("유저 단건 조회: " + foundUser.getId());
        List<User> foundUsers = userService.findAll();
        System.out.println("유저 다건 조회: " + foundUsers.size());

        // 수정
        User updateUser = userService.update(user.getId(), null, null, "woody5678");
        System.out.println("유저 수정: " + String.join("/", updateUser.getUsername(), updateUser.getEmail(), updateUser.getPassword()));

        // 삭제
        userService.delete(user.getId());
        List<User> foundUsersAfterDelete = userService.findAll();
        System.out.println("유저 삭제: " + foundUsersAfterDelete.size());
    }

    static void channelCRUDTest(ChannelService channelService) {
        // 생성
        Channel channel = channelService.create(ChannelType.PUBLIC, "공지", "공지 채널입니다.");
        System.out.println("채널 생성: " + channel.getId());

        // 조회
        Channel foundChannel = channelService.find(channel.getId());
        System.out.println("채널 조회: " + foundChannel.getId());
        List<Channel> foundChannels = channelService.findAll();
        System.out.println("전체 채널 조회: " + foundChannels.size());

        // 수정
        Channel channelUpdate = channelService.update(channel.getId(), "공지사항", null);
        System.out.println("채널 수정: " + String.join("/", channelUpdate.getName(), channelUpdate.getDescription()));

        // 삭제
        channelService.delete(channel.getId());
        List<Channel> foundChannelAfterDelete = channelService.findAll();
        System.out.println("채널 삭제: " + foundChannelAfterDelete.size());
    }

    static void messageCURDTest(MessageService messageService) {
        // 생성
        UUID channelId = UUID.randomUUID();
        UUID authorId = UUID.randomUUID();
        Message message = messageService.create("안녕하세요", channelId, authorId);
        System.out.println("메세지 생성: " + message.getId());

        // 조회
        Message foundMessage = messageService.find(message.getId());
        System.out.println("메시지 조회(단건): " + foundMessage.getId());
        List<Message> foundMessages = messageService.findAll();
        System.out.println("메세지 조회(다건): " + foundMessages.size());

        // 수정
        Message updateMessage = messageService.update(message.getId(),"반갑습니다.");
        System.out.println("메세지 수정: " + updateMessage.getContent());

        // 삭제
        messageService.delete(message.getId());
        List<Message> foundMessageAfterDelete = messageService.findAll();
        System.out.println("메세지 삭제: " + foundMessageAfterDelete.size());
    }

    static User setUser(UserService userService) {
        User user = userService.create("woody", "woody@codeit.com", "woody1234");
        return user;
    }

    static Channel setupChannel(ChannelService channelService) {
        Channel channel = channelService.create(ChannelType.PUBLIC, "공지", "공지 채널입니다.");
        return channel;
    }

    static void messageCreateTest(MessageService messageService, Channel channel, User author) {
        Message message = messageService.create("안녕하세요.", channel.getId(), author.getId());
        System.out.println("메시지 생성: " + message.getId());
    }

    public static void main(String[] args) {
        // 서비스 초기화
        UserService userService = new JCFUserService();
        ChannelService channelService = new JCFChannelService();
        MessageService messageService = new JCFMessageService();

        // 테스트
        userCRUDTest(userService);
        channelCRUDTest(channelService);
        messageCURDTest(messageService);
    }
}
