package com.example.securitymember.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SecurityMemberDTO {

    private Long num;

    private String name;

    private String email;

    private String password;

    private String address;
}


