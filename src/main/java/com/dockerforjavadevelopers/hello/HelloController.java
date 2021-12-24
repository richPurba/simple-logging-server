package com.dockerforjavadevelopers.hello;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Random;

@RestController
public class HelloController {
    private static long start = 0l;
    private static long elapsed = 0l;

    @RequestMapping(path = "/log", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String[] index() {
        if(start == 0l){
            start = System.currentTimeMillis();
            elapsed = System.currentTimeMillis();
        }
        String[] result = returningRandomLogs();
        elapsed = System.currentTimeMillis();
        return  result;
    }

    private String[] returningRandomLogs() {
        if((elapsed - start) >= 2*1000){// NOTE: this is 2sec. Change this to other sec if you like
            return new String[]{""};
        }
        Random random = new Random();
        int maxCurrentBatch = random.nextInt(100);//NOTE change to 1 Mili for more arrays

        String[] result = new String[maxCurrentBatch];
        for (int i = 0; i < maxCurrentBatch; i++) {

            int next = random.nextInt(4);

            if (next == 0) {
                result[i] = String.format(
                        "%s ERROR An error is usually an exception that has been caught and not handled.",
                        LocalDateTime.now().toString());
            } else if (next == 1) {
                result[i] = String.format(
                        "%s INFO This is less important than debug log and is often used to provide context in the current task.",
                        LocalDateTime.now().toString());
            } else if (next == 2) {
                result[i] = String.format(
                        "%s WARN A warning that should be ignored is usually at this level and should be actionable.",
                        LocalDateTime.now().toString());
            } else {
                result[i] = String.format("%s DEBUG This is a debug log that shows a log that can be ignored.",
                        LocalDateTime.now().toString());
            }
        }
        System.out.println("log number is: " + result.length);
        return result;
    }

}
