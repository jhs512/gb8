package com.gb.domain.member.member.repository;

import com.gb.domain.member.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Object> findByUsername(String username);
}