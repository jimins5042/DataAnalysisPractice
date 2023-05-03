package com.example.DataAnalysisPractice.memory.Controller;

import com.example.DataAnalysisPractice.sql.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
//@RestController
public class DbController {
    private final MemberService memberService;

    @GetMapping("/test")
    public String  test(){
        memberService.getTime();
        return "/index";
    }

}
