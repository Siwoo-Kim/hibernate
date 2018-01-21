package com.learning.jpa.service;

import com.learning.jpa.domain.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MemberService {

    Member join(Member member);

    @Transactional(readOnly = true)
    List<Member> getMembers();

    @Transactional(readOnly = true)
    Member getMember(Long id);
}
