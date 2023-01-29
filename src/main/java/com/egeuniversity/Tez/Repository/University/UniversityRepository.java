package com.egeuniversity.Tez.Repository.University;

import com.egeuniversity.Tez.Model.University.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {


}
