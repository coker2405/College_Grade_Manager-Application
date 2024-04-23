package com.coker.springboot.repository;

import com.coker.springboot.dto.StaticAVG;
import com.coker.springboot.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepo extends JpaRepository<Grade,Integer> {
    @Query("SELECT u FROM Grade u WHERE u.student.user.Id = :sid")
    List<Grade> searchByUserid(@Param("sid") int sid);
    @Query("SELECT u FROM Grade u WHERE u.courses.Id = :cid")
    List<Grade> searchByCourseId(@Param("cid") int cid);

    @Query("SELECT new com.coker.springboot.dto.StaticAVG(" +
            "c.id, c.name, AVG(g.score))" +
            "FROM Grade g JOIN g.courses c GROUP BY c.id, c.name")
    List<StaticAVG> staticAVG();


//    @Query("SELECT AVG(s.score) AS score,u.id as id, u.name as name FROM " +
//            "Score s JOIN s.student st JOIN st.user u " +
//            "GROUP BY u.id ORDER BY score DESC")
//    public Page<IAVGScoreDTO> statistic1(Pageable pageable);

}
