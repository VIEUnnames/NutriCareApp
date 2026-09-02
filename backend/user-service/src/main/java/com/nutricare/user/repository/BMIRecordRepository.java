package com.nutricare.user.repository;

import com.nutricare.user.dto.BMIResponseDTO;
import com.nutricare.user.model.BMIRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BMIRecordRepository extends JpaRepository<BMIRecord, Integer> {
    @Query("SELECT new com.nutricare.user.dto.BMIResponseDTO(" +
            "b.bmiRecordId, b.weightCm, b.heightCm, b.recordAt) FROM BMIRecord b " +
            "WHERE b.user.id = :userId")
    List<BMIResponseDTO> findByUserId(@Param("userId") Integer userId);

    @Query("SELECT new com.nutricare.user.dto.BMIResponseDTO(" +
            "b.bmiRecordId, b.weightCm, b.heightCm, b.recordAt) FROM BMIRecord b " +
            "WHERE b.user.id = :userId AND b.bmiRecordId = :bmiRecordId")
    BMIResponseDTO findByUserIdAndBmiRecordId(@Param("userId") Integer userId,
                                              @Param("bmiRecordId") Integer bmiRecordId);

    BMIRecord findByBmiRecordIdAndUser_UserId(Integer bmiRecordId, Integer userId);
}
