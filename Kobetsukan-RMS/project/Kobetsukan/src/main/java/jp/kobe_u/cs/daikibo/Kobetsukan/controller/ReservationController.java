package jp.kobe_u.cs.daikibo.Kobetsukan.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
     * 画面に講師の一覧を表示する
     * 
     * @param uid   自分のユーザID
     * @param model 画面モデル
     * @return 呼び出すテンプレート
     */
    @GetMapping("{uid}/teachers")
    String showTeacherList(@PathVariable("uid") String uid, Model model) {
        // 自分のユーザ情報をモデルに登録
        User user = us.getUser(uid);
        model.addAttribute("user", user);
        // 講師の一覧をモデルに登録
        List<User> teachers = us.getAllTeachers();
        model.addAttribute("teachers", teachers);
        return "teachers";
    }

    /**
     * 画面に講師のシフトを表示する
     * 
     * @param uid   自分のユーザID
     * @param tUid  講師のユーザID
     * @param model 画面モデル
     * @return 呼び出すテンプレート
     */
    @GetMapping("{uid}/teachers/{tUid}/shift")
    String showTeachersShift(@PathVariable("uid") String uid, @PathVariable("tUid") String tUid, Model model) {
        // 自分のユーザ情報をモデルに登録
        User user = us.getUser(uid);
        model.addAttribute("user", user);
        // 講師のシフトをモデルに登録
        List<Reservation> reserves = rs.getReservationByUser(tUid);
        model.addAttribute("reserves", reserves);
        return "teacherShift";
    }

    /**
     * 講習会の予約フォームを表示する
     * 
     * @param uid   自分のユーザID
     * @param tUid  講師のユーザID
     * @param rid   予約ID
     * @param model 画面モデル
     * @return 呼び出すテンプレート
     */
    @GetMapping("{uid}/teachers/{tUid}/shift/{rid}/reserve")
    String showReservationForm(@PathVariable("uid") String uid, @PathVariable("tUid") String tUid,
            @PathVariable("rid") Long rid, Model model) {
        // モデルに空のフォームを登録
        model.addAttribute("ReservationForm", new ReservationForm());
        return "reserve";
    }

    @PostMapping("/")
    String registerReservation(@ModelAttribute("ReservationForm") @Validated ReservationForm form, BindingResult result,
            Model model) {
        // フォームの内容にエラーがある場合
        if (result.hasErrors()) {
            return "reserve";
        }
        // フォームをエンティティに変換し，データベースに登録
        rs.createReservation(form.toEntity());
        // 自分のページに飛ばしたい（これはダメ）
        return "redirect:/" + form.getStudentId1() + "/top";
    }
}
