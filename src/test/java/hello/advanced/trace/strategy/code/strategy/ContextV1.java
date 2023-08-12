package hello.advanced.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 필드에 전략을 보관하는 방식
 * 사실 스프링의 의존관계 주입에서 사용하는 방식이 바로 전략 패턴이다.
 */
@Slf4j
public class ContextV1 {

    private Strategy strategy; // 새로운 전략으로 변경하더라도 인터페이스이므로, 이 클래스에서의 코드 변경은 하나도 없다.

    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute() { // 아래의 거대한 문맥은 그대로인데, '위임'하는 부분만 변경된 부분이 주입바덱 된다.. Strategy 를 통해서.
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        strategy.call();    // 위임
        // 비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
