package jp.kobe_u.cs.daikibo.Kobetsukan.exception;

public class KobetsukanAppException extends RuntimeException{
   public static final int NO_SUCH_USER_EXISTS = 101;
   public static final int USER_ALREADY_EXISTS = 102;
   public static final int INVALID_USER_INFO = 103;
   public static final int INVALID_USER_OPERATION = 104;
   int code;
   public KobetsukanAppException(int code, String message) {
       super(message);
       this.code = code;
   }
   public KobetsukanAppException(int code, String message, Exception cause) {
       super(message, cause);
       this.code = code;
   }
   public int getCode() {
       return code;
   }
}