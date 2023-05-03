package com.example.DataAnalysisPractice.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
//@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private static final Map<Long, Member> store = new HashMap<>();

    private static long sequence = 0L;
    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void update(long memberId, Member updateParam){
        Member findMember = findById(memberId);

        findMember.setName(updateParam.getName());
        findMember.setSex(updateParam.getSex());
        findMember.setPhoneNumber(updateParam.getPhoneNumber());
        findMember.setAge(updateParam.getAge());
        findMember.setLocation(updateParam.getLocation());
    }
    public void delete(long memberId){
        store.remove(memberId);
    }

}
