package jp.kobe_u.cs.daikibo.Kobetsukan.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.kobe_u.cs.daikibo.Kobetsukan.dto.ReservationForm;
import jp.kobe_u.cs.daikibo.Kobetsukan.entity.Reservation;
import jp.kobe_u.cs.daikibo.Kobetsukan.entity.User;
import jp.kobe_u.cs.daikibo.Kobetsukan.service.ReservationService;
import jp.kobe_u.cs.daikibo.Kobetsukan.service.UserService;
import lombok.RequiredArgsConstructor;

/**
 * 予約コントローラ
 */
@Controller
@RequiredArgsConstructor
public class ReservationController {
    private final UserService us;
    private final ReservationService rs;

    /**
     * 講師一覧画面を表示する
     * 
     * @param uid   自分のユーザID
     * @param model 画面モデル
     * @return 講師一覧画面
     */
    @GetMapping("/{uid}/teachers")
    String showTeacherList(@PathVariable("uid") String uid, Model model) {
        // 自分のユーザ情報をモデルに登録
        User user = us.getUser(uid);
        model.addAttribute("user", user);
        // 講師のリストをモデルに登録
        List<User> teachers = us.getAllTeachers();
        model.addAttribute("teachers", teachers);
        return "teachers";
    }

    /**
     * 講師のシフトを表示する
     * 
     * @param uid   自分のユーザID
     * @param tid   講師のユーザID
     * @param model 画面モデル
     * @return 講師のシフト画面
     */
    @GetMapping("/{uid}/teachers/{tid}/shift")
    String showTeacherShift(@PathVariable("uid") String uid, @PathVariable("tid") String tid, Model model) {
        // 自分のユーザ情報をモデルに登録
        User user = us.getUser(uid);
        model.addAttribute("user", user);
        // 講師のユーザ情報をモデルに登録
        User teacher = us.getUser(tid);
        model.addAttribute("teacher", teacher);
        // 講師のシフト
        List<Reservation> reserves = rs.getReservationByTeacher(tid);
        // 自分のIDが入っているものは除いておく（本当はサービス層でする？）
        for (Reservation r : reserves) {
            if ((r.getStudentId1() != null && r.getStudentId1().equals(uid)) || (r.getStudentId2() != null && r.getStudentId2().equals(uid))) {
                reserves.remove(r);
            }
        }
        model.addAttribute("reserves", reserves);
        return "teacherShift";
    }

    /**
     * 講習会予約画面を表示する
     * 
     * @param uid   自分のユーザID
     * @param tid   講師のユーザID
     * @param rid   予約ID
     * @param model 画面モデル
     * @return 講習会予約画面
     */
    @GetMapping("/{uid}/teachers/{tid}/shift/{rid}/reserve")
    String checkReservationForm(@PathVariable("uid") String uid, @PathVariable("tid") String tid,
            @PathVariable("rid") Long rid, Model model) {
        // 自分のユーザIDをモデルに登録
        model.addAttribute("uid", uid);

        Reservation r = rs.getReservation(rid);
        // 生徒ID（1人目）が登録されていないとき
        if (r.getStudentId1() == null) {
            r.setStudentId1(uid);
        }
        // 生徒ID（2人目）が登録されていないとき
        else if (r.getStudentId2() == null) {
            r.setStudentId2(uid);
        }
        // 予約フォームをモデルに登録
        model.addAttribute("ReservationForm", ReservationForm.toForm(r));
        return "reserve";
    }

    /**
     * 講習会を予約する
     * 
     * @param form  予約フォーム
     * @param model 画面モデル
     * @return トップ画面にリダイレクト
     */
    @PostMapping("/{uid}/reserve")
    String registerReservation(@PathVariable("uid") String uid, @ModelAttribute("ReservationForm") ReservationForm form,
            Model model) {
        rs.createReservation(form.toEntity());
        return "redirect:/" + uid + "/top";
    }
}