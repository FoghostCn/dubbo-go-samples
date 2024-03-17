package org.apache.dubbo.samples;

import org.apache.dubbo.samples.proto.GreetRequest;
import org.apache.dubbo.samples.proto.GreetResponse;
import org.apache.dubbo.samples.proto.GreetService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    // Define a private variable (Required in Spring)
    private static GreetService greetService;
    public static void main(String[] args) throws InterruptedException, IOException, ExecutionException {
        System.setProperty("dubbo.application.service-discovery.migration", "APPLICATION_FIRST");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"/spring/dubbo.consumer.xml"});
        context.start();
        greetService = (GreetService)context.getBean("GreetService");
        GreetRequest req = GreetRequest.newBuilder().setName("Mamba").build();
        System.out.println("dubbo ref started");
//        GreetResponse greet = greetService.greet(req);
        CompletableFuture<GreetResponse> future = greetService.greetAsync(req);
        GreetResponse greet = future.get();
        System.out.println("Greeting:" + greet.getGreeting() + "!!!!!!!!!!!!");
//        new CountDownLatch(1).await();
    }

}