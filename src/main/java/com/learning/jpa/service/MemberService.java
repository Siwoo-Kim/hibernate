package com.learning.jpa.service;

import com.learning.jpa.domain.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MemberService {

    Member join(Member member);

    List<Member> getMembers();

    Member getMember(Long id);
}
