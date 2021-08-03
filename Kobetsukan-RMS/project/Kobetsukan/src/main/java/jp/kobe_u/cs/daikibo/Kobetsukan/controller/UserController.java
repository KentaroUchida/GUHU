package jp.kobe_u.cs.daikibo.Kobetsukan.controller;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.kobe_u.cs.daikibo.Kobetsukan.dto.UserForm;
import jp.kobe_u.cs.daikibo.Kobetsukan.entity.User;
import jp.kobe_u.cs.daikibo.Kobetsukan.service.UserService;

@Controller
@RequestMapping("/admin")
public class UserController {
   @Autowired
   UserService uService;
   /**
     * 管理者用・ユーザ登録ページ HTTP-GET /admin/register
     * 
     * @param model
     * @return
     */
    @GetMapping("/register")
    String showUserForm(@ModelAttribute UserForm form, Model model) {
        List<User> Users = uService.getAllUsers();
        model.addAttribute("Users", Users);
        model.addAttribute("UserForm", form);

        return "register";
    }

    /**
     * 管理者用・ユーザ登録確認ページを表示 HTTP-POST /admin/check
     * 
     * @param form
     * @param model
     * @return
     */
    @PostMapping("/check")
    String checkUserForm(@Validated @ModelAttribute(name = "UserForm") UserForm form, 
            BindingResult bindingResult, Model model) {
        // 入力チェックに引っかかった場合、ユーザー登録画面に戻る
        if (bindingResult.hasErrors()) {
            // GETリクエスト用のメソッドを呼び出して、ユーザー登録画面に戻る
            return showUserForm(form, model);
        }

        model.addAttribute("UserForm", form);

        return "check";
    }
   /**
    * 管理者用・ユーザ登録処理 -> 完了ページ HTTP-POST /admin/register
    * @param form
    * @param model
    * @return
    */
   @PostMapping("/register")
   String createUser(@ModelAttribute(name = "UserForm") UserForm form,  Model model) {
       User u =  uService.createUser(form);
       model.addAttribute("UserForm", u);
       return "registered";
   }
   /**
    * 管理者用・ユーザ削除処理　HTTP-GET /admin/delete/{mid}
    * @param uid
    * @param model
    * @return
    */
   @GetMapping("/delete/{uid}")
   String deleteUser(@ModelAttribute(name = "UserForm") UserForm form,@PathVariable String uid, Model model) {
       uService.deleteUser(uid);
       return showUserForm(form,model);
   }
}