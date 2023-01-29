package com.egeuniversity.Tez.Repository.University;

import com.egeuniversity.Tez.Model.University.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
    void deleteById(Integer id);

}
