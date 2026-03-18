package com.sacristan.api.OLDinterfaces.student.controllers;


import com.sacristan.api.global.models.user.User;
import com.sacristan.api.interfaces.student.dtos.sequence.PrincipalSequenceDTO;
import com.sacristan.api.interfaces.student.services.routine.StudentRoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student/routines")
public class StudentRoutineController {

    private final StudentRoutineService studentRoutineService;

    @GetMapping()
    public Page<PrincipalSequenceDTO> getPendingSequences(Pageable pageable, @AuthenticationPrincipal User user) {
        return studentRoutineService.getPendingSequences(pageable, user);
    };


}
