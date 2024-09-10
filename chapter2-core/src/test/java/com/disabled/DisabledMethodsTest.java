package com.disabled;

import com.lifecycle.SUT;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DisabledMethodsTest {
    private SUT systemUnderTest = new SUT("테스트 대상 시스템");

    @Test
    @Disabled
    void testRegularWork(){
        boolean canReceiveRegularWork = systemUnderTest.canReceiveRegularWork();

        assertTrue(canReceiveRegularWork);
    }

//    위 방법보다 해당 방법이 권장된다 (이유 기입)
    @Test
    @Disabled("기능이 아직 개발 중")
    void testAdditionalWork(){
        boolean canReceiveAdditionalWork = systemUnderTest.canReceiveAdditionalWork();

        assertFalse(canReceiveAdditionalWork);
    }
}
