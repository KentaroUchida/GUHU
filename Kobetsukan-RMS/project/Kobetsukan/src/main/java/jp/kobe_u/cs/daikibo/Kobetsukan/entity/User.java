package jp.kobe_u.cs.daikibo.Kobetsukan.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    String uid;   //ユーザーID
    String name;  //氏名
    boolean isTeacher; //先生かどうか
    
}