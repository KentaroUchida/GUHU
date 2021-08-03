package jp.kobe_u.cs.daikibo.Kobetsukan.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.kobe_u.cs.daikibo.Kobetsukan.entity.User;
import jp.kobe_u.cs.daikibo.Kobetsukan.service.ReservationService;
import jp.kobe_u.cs.daikibo.Kobetsukan.service.UserService;
import lombok.RequiredArgsConstructor;

/**
 * 予約コントローラ
 */
@Controller
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final UserService us;
    private final ReservationService rs;

    /**
     * 
     * @param model 画面モデル
     * @return 呼び出すテンプレート
     */
    @GetMapping("/teachers")
    String showReservationForm(Model model) {
        // 講師の一覧をモデルに登録
        List<User> teachers = us.getAllTeachers();
        model.addAttribute("teachers", teachers);
        return "reservation/teachers";
    }
}
