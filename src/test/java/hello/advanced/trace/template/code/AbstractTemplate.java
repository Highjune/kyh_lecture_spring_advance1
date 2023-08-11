package hello.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {

    public void execute() { // 변하지 않는 부분(템플릿) => 다 공통으로 사용하는 부분이므로, 변경이 있더라도 이 곳 1곳만 변경하면 상속해서 사용하는 모든 것들에서 다 적용이 된다.
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        call(); // 상속 (자식 클래스에 따라 다를 것 -> 변하는 부분)
        // 비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    protected abstract void call(); // 변하는 부분
}
