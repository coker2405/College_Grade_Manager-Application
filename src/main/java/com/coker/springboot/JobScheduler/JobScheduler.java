package com.coker.springboot.JobScheduler;

import com.coker.springboot.model.User;
import com.coker.springboot.repository.UserRepo;
import com.coker.springboot.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class JobScheduler {
    @Autowired
    UserRepo userRepo;

    @Autowired
    EmailService emailService;
    @Scheduled(fixedDelay = 50000)
    public void hello(){
        log.info("Hello ");
//        emailService.testEmail();
    }
    //Giay - Phut - Gio - Ngay - Thang - Thu
    @Scheduled(cron = " 0 0 9 * * * ")
    public void greeting(){
        Calendar cal = Calendar.getInstance();

        int day = cal.get(Calendar.DATE);
        //The first month of the year in the Gregorian and Julian calendars is JANUARY which is 0
        int month = cal.get(Calendar.MONTH) + 1;

        List<User> users = userRepo.searchByDOB(day,month);

        for(User u : users){
            log.info("Happy Birthday" + u.getFirst_Name());
        }

    }

}
