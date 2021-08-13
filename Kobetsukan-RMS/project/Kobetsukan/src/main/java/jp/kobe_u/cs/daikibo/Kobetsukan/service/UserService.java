package jp.kobe_u.cs.daikibo.Kobetsukan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 

import jp.kobe_u.cs.daikibo.Kobetsukan.dto.UserForm;
import jp.kobe_u.cs.daikibo.Kobetsukan.entity.User;
import jp.kobe_u.cs.daikibo.Kobetsukan.exception.KobetsukanAppException;
import jp.kobe_u.cs.daikibo.Kobetsukan.repository.UserRepository;

@Service
public class UserService {
   @Autowired
   UserRepository uRepo;
   /**
    * ユーザーを作成する (C)
    * @param form
    * @return
    */
   public User createUser(UserForm form) {
       //IDの重複チェック
       String uid = form.getUid();
       if (uRepo.existsById(uid)) {
           throw new KobetsukanAppException(KobetsukanAppException.USER_ALREADY_EXISTS, uid + ": User already exists");
       }
       User u = form.toEntity();
       return uRepo.save(u);
   }
   /**
    * メンバーを取得する (R)
    * @param uid
    * @return
    */
   public User getUser(String uid) {
       User u = uRepo.findById(uid).orElseThrow(
               () -> new KobetsukanAppException(KobetsukanAppException.NO_SUCH_USER_EXISTS, uid + ": No such user exists"));
       return u;
   }
   /**
    * 全メンバーを取得する (R)
    * @return
    */
   public List<User> getAllUsers() {
       return uRepo.findAll();
   }
   /**
    * メンバーを削除する (D)
    */
   public void deleteUser(String uid) {
       User u = getUser(uid);
       uRepo.delete(u);
   }

   /**
     * 講師の一覧を取得する
     * 
     * @param teacherId
     * @return list
     */
    //all:すべてのユーザーを取得するようになっている
    //all.forEach:先生のみに変更
    public List<User> getAllTeachers() {
        ArrayList<User> list = new ArrayList<>();
        Iterable<User> all = uRepo.findAll();
        all.forEach(lists -> {if(lists.isTeacher()) list.add(lists);});
        return list;
    }
}