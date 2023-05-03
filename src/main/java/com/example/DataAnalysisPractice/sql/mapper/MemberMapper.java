package com.example.DataAnalysisPractice.sql.mapper;

import com.example.DataAnalysisPractice.memory.member.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper {
    @Select("now")
    String getTime();

    @Insert("insert into memberinfo (name, phone_number, sex, age, location) values (#{name}, #{phoneNumber}, #{sex},#{age}, #{location})")
    void save(Member member);

    @Select("select * from memberinfo order by id DESC limit 1")
    long getId();


    @Select("select * from memberinfo where id = #{id}")
    Member findById(long id);

    @Select("select * from memberinfo")
    List<Member> findAll();

    @Update("update memberinfo set name=#{updateParam.name}, phone_number=#{updateParam.phoneNumber}, sex=#{updateParam.sex}, age=#{updateParam.age} , location=#{updateParam.location} where id = #{memberId}")
    void update(@Param("memberId") long memberId, @Param("updateParam") Member updateParam);

    @Delete("delete from memberinfo where id = #{memberId}")
    void delete(long memberId);
}
