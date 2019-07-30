package com.smlyk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yekai
 */
@RestController
@RequestMapping("zkleader")
public class ZkLeaderController {

    @Autowired
    private ZkLeaderService zkLeaderService;

    @GetMapping("leaderLatch")
    public String leaderLatch(){

        try {
            zkLeaderService.leaderLatch();
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }

        return "Success";
    }

    @GetMapping("leaderElection")
    public String leaderElection(){

        try {
            zkLeaderService.leaderElection();
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
        return "Success";
    }

}
