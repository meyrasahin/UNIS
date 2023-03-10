package com.egeuniversity.Tez.Controller;

import com.egeuniversity.Tez.Model.University.University;
import com.egeuniversity.Tez.Model.University.UniversityRequestDto;
import com.egeuniversity.Tez.Service.University.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
@RestController
@RequiredArgsConstructor
@RequestMapping("/university")
public class UniversityController implements Serializable {
    private final UniversityService universityService;

    @PostMapping(path = "/add")
    public ResponseEntity<Void> addUniversity(@RequestBody UniversityRequestDto universityRequestDto) {
        universityService.addUniversity(universityRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/getAll")
    public List<University> getAllUniversity() {
        return universityService.getAllUniversity();
    }

    @DeleteMapping(path= "/delete")
    public ResponseEntity<Void> deleteUniversity(@RequestBody Integer id) {
        universityService.deleteUniversity(id);
        return ResponseEntity.ok().build();
    }
}