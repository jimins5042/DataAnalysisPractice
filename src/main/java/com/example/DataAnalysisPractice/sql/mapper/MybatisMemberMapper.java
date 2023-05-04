package com.example.DataAnalysisPractice.sql.mapper;

import com.example.DataAnalysisPractice.memory.member.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MybatisMemberMapper {
    void save(Member member);
    long getId();
    Member findById(long id);
    List<Member> findAll();
    void update(@Param("memberId") long memberId, @Param("updateParam") Member updateParam);
    void delete(long memberId);
}
