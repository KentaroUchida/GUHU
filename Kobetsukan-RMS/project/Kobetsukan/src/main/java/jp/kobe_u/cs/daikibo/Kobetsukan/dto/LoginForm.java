package jp.kobe_u.cs.daikibo.Kobetsukan.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ログインフォーム
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {
    /**
     * ユーザID
     */
    @NotBlank
    String uid;
}
