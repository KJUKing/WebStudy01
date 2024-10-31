package kr.or.ddit.member.service;

import kr.or.ddit.vo.MemberVO;

/**
 * 인증시스템용 비즈니스 로직 레이어
 *
 */
public interface AuthenticateService {

    /**
     * 인증 여부 판단
     * @param inputData : 입력된 id와 password
     * @return 인증 성공시 나머지 VO
     *          인증 실패시 null 반환(차후 변경)
     */
    public MemberVO authenticate(MemberVO inputData);


}
