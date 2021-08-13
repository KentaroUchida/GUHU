package jp.kobe_u.cs.daikibo.Kobetsukan.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import jp.kobe_u.cs.daikibo.Kobetsukan.entity.Reservation;



/**
 * 予約リポジトリ
 */
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    // 日付で検索する
    public Iterable<Reservation> findByDate(Date date);

    //先生の全予約を取得する
    public Iterable<Reservation> findByteacherId(String teacherId);

    // 生徒の全予約を取得する
    @Query(value = "SELECT * FROM reservation WHERE student_id1 = ?1 OR student_id2 = ?1", nativeQuery = true)
    public List<Reservation> findByStudentId(String studentId);

    /*
    *与えられた日時に重複している予約の個数を取得する
    * @param Date date
    * @param int period
    */
    @Query(value = "SELECT COUNT(*) FROM reservation r WHERE (r.date = ?1) AND (r.period = ?2)",  nativeQuery = true)    
    public Long countAlreadyBooked(Date  date, int period);
}