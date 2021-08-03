package jp.kobe_u.cs.daikibo.Kobetsukan.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 予約エンティティ
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation {
    /**
     * 予約ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long rid;
    /**
     * 日付
     */
    @Temporal(TemporalType.DATE)
    Date date;
    /**
     * その日の何コマ目か
     */
    int period;
    /**
     * 講師ID
     */
    String teacherId;
    /**
     * 生徒ID（1人目）
     */
    String studentId1;
    /**
     * 生徒ID（2人目）
     */
    String studentId2;
}