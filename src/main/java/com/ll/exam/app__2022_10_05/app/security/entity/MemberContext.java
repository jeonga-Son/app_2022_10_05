package com.ll.exam.app__2022_10_05.app.security.entity;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.ll.exam.app__2022_10_05.app.member.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@JsonIncludeProperties({"id", "createDate", "modifyDate", "username", "email", "authorities"})
public class MemberContext extends User {

    private final long id;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;
    private final String username;
    private final String email;
    private final Set<GrantedAuthority> authorities;

    public MemberContext(Member member) {
        super(member.getUsername(), "", member.getAuthorities());

        id = member.getId();
        createDate = member.getCreateDate();
        modifyDate = member.getModifyDate();
        username = member.getUsername();
        email = member.getEmail();
        // stream.collect()는 데이터의 중간 처리 후 마지막에 원하는 형태로 반환해주는 역할을 한다.
        // Collectors.toSet()는 Strem의 요소를 Set 인스턴스로 수집하는데 사용할 수 있다.
        authorities = member.getAuthorities().stream().collect(Collectors.toSet());
    }
}
