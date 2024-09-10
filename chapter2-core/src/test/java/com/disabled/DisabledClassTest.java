package com.disabled;

import com.lifecycle.SUT;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

//전체 테스트 클래스 비활성화
//동료 개발자나 나중에 테스트를 다시 볼 자기 자신에게 테스트가 왜 비활성화되었는지를 알랴 줄 수 있으므로
//@Disabled의 파라미터로 테스트를 비활성화한 이유를 적는 방법은 실무에서 자주 쓰인다
@Disabled("기능이 아직 개발 중")
public class DisabledClassTest {
    private SUT systemUnderTest = new SUT("테스트 대상 시스템");

    @Test
    void testRegularWork() {
        boolean canReceiveRegularWork = systemUnderTest.canReceiveRegularWork();

        assertTrue(canReceiveRegularWork);
    }

    @Test
    void testAdditionalWork(){
        boolean canReceiveAdditionalWork = systemUnderTest.canReceiveAdditionalWork();

        assertFalse(canReceiveAdditionalWork);
    }
}
