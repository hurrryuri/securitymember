package com.example.securitymember.service;

import com.example.securitymember.constant.Role;
import com.example.securitymember.dto.SecurityMemberDTO;
import com.example.securitymember.entity.SecurityMember;
import com.example.securitymember.repository.SecurityMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.image.FilteredImageSource;
import java.lang.reflect.Member;
import java.nio.channels.IllegalChannelGroupException;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class SecurityMemberService implements UserDetailsService {

    private final SecurityMemberRepository securityMemberRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public SecurityMemberDTO saveMember(SecurityMemberDTO securityMemberDTO){
        SecurityMember securityMember =
                securityMemberRepository.findByEmail(securityMemberDTO.getEmail());

        if(securityMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }

        securityMember =
                modelMapper.map(securityMemberDTO,SecurityMember.class);

        securityMember.setRole(Role.ADMIN);

        securityMember.setPassword(passwordEncoder.encode(securityMemberDTO.getPassword()));

        securityMember =
                securityMemberRepository.save(securityMember);

        return modelMapper.map(securityMember, SecurityMemberDTO.class);




    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        SecurityMember securityMember =
                this.securityMemberRepository.findByEmail(email);


        return null;
    }
}

