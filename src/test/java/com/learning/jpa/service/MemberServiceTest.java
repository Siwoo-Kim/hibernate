package com.learning.jpa.service;

import com.learning.jpa.domain.Member;
import com.learning.jpa.repository.MemberRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@Log
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void persist(){

        Member member = Member.builder().name("member1").build();

        Member foundMember = memberService.join(member);

        log.warning(foundMember::toString);
        assertThat(member,is(foundMember));
    }

    @Test(expected = IllegalStateException.class)
    public void duplicate_join(){

        Member member1 = Member.builder().name("member1").build();
        Member member2 = Member.builder().name("member1").build();

        memberService.join(member1);
        memberService.join(member2);

        fail("There must be exception");
    }
}
