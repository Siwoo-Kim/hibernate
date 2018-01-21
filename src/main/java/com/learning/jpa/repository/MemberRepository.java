package com.learning.jpa.repository;


import com.learning.jpa.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long>{

    List<Member> findByName(String name);

    Boolean existsByName(String name);


}
