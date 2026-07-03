package com.codeit.misstionanswer.service.file;

import com.codeit.misstionanswer.entity.*;
import com.codeit.misstionanswer.service.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

// 데이터베이스(DB) 대신, 컴퓨터의 일반 폴더와 파일(.ser)을 사용해서 사용자(User) 정보를 저장하고, 읽고, 수정하고, 삭제하는 프로그램
public class FileUserService implements UserService {

    // Path는 컴퓨터 안에 있는 폴더나 파일의 위치(주소)를 나타내는 전용
    private final Path DIRECTORY;
    private final String EXTENSION = ".ser";

    public FileUserService() {
        this.DIRECTORY = Paths.get(System.getProperty("user.dir"), "file-data-map", User.class.getSimpleName());
        if (Files.notExists(DIRECTORY)) {
            try {
                Files.createDirectories(DIRECTORY);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Path resolvePath(UUID id) {
        return DIRECTORY.resolve(id.toString() + EXTENSION);
    }


    @Override
    public User create(String username, String email, String password) {
        User user = new User(username, email, password);  // 유저 객체 생성
        Path path = resolvePath(user.getId()); // 저장할 파일 주소 계산
        try (
                // 자바의 객체를 퍼알로 내보내는 작업
                // FileOutPutStream은 단순히 파일로 가는 길만 뚫어주는 역할
                // ObjectOutputStream은 자바 객체를 파일에 적합한 데이터로 예쁘게 포장하는 역할
                FileOutputStream fos = new FileOutputStream(path.toFile());
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(user); // 파일에 유저 저장 (직렬화)
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User find(UUID userId) {
        User userNullable = null;
        Path path = resolvePath(userId); // 찾고 싶은 사람의 파일 주소 확인
        if (Files.exists(path)) { // 진짜 그 파일이 폴더에 존재하니?
            try (
                    FileInputStream fis = new FileInputStream(path.toFile()); // 파일을 자바프로그램으로 가져오는 작업
                    ObjectInputStream ois = new ObjectInputStream(fis)
            ) {
                userNullable = (User) ois.readObject(); // 파일을 읽어서 자바 유저 객체로 변환 (역직렬화)
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        return Optional.ofNullable(userNullable).orElseThrow(() -> new NoSuchElementException("user with id " + userId + " not found "));
    }

    @Override
    public List<User> findAll() {
        try {
            return Files.list(DIRECTORY) // 폴더 안에 있는 모든 파일 목록을 쫙 긁어온다.
                    // 그 중 뒤에 .ser가 붙은 유저 파일만 골라냅니다.
                    .filter(path -> path.toString().endsWith(EXTENSION))
                    .map(path -> { // 파일들을 하나씩 꺼내서 아래 규칙대로 변환합니다.
                        try (
                                FileInputStream fis = new FileInputStream(path.toFile());
                                ObjectInputStream ois = new ObjectInputStream(fis)
                        ) {
                            return (User) ois.readObject(); // 파일을 읽어서 유저 데이터로 변환
                        } catch (IOException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .toList(); // 변환된 유저들을 하나의 예쁜 리스트로 묶어서 반환
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User update(UUID userId, String newUsername, String newEmail, String newPassword) {
        User userNullable = null;
        Path path = resolvePath(userId);
        if (Files.exists(path)) {
            try (
                    FileInputStream fis = new FileInputStream(path.toFile());
                    ObjectInputStream ois = new ObjectInputStream(fis)
            ) {
                userNullable = (User) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        User user = Optional.ofNullable(userNullable).orElseThrow(() -> new NoSuchElementException("user with id " + userId + " not found "));

        // 값 덮여쓰기
        user.update(newUsername, newEmail, newPassword);

        // 수정된 유저를 파일에 다시 저장하기
        try (
                FileOutputStream fos = new FileOutputStream(path.toFile());
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void delete(UUID userId) {
        Path path = resolvePath(userId);
        if (!Files.exists(path)) { // 파일이 존재하지 않으면
            throw new NoSuchElementException("user with id " + userId + " not found ");
        }
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
