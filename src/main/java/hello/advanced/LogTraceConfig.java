package hello.advanced;

import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() { // 각 Controller, Service, Repository 에서 주입받음
//        return new FieldLogTrace(); // 동시성 문제O
        return new ThreadLocalLogTrace();  // 동시성 문제X. ThreadLocal 로 해결
    }
}
