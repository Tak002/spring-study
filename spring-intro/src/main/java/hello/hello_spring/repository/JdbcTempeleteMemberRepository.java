package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JdbcTempeleteMemberRepository implements MemberRepository {
    private final JdbcTemplate jdbcTemplate;

//    @Autowired
    //spring에서 bean객체의 생성자가 1개만 존재한다면, autowired를 생략해도 된다.
    //이 클래스는 springconfig에서 bean객체로 등록되므로 bean객체가 맞다.
    public JdbcTempeleteMemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        return null;
    }

    @Override
    public Optional<Member> findById(long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ? ", memberWrapper());
        return result.stream().findAny();

    }

    public RowMapper<Member> memberWrapper() {
//        return new RowMapper<Member>() {
//            @Override
//            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Member member = new Member();
//                member.setId(rs.getLong("id"));
//                member.setName(rs.getString("name"));
//                return member;
//            }
//        };
        // RowMapper는 FunctionalInterface임
        // functional Interface는 오직 하나의 메서드만 리턴함
        // 자바에서는 함수형 인터페이스 타입에 람다식(또는 메서드 참조)을 대입하면,
        // 그 람다식이 해당 인터페이스의 '유일한 추상 메서드' 구현으로 자동으로 연결됩니다.

        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }
    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return List.of();
    }
}
