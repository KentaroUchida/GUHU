package jp.kobe_u.cs.daikibo.Kobetsukan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.kobe_u.cs.daikibo.Kobetsukan.dto.LoginForm;
import jp.kobe_u.cs.daikibo.Kobetsukan.entity.User;
import jp.kobe_u.cs.daikibo.Kobetsukan.service.UserService;

/**
 * ログインコントローラ
 */
@Controller
public class LoginController {
    private final UserService us;

    /**
     * ログイン画面を表示する
     * 
     * @param form  ログインフォーム
     * @param model 画面モデル
     * @return ログイン画面
     */
    @GetMapping("")
    String showLoginForm(@ModelAttribute("loginForm") LoginForm form, Model model) {
        model.addAttribute("loginForm", form);
        return "index";
    }

    /**
     * ログインする
     * 
     * @param form   ログインフォーム
     * @param result BindingResult
     * @param model  画面モデル
     * @return ログイン成功→トップ画面，ログイン失敗→ログイン画面
     */
    @PostMapping("/login")
    String login(@Validated @ModelAttribute("loginForm") LoginForm form, BindingResult result, Model model) {
        // 入力チェックに引っかかった場合，ユーザ登録画面に戻る
        if (result.hasErrors()) {
            return showLoginForm(form, model);
        }
        // ユーザが存在するか確認
        us.getUser(form.getUid());
        return "refirect:/" + form.getUid() + "/top";
    }

    /**
     * トップ画面を表示する
     * 
     * @param uid   自分のユーザID
     * @param model 画面モデル
     * @return トップ画面
     */
    @GetMapping("/{uid}/top")
    String showTopPage(@PathVariable("uid") String uid, Model model) {
        // 自分のユーザ情報をモデルに登録
        User user = us.getUser(uid);
        model.addAttribute("user", user);
        return "top";
    }
}
