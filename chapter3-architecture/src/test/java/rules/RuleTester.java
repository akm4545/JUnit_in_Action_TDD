package rules;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RuleTester {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private static File createdFolder;
    private static File createdFile;

    @Test
    public void testTemporaryFolder() throws IOException {
//        사용자명 폴더 아래 /Temp 폴더에 임시 폴더와 파일 생성
        createdFolder = folder.newFolder("createdFolder");
        createdFile = folder.newFile("createdFile.txt");

//        생성이 정상적으로 이루어졌는지 검증
        assertTrue(createdFolder.exists());
        assertTrue(createdFile.exists());
    }

//    테스트가 모두 끝난 뒤 임시 자원이 더 이상 존재하지 않는지 확인
    @AfterClass
    public static void cleanUpAfterAllTestsRan() {
        assertFalse(createdFolder.exists());
        assertFalse(createdFile.exists());
    }
}
