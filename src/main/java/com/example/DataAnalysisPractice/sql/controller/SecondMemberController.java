package com.example.DataAnalysisPractice.sql.controller;

import com.example.DataAnalysisPractice.memory.member.Member;
import com.example.DataAnalysisPractice.sql.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("basic/items")
public class SecondMemberController {

    private final MemberService memberService;

    @GetMapping
    public String items(Model model) {
        List<Member> members = memberService.findAll();

        /*
        1. model에 members값을 담아 view로 전달
           이때 json처럼 {key, values}형식으로 전달된다
        2. view에서 key메소드를 이용하여 values, 여기선 members의 값을 이용한다
         */

        model.addAttribute("members", members);
        return "basic/items";
    }

    @GetMapping("/{memberId}")
    public String item(@PathVariable long memberId, Model model) {
        Member member = memberService.findById(memberId);
        model.addAttribute("member", member);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    @PostMapping("/add")
    public String addItemV6(@ModelAttribute Member member, RedirectAttributes redirectAttributes) {
        // 클래스 첫 글자를 소문자로 변환해주는 것이 값에 들어감

        Member savedMember = memberService.save(member);
        log.info("확인용 id : " + savedMember.getId());
        redirectAttributes.addAttribute("memberId", savedMember.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/basic/items/{memberId}";
    }

    @GetMapping("/{memberId}/edit")
    public String editForm(@PathVariable Long memberId, Model model) {

        Member member = memberService.findById(memberId);
        model.addAttribute("member", member);
        return "basic/editForm";
    }

    @PostMapping("/{memberId}/edit")
    public String edit(@PathVariable Long memberId, @ModelAttribute Member item) {
        memberService.update(memberId, item);
        return "redirect:/basic/items/{memberId}";
    }

    @GetMapping("/{memberId}/delete")
    public String deleteItem(@PathVariable Long memberId) {
        memberService.delete(memberId);
        return "redirect:/basic/items";
    }

    //테스트용 데이터
    //@PostConstruct
    public void init() {
        memberService.save(new Member("name1", 1, 17, "01012341234", "대전"));
        memberService.save(new Member("name2", 2, 27, "01056781234", "서울"));
    }

}
