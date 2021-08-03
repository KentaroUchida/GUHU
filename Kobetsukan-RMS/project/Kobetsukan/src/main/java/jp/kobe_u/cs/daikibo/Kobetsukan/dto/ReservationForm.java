package jp.kobe_u.cs.daikibo.Kobetsukan.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 予約のフォームクラス
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationForm {
    /**
     * 日付
     */
    @NotBlank
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
    String date;
    /**
     * その日の何コマ目か
     */
    @NotNull
    Integer period;
    /**
     * 講師ID
     */
    @NotBlank
    String teacherId;
    /**
     * 生徒ID（1人目）
     */
    @NotBlank
    String studentId1;
    /**
     * 生徒ID（2人目）
     */
    String studentId2;
}
