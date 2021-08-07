package jp.kobe_u.cs.daikibo.Kobetsukan.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import jp.kobe_u.cs.daikibo.Kobetsukan.entity.Reservation;
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
     * 自分の生徒ID
     */
    @NotBlank
    String studentId;

    /**
     * フォームをエンティティに変換する
     * 
     * @return 予約エンティティ
     */
    public Reservation toEntity() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = new Date();
        try {
            newDate = sdf.parse(date);
        } catch (ParseException e) {
            // 例外処理（書いてない）
            e.printStackTrace();
        }
        return new Reservation(null, newDate, period, teacherId, studentId, null);
    }

    /**
     * エンティティをフォームに変換する
     * 
     * @param r 予約エンティティ
     * @return 予約フォーム
     */
    public static ReservationForm toForm(Reservation r) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return new ReservationForm(sdf.format(r.getDate()), r.getPeriod(), r.getTeacherId(), null);
    }
}
