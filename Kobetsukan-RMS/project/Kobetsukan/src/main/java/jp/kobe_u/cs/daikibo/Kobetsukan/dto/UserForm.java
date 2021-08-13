package jp.kobe_u.cs.daikibo.Kobetsukan.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import jp.kobe_u.cs.daikibo.Kobetsukan.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    @Pattern(regexp ="[a-z0-9_\\-]{4,16}")
    String uid; //メンバーID．英小文字，数字，ハイフン，アンダーバー．4文字以上16文字以下．

    @NotBlank
    @Size(min = 1, max = 32)
    String name; //名前．最大32文字

    Boolean isTeacher;

    public User toEntity() {
        User u = new User(uid, name, isTeacher);
        return u;
    }
}