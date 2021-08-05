package jp.kobe_u.cs.daikibo.Kobetsukan.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.kobe_u.cs.daikibo.Kobetsukan.entity.Reservation;
import jp.kobe_u.cs.daikibo.Kobetsukan.entity.User;
import jp.kobe_u.cs.daikibo.Kobetsukan.repository.ReservationRepository;
import jp.kobe_u.cs.daikibo.Kobetsukan.repository.UserRepository;

@Service

public class UserService {

    /* UserRepository：ユーザー情報
    　 ReservationRepository：予約情報
    */
    @Autowired
    UserRepository users;
    @Autowired
    ReservationRepository reserves;

    /**
     * 講師の一覧を取得する
     * 
     * @param teacherId
     * @return list:user
     */
    //all:すべてのユーザーを取得するようになっている
    //all.forEach:先生のみに変更
    public List<User> getAllTeachers() {
        ArrayList<User> list = new ArrayList<>();
        Iterable<User> all = users.findAll();
        all.forEach(lists -> {if(lists.isTeacher()) list.add(lists);});
        return list;
    }

    
    /**
     * 指定した講師のシフトを取得する
     * @param teacherId
     * @return list:reservation
     */
    //teacherIDで予約情報一括取得
    public List<Reservation> getShiftByUser(String teacherId) {
        Iterable<Reservation> all = reserves.findByteacherId(teacherId);
        ArrayList<Reservation> list = new ArrayList<>();

        all.forEach(list::add);
        return list;
    }


    /**
     * 授業予約可能かを確認する  
     * @param date      
     * @param period 
     * @return　予約可能ならtrue
     */
    public boolean canReserve(Date date, int period) {
        //予約レポジトリのメソッド変更必要
        Long count = reserves.countAlreadyBooked(date, period);

        return (count == 0) ? true : false;
    }

    /**
     * 予約を登録する
     */
    public Reservation add(Reservation r) {
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

    public List<Reservation> getDummy() throws ParseException {
        ArrayList<Reservation> list = new ArrayList<>();

        //日付文字列
        String DateStr = "2021/08/03 00:00:00";
        int period=1;
        String teacherId="oono";
        String studentId2="suzuki";
        String studentId1="satou";

        Reservation dummy = new Reservation(getDate(DateStr),period,teacherId,studentId1,studentId2);
        DateStr = "2021/08/03";
        Reservation dummy1 = new Reservation(getDate(DateStr),2,"kozima","suzuki","yamada");
        DateStr = "2021/08/04";
        Reservation dummy2 = new Reservation(getDate(DateStr),3,"hayasi",null,"satou");
        DateStr = "2021/08/05";
        Reservation dummy3 = new Reservation(getDate(DateStr),4,"kozima","yamamoto",null);
        DateStr = "2021/08/06";
        Reservation dummy4 = new Reservation(getDate(DateStr),5,"hayasi",null,null);
        
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