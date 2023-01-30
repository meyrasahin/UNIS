package com.egeuniversity.Tez.Repository.University;

import com.egeuniversity.Tez.Model.University.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {

    @Query("select u from University as u where u.id=?1")
    University get(Integer id);

}
