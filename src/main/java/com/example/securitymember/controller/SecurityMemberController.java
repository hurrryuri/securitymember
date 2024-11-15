package com.example.securitymember.controller;

import com.example.securitymember.dto.SecurityMemberDTO;
import com.example.securitymember.entity.SecurityMember;
import com.example.securitymember.service.SecurityMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/member")
public class SecurityMemberController {

    private final SecurityMemberService securityMemberService;

    @GetMapping("/signup")
    public String signup(SecurityMemberDTO securityMemberDTO){

        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(SecurityMemberDTO securityMemberDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){

        if(bindingResult.hasErrors()){
            return "member/signup";
        }

        try{
            securityMemberDTO =
                    securityMemberService.saveMember(securityMemberDTO);
        }catch (IllegalStateException e){
            model.addAttribute("msg", e.getMessage());
            return "member/signup";
        }

        redirectAttributes.addFlashAttribute("securityMemberDTO", securityMemberDTO);

        return "redirect:/member/signup";

    }
    @GetMapping("/login")
    public String login(SecurityMemberDTO securityMemberDTO, Principal principal){
        if(principal !=null){

        }
        return "member/login";
    }
}
