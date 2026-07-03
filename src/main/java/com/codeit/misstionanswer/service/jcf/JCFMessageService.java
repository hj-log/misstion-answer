package com.codeit.misstionanswer.service.jcf;

import com.codeit.misstionanswer.entity.*;
import com.codeit.misstionanswer.service.*;

import java.util.*;

public class JCFMessageService implements MessageService {

    // [ 메세지 저장 공간 확보 ]
    // Key(UUID)는 고유번호, Value(Message)는 진짜 채팅 내용
    // 아직 사물함 가구가 들어오기 전, 간판만 달아놓은 상태
    private final Map<UUID, Message> data;

    private final ChannelService channelService;
    private final UserService userService;

    // [ 사물함 조립 및 초기화 ]
    // 이 줄이 없으면 텅 빈 공중에 물건을 넣으라는 꼴이 되어 에러가 남
    // 진짜 데이터들을 집어넣을 수 있는 빈 사믈함을 새로 사서 조립
    public JCFMessageService(ChannelService channelService, UserService userService) {
        this.data = new HashMap<>();
        this.channelService = channelService;
        this.userService = userService;
    }

    @Override
    public Message create(String content, UUID channelId, UUID authorId) {
        try {
            this.channelService.find(channelId);
            this.userService.find(authorId);
        } catch (NoSuchElementException e) {
            throw  e;
        }
        // 1. 재료를 모아 새로운 메시지 카드를 생성합니다. (새 메시지 객체 생성)
        Message message = new Message(content, channelId, authorId);
        // 2. 조립해 둔 사물함(data)에 [번호표, 메시지] 짝꿍으로 쏙 집어넣습니다.
        // 생성된 메시지를 고유 ID와 함께 인메모리 맵에 저장
        this.data.put(message.getId(), message);
        // 3. 화면에 띄울 수 있도록 만든 메시지를 돌려줍니다.
        return message;
    }

    @Override
    public Message find(UUID messageId) {
        // 1. 사물함(data)에서 번호표(messageId)를 주고 메시지를 꺼내옵니다. (없으면 null이 나옴)
        Message messageNullable = this.data.get(messageId);

        // 2. 안전 봉투(Optional)에 담아서 null 체크를 합니다.
        return Optional.ofNullable(messageNullable)
                // 3. 값이 있으면 메시지를 반환하고, 없으면(null이면) 예쁜 안내 문구와 함께 에러를 던집니다.
                .orElseThrow(() -> new NoSuchElementException("message with id " + messageId + " not found "));
    }

    @Override
    public List<Message> findAll() {
        // 1. values(): 사물함에서 번호표는 빼고 진짜 메시지들만 다 모아서
        // 2. stream(): 한 줄로 쪼르르 흐르게 만든 다음
        // 3. toList(): 예쁜 리스트(기차)로 포장해서 한 번에 반환합니다.
        return this.data.values().stream().toList();
    }

    @Override
    public Message update(UUID messageId, String newContent) {
        // 1단계: 번호표(messageId)를 가지고 사물함에서 메시지를 꺼내봅니다.
        Message messageNullable = this.data.get(messageId);

        // 2단계: [검사] 꺼낸 메시지가 null(빈손)인지 아닌지 확인합니다.
        Message message = Optional.ofNullable(messageNullable)
                // 만약 사물함이 비어있었다면(null이었다면) "없어요!" 하고 에러를 던집니다.
                .orElseThrow(() -> new NoSuchElementException("message with id " + messageId + " not found "));

        // 3단계: [진짜 중요] 에러 안 나고 통과했다면, '진짜 메시지'의 내용을 새 내용으로 바꿉니다.
        message.update(newContent);
        return message;
    }

    @Override
    public void delete(UUID messageId) {
        if(!this.data.containsKey(messageId)) {
            throw new NoSuchElementException("message with id " + messageId + " not found ");
        }
        this.data.remove(messageId);
    }
}
