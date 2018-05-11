package cn.edu.bupt.sice;

import cn.edu.bupt.sice.execute.TransmittableThreadLocalThreadPoolExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.*;

@SpringBootApplication
@EnableAsync
public class CodelintApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodelintApplication.class, args);
	}
	@EnableAsync
	@Configuration
	class TaskPoolConfig {
		@Bean("taskExecutor")
		public Executor reportExecutor() {
			BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue(1000);
			ThreadPoolExecutor executor = new TransmittableThreadLocalThreadPoolExecutor(50,50,1000, TimeUnit.MILLISECONDS,blockingQueue);
			executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
			return executor;
		}
	}
}
