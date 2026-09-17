package com.nutricare.user.controller;

import com.nutricare.user.dto.*;
import com.nutricare.user.exception.InvalidUserInputException;
import com.nutricare.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO,
                                   BindingResult result,
                                   HttpSession session) {
        UserInfoResponseDTO user = (UserInfoResponseDTO) session.getAttribute("userInfo");
        if (user != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO("409", "User is authenticated"));
        }

        if (result.hasErrors()) {
            throw new InvalidUserInputException(result.getFieldError().getDefaultMessage());
        }

        UserInfoResponseDTO userInfoResponseDTO = userService.login(loginRequestDTO);

        session.setAttribute("userInfo", userInfoResponseDTO);
        return ResponseEntity.ok(userInfoResponseDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO,
                                      BindingResult result,
                                      HttpSession session) {
        UserInfoResponseDTO user = (UserInfoResponseDTO) session.getAttribute("userInfo");
        if (user != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO("409", "User is authenticated"));
        }

        if (result.hasErrors()) {
            throw new InvalidUserInputException(result.getFieldError().getDefaultMessage());
        }

        userService.register(registerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/health-conditions")
    public ResponseEntity<?> getHealthConditionList(HttpSession session) {
        UserInfoResponseDTO user = (UserInfoResponseDTO) session.getAttribute("userInfo");

        List<HealthConditionResponseDTO> healthConditionList = userService.getHealthConditionList(user.getUserId());
        return ResponseEntity.status(HttpStatus.FOUND).body(healthConditionList);
    }

    @GetMapping("/health-conditions/{id}")
    public ResponseEntity<?> getHealCondition(HttpSession session,
                                              @PathVariable("id") Integer healthConditionId) {
        UserInfoResponseDTO user = (UserInfoResponseDTO) session.getAttribute("userInfo");

        HealthConditionResponseDTO healthConditionResponseDTO = userService.getHealthCondition(user.getUserId(), healthConditionId);

        if (healthConditionResponseDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(healthConditionResponseDTO);
    }

    @PostMapping("/health-condition")
    public ResponseEntity<?> addHealthCondition(@Valid @RequestBody HealthConditionRequestDTO healthConditionRequestDTO,
                                                BindingResult result,
                                                HttpSession session) {
        UserInfoResponseDTO user = (UserInfoResponseDTO) session.getAttribute("userInfo");

        if (result.hasErrors()) {
            throw new InvalidUserInputException(result.getFieldError().getDefaultMessage());
        }

        userService.addHealthCondition(healthConditionRequestDTO, user.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/health-conditions/{id}")
    public ResponseEntity<?> updateHealthCondition(HttpSession session,
                                                   @PathVariable("id") Integer healthConditionId,
                                                   @Valid @RequestBody HealthConditionRequestDTO healthConditionRequestDTO,
                                                   BindingResult result) {
        UserInfoResponseDTO user = (UserInfoResponseDTO) session.getAttribute("userInfo");

        userService.updateHealthCondition(user.getUserId(), healthConditionId, healthConditionRequestDTO);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/health-condition/{id}")
    public ResponseEntity<?> deleteHealthCondition(HttpSession session,
                                                   @PathVariable("id") Integer healthConditionId) {
        UserInfoResponseDTO user = (UserInfoResponseDTO) session.getAttribute("userInfo");

        userService.deleteHealthCondition(user.getUserId(), healthConditionId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/bmi-records")
    public ResponseEntity<?> getBmiRecord(HttpSession session) {
        UserInfoResponseDTO user = (UserInfoResponseDTO) session.getAttribute("userInfo");

        List<BMIResponseDTO> bmiRecordList = userService.getBMIRecordList(user.getUserId());

        return ResponseEntity.status(HttpStatus.FOUND).body(bmiRecordList);
    }

    @GetMapping("/bmi-record/{id}")
    public ResponseEntity<?> getBmiRecord(@PathVariable("id") Integer bmiRecordId,
                                          HttpSession session) {
        UserInfoResponseDTO user = (UserInfoResponseDTO) session.getAttribute("userInfo");

        BMIResponseDTO bmiResponseDTO = userService.getBMIRecord(user.getUserId(), bmiRecordId);;

        if (bmiResponseDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(bmiResponseDTO);
    }

    @PostMapping("/bmi-record")
    public ResponseEntity<?> addBmiRecord(HttpSession session,
                                          @Valid @RequestBody BMIRequestDTO bmiRequestDTO,
                                          BindingResult result) {
        UserInfoResponseDTO user = (UserInfoResponseDTO) session.getAttribute("userInfo");

        if (result.hasErrors()) {
            throw new InvalidUserInputException(result.getFieldError().getDefaultMessage());
        }

        userService.addBmiRecord(user.getUserId(), bmiRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/bmi-record/{id}")
    public ResponseEntity<?> updateBmiRecord(HttpSession session,
                                             @PathVariable("id") Integer bmiRecordId,
                                             @Valid @RequestBody BMIRequestDTO bmiRequestDTO,
                                             BindingResult result) {
        UserInfoResponseDTO user = (UserInfoResponseDTO) session.getAttribute("userInfo");

        userService.updateBmiRecord(user.getUserId(), bmiRecordId, bmiRequestDTO);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/bmi-record/{id}")
    public ResponseEntity<?> deleteBmiRecord(HttpSession session,
                                             @PathVariable("id") Integer bmiRecordId) {
        UserInfoResponseDTO user = (UserInfoResponseDTO) session.getAttribute("userInfo");

        userService.deleteBmiRecord(user.getUserId(), bmiRecordId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nutrition-targets")
    public ResponseEntity<?> getNutritionTargetList(HttpSession session) {
        UserInfoResponseDTO user = (UserInfoResponseDTO) session.getAttribute("userInfo");

        List<NutritionTargetResponseDTO> nutritionTargetList = userService.getNutritionTargetList(user.getUserId());

        return ResponseEntity.status(HttpStatus.FOUND).body(nutritionTargetList);
    }

    @PostMapping("/nutrition-target")
    public ResponseEntity<?> addNutritionTarget(HttpSession session,
                                                @Valid @RequestBody NutritionTargetRequestDTO nutritionTargetRequestDTO,
                                                BindingResult result) {
        UserInfoResponseDTO user = (UserInfoResponseDTO) session.getAttribute("userInfo");

        if (result.hasErrors()) {
            throw new InvalidUserInputException(result.getFieldError().getDefaultMessage());
        }

        userService.addNutritionTarget(user.getUserId(), nutritionTargetRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
