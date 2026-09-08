package com.nutricare.user.controller;

import com.nutricare.user.dto.*;
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
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("400", result.getFieldError().getDefaultMessage()));
        }

        UserInfoResponseDTO userInfoResponseDTO = null;
        try {
            userInfoResponseDTO = userService.login(loginRequestDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("400", e.getMessage()));
        }

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
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("400", result.getFieldError().getDefaultMessage()));
        }

        try {
            userService.register(registerRequestDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("400", e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/health-conditions")
    public ResponseEntity<?> getHealthConditionList(HttpSession session) {
        UserInfoResponseDTO user = (UserInfoResponseDTO) session.getAttribute("userInfo");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDTO("403", "User is not exists"));
        }

        List<HealthConditionResponseDTO> healthConditionList = userService.getHealthConditionList(user.getUserId());
        return ResponseEntity.status(HttpStatus.FOUND).body(healthConditionList);
    }

    @GetMapping("/health-conditions/{id}")
    public ResponseEntity<?> getHealCondition(HttpSession session,
                                              @PathVariable("id") String rawId) {
        UserInfoResponseDTO user = (UserInfoResponseDTO) session.getAttribute("userInfo");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDTO("403", "User is not exists"));
        }

        Integer healthConditionId = null;
        try {
            healthConditionId = Integer.parseInt(rawId);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorResponseDTO("401", "Health Condition is invalid"));
        }

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
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDTO("403", "User is not exists"));
        }

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("400", result.getFieldError().getDefaultMessage()));
        }

        try {
            userService.addHealthCondition(healthConditionRequestDTO, user.getUserId());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("400", e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/health-conditions/{id}")
    public ResponseEntity<?> updateHealthCondition(HttpSession session,
                                                   @PathVariable("id") String rawId,
                                                   @Valid @RequestBody HealthConditionRequestDTO healthConditionRequestDTO,
                                                   BindingResult result) {
        UserInfoResponseDTO user = (UserInfoResponseDTO) session.getAttribute("userInfo");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDTO("403", "User is not exists"));
        }

        Integer healthConditionId = null;
        try {
            healthConditionId = Integer.parseInt(rawId);
            userService.updateHealthCondition(user.getUserId(), healthConditionId, healthConditionRequestDTO);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorResponseDTO("401", "Health Condition is invalid"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("400", e.getMessage()));
        }

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/health-condition/{id}")
    public ResponseEntity<?> deleteHealthCondition(HttpSession session,
                                                   @PathVariable("id") String rawId) {
        UserInfoResponseDTO user = (UserInfoResponseDTO) session.getAttribute("userInfo");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDTO("403", "User is not exists"));
        }

        Integer healthConditionId = null;
        try {
            healthConditionId = Integer.parseInt(rawId);
            userService.deleteHealthCondition(user.getUserId(), healthConditionId);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorResponseDTO("401", "Health Condition is invalid"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("400", e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/bmi-records")
    public ResponseEntity<?> getBmiRecord(HttpSession session) {
        UserInfoResponseDTO user = (UserInfoResponseDTO) session.getAttribute("userInfo");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDTO("403", "User is not exists"));
        }

        List<BMIResponseDTO> bmiRecordList = null;
        try {
            bmiRecordList = userService.getBMIRecordList(user.getUserId());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("400", e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(bmiRecordList);
    }

    @GetMapping("/bmi-record/{id}")
    public ResponseEntity<?> getBmiRecord(@PathVariable("id") String rawId,
                                          HttpSession session) {
        UserInfoResponseDTO user = (UserInfoResponseDTO) session.getAttribute("userInfo");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDTO("403", "User is not exists"));
        }

        Integer bmiRecordId = null;
        BMIResponseDTO bmiResponseDTO = null;
        try {
            bmiRecordId = Integer.parseInt(rawId);
            bmiResponseDTO = userService.getBMIRecord(user.getUserId(), bmiRecordId);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorResponseDTO("401", "Bmi record id is not exists"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("400", e.getMessage()));
        }

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
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDTO("403", "User is not exists"));
        }

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("400", result.getFieldError().getDefaultMessage()));
        }

        try {
            userService.addBmiRecord(user.getUserId(), bmiRequestDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("400", e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/bmi-record/{id}")
    public ResponseEntity<?> updateBmiRecord(HttpSession session,
                                             @PathVariable("id") String id,
                                             @Valid @RequestBody BMIRequestDTO bmiRequestDTO,
                                             BindingResult result) {
        UserInfoResponseDTO user = (UserInfoResponseDTO) session.getAttribute("userInfo");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDTO("403", "User is not exists"));
        }

        Integer bmiRecordId = null;
        try {
            bmiRecordId = Integer.parseInt(id);
            userService.updateBmiRecord(user.getUserId(), bmiRecordId, bmiRequestDTO);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorResponseDTO("400", "BMI Record is invalid"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("400", e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/bmi-record/{id}")
    public ResponseEntity<?> deleteBmiRecord(HttpSession session,
                                             @PathVariable("id") String id) {
        UserInfoResponseDTO user = (UserInfoResponseDTO) session.getAttribute("userInfo");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDTO("403", "User is not exists"));
        }

        Integer bmiRecordId = null;
        try {
            bmiRecordId = Integer.parseInt(id);
            userService.deleteBmiRecord(user.getUserId(), bmiRecordId);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorResponseDTO("400", "BMI Record is invalid"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nutrition-targets")
    public ResponseEntity<?> getNutritionTargetList(HttpSession session) {
        UserInfoResponseDTO user = (UserInfoResponseDTO) session.getAttribute("userInfo");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDTO("403", "User is not exists"));
        }

        List<NutritionTargetResponseDTO> nutritionTargetList = null;

        try {
            nutritionTargetList = userService.getNutritionTargetList(user.getUserId());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("400", e.getMessage()));
        }
        System.out.println(nutritionTargetList.size());
        return ResponseEntity.status(HttpStatus.FOUND).body(nutritionTargetList);
    }

    @PostMapping("/nutrition-target")
    public ResponseEntity<?> addNutritionTarget(HttpSession session,
                                                @Valid @RequestBody NutritionTargetRequestDTO nutritionTargetRequestDTO,
                                                BindingResult result) {
        UserInfoResponseDTO user = (UserInfoResponseDTO) session.getAttribute("userInfo");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDTO("403", "User is not exists"));
        }

        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorResponseDTO("400", result.getFieldError().getDefaultMessage()));
        }

        try {
            userService.addNutritionTarget(user.getUserId(), nutritionTargetRequestDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("400", e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
