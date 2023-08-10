package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    @Test
    void field() {
        log.info("main start");
        Runnable userA = () -> {
            fieldService.logic("userA");
        };
        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A"); // 로그에 Thread명이 이렇게 찍힘
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
//        sleep(2000); // 동시성 문제 발생X. A로직 실행하는 1초 걸리므로
        sleep(100); // 동시성 문제 발생O. threadA.start(); 작업하는데 걸리는 시간이 1초이므로 끝나지 않았을 때 threadB.start()가 실행이 된다.
        threadB.start();

        sleep(3000); // 메인 쓰레드 종료 대기(이걸 하지 않으면 threadB.start(); 돌기 전에 메인 쓰레드가 끝나버린다)
        log.info("main exit");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
