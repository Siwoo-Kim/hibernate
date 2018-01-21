package com.learning.jpa.service;

import com.learning.jpa.domain.Member;
import com.learning.jpa.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public Member join(Member member){
        validateDuplicate(member);
        return this.memberRepository.save(member);
    }

    @Override
    public List<Member> getMembers(){
        return this.memberRepository.findAll();
    }

    @Override
    public Member getMember(Long id){
        return this.memberRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    void validateDuplicate(Member member) {
        if(this.memberRepository.existsByName(member.getName())){
            throw new IllegalStateException("Member name already exists");
        }
    }
}
