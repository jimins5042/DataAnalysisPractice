package com.example.DataAnalysisPractice.Controller;

import com.example.DataAnalysisPractice.member.Member;
import com.example.DataAnalysisPractice.member.MemberRepository;
import jakarta.annotation.PostConstruct;
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
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping
    public String items(Model model) {
        List<Member> members = memberRepository.findAll();

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
        Member member = memberRepository.findById(memberId);
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

        Member savedMember = memberRepository.save(member);
        redirectAttributes.addAttribute("memberId", savedMember.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/basic/items/{memberId}";
    }

    @GetMapping("/{memberId}/edit")
    public String editForm(@PathVariable Long memberId, Model model) {

        Member member = memberRepository.findById(memberId);
        model.addAttribute("member", member);
        return "basic/editForm";
    }

    @PostMapping("/{memberId}/edit")
    public String edit(@PathVariable Long memberId, @ModelAttribute Member item) {
        memberRepository.update(memberId, item);
        return "redirect:/basic/items/{memberId}";
    }

    @GetMapping("/{memberId}/delete")
    public String deleteItem(@PathVariable Long memberId) {
        memberRepository.delete(memberId);
        return "redirect:/basic/items";
    }

    //테스트용 데이터
    @PostConstruct
    public void init() {
        memberRepository.save(new Member("name1", 1, 17, "010-1234-1234", "대전"));
        memberRepository.save(new Member("name2", 2, 27, "010-5678-1234", "서울"));
    }

}
