package com.egeuniversity.Tez.Controller;

import com.egeuniversity.Tez.Model.University.University;
import com.egeuniversity.Tez.Model.University.UniversityRequestDto;
import com.egeuniversity.Tez.Service.University.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UniversityController {
    private final UniversityService universityService;

    @PostMapping(path = "/university/add")
    public ResponseEntity<Void> addUniversity(@RequestBody UniversityRequestDto universityRequestDto) {
        universityService.addUniversity(universityRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/university/getAll")
    public List<University> getUniversity() {
        return universityService.getAllUniversity();
    }

    @DeleteMapping(path= "/university/delete")
    public ResponseEntity<Void> deleteUniversity(@RequestBody Integer id) {
        universityService.deleteUniversity(id);
        return ResponseEntity.ok().build();
    }
}