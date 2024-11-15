package com.example.securitymember.repository;

import com.example.securitymember.entity.SecurityMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityMemberRepository extends JpaRepository<SecurityMember, Long> {

    public SecurityMember findByEmail (String email);

}

