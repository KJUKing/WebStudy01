package kr.or.ddit.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 회원관리용 Domain Layer
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"memId", "memRegno1", "memRegno2"})
@ToString
public class MemberVO implements Serializable {
    private String memId;
    @ToString.Exclude
    private transient String memPass;
    private String memName;
    @ToString.Exclude
    private transient String memRegno1;
    @ToString.Exclude
    private transient String memRegno2;
    private LocalDate memBir;
    private String memZip;
    private String memAdd1;
    private String memAdd2;
    private String memHometel;
    private String memComtel;
    private String memHp;
    private String memMail;
    private String memJob;
    private String memLike;
    private String memMemorial;
    private LocalDate memMemorialday;
    private Long memMileage;
    private String memDelete;

}
