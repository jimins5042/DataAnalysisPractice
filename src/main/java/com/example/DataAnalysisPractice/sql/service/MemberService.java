package com.example.DataAnalysisPractice.sql.service;

import com.example.DataAnalysisPractice.memory.member.Member;
import com.example.DataAnalysisPractice.sql.mapper.MemberMapper;
import com.example.DataAnalysisPractice.sql.mapper.MybatisMemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MybatisMemberMapper memberMapper;

    /*
    public void getTime() {
        memberMapper.getTime();
        log.info("현재 시간 = " + memberMapper.getTime());
    }
*/

    public Member save(Member member) {
        Member findMember = member;
        memberMapper.save(member);
        findMember.setId(memberMapper.getId());
        return findMember;
    }

    public Member findById(long id) {
        return memberMapper.findById(id);

    }

    public List<Member> findAll() {
        return memberMapper.findAll();
    }

    public void update(long memberId, Member updateParam) {
        memberMapper.update(memberId, updateParam);
    }

    public void delete(long memberId) {
        memberMapper.delete(memberId);
    }
}
