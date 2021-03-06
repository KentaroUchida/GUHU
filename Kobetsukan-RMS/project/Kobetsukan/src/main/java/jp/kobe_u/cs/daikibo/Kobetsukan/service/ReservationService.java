package jp.kobe_u.cs.daikibo.Kobetsukan.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.kobe_u.cs.daikibo.Kobetsukan.entity.Reservation;
import jp.kobe_u.cs.daikibo.Kobetsukan.exception.KobetsukanAppException;
import jp.kobe_u.cs.daikibo.Kobetsukan.repository.ReservationRepository;
import jp.kobe_u.cs.daikibo.Kobetsukan.repository.UserRepository;

@Service
public class ReservationService {

    /* UserRepository：ユーザー情報
    　 ReservationRepository：予約情報
    */
    @Autowired
    UserRepository users;
    @Autowired
    ReservationRepository reserves;

    /**
     * 予約を取得する
     * @param rid 予約ID
     * @return
     */
    public Reservation getReservation(Long rid) {
        Reservation r = reserves.findById(rid)
            .orElseThrow(() -> new KobetsukanAppException(KobetsukanAppException.NO_SUCH_RESERVATION_EXISTS,
                rid + ": No such reservation exists"));
        return r;
    }
    
    /**
     * 指定した講師のシフトを取得する
     * @param uid
     * @return list:reservation
     */
    //teacherIDで予約情報一括取得
    public List<Reservation> getReservationByTeacher(String uid) {
        Iterable<Reservation> all = reserves.findByteacherId(uid);
        ArrayList<Reservation> list = new ArrayList<>();

        //生徒が二人埋まってたらリストに追加しない
        all.forEach(lists -> {if(lists.studentId1==null || lists.studentId2==null) list.add(lists);});

        return list;
    }

    /**
     * 指定した生徒の予約を取得する
     * @param uid
     * @return list:reservation
     */
    public List<Reservation> getReservationByStudent(String uid) {
        return reserves.findByStudentId(uid);
    }


    /**
     * 授業予約可能かを確認する  
     * @param date      
     * @param period 
     * @return　予約可能ならtrue
     */
    public boolean isReservable(Date date, int period) {
        //予約レポジトリのメソッド変更必要
        Long count = reserves.countAlreadyBooked(date, period);

        return (count == 0) ? true : false;
    }

    /**
     * 予約を登録する
     */
    public Reservation createReservation(Reservation r) {
        /* (必要なら)空きチェック
        if (!canReserve(r.getDate(), r.getPeriod())) {
            Reservation reserve = getReserve(r.getDate(), r.getPeriod());
            throw new Exception("予約できません");
            //例外処理:専用のコード作る場合
            throw new YoyakuAppException(YoyakuAppException.ROOM_ALREADY_BOOKED,
                    "Reserve " + reserve.getDate() +":"+ reserve.getpriod() + " is already booked.");
        }
        */
        return reserves.save(r); //セーブした値返す
    }
    /**
     * カレンダーを取得する
     * 一か月分に設定してある、予約可能な日時の範囲設定必要かも
     */
    public List<Calendar> getCalender(){
        ArrayList<Calendar> list = new ArrayList<>();
        Calendar cl = Calendar.getInstance();
        cl.set(2021,8,1);
        //その月が何日まであるか
		int daysCount=cl.getActualMaximum(Calendar.DAY_OF_MONTH);
        //一か月分リストに格納
        for(int i=0;i<daysCount;i++){
            int date=i+1;
            Calendar cal = Calendar.getInstance();
            cal.set(cl.get(Calendar.YEAR),cl.get(Calendar.MONTH),date);
            list.add(cal);
        }
        return list;
    }

    //一旦一つのダミー、複数のダミー作成に変更する必要あり
    /**
     * ダミーシフトを作成する
     * 
     * @param date
     * @param period
     * @param teacherId
     * @param studentId2
     * @param studentId1
     * @return list:reservation
     * @throws ParseException
     */

    public List<Reservation> getDummy(){
        ArrayList<Reservation> list = new ArrayList<>();

        //日付文字列
        String DateStr = "2021/08/03 00:00:00";
        int period=1;
        String teacherId="oono";
        String studentId2="suzuki";
        String studentId1="satou";

        Reservation dummy = new Reservation(1L,getDate(DateStr),period,teacherId,studentId1,studentId2);
        DateStr = "2021/08/03";
        Reservation dummy1 = new Reservation(2L,getDate(DateStr),2,"kojima","suzuki","yamada");
        DateStr = "2021/08/04";
        Reservation dummy2 = new Reservation(3L,getDate(DateStr),3,"hayashi",null,"satou");
        DateStr = "2021/08/05";
        Reservation dummy3 = new Reservation(4L,getDate(DateStr),4,"kojima","yamamoto",null);
        DateStr = "2021/08/06";
        Reservation dummy4 = new Reservation(5L,getDate(DateStr),5,"hayashi", null, null);
        
        list.add(dummy);
        list.add(dummy1);
        list.add(dummy2);
        list.add(dummy3);
        list.add(dummy4);

        return list;
    }


    /** 
     *  private 
     * 日付をstring→Date型へ変換
    */
    private Date getDate(String DateStr){
        try {
            //フォーマット設定
            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy/MM/dd");
            // Date型に変換
            Date date = sdformat.parse(DateStr);
            return date;
        }catch(ParseException e){
            e.printStackTrace();
            //現在の時刻を返す
            Date date = new Date();
            return date;
        }

    }

}