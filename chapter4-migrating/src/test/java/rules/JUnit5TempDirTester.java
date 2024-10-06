package rules;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JUnit5TempDirTester {
//    @TempDir 어노테이션으로 임시 디렉토리 생성
    @TempDir
    Path tempDir;

    private static Path createdFile;

    @Test
    public void testTemporaryFolder() throws IOException{
//        임시 디렉토리 생성 확인
        assertTrue(Files.isDirectory(tempDir));

        createdFile = Files.createFile(tempDir.resolve("createdFile.txt"));

//        임시파일 생성 확인
        assertTrue(createdFile.toFile().exists());
    }

    @AfterAll
    public static void afterAll() {
//        리로스 삭제 확인
        assertFalse(createdFile.toFile().exists());
    }
}
