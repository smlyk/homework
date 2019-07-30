package com.smlyk.controller;

import com.smlyk.CuratorZkTemplate;
import com.smlyk.config.CustomerProperties;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.utils.CloseableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yekai
 */
@Service
public class ZkLeaderService {

    @Autowired
    private CuratorZkTemplate curatorZkTemplate;

    @Autowired
    private CustomerProperties customerProperties;


    /**
     * Leader Latch
     * 随机从候选着中选出一台作为leader，选中之后除非调用close()释放leadship，
     * 否则其他的后选择无法成为leader。其中spark使用的就是这种方法。
     */
    public synchronized void leaderLatch() throws Exception {

        List<CuratorFramework> clients = new ArrayList<>();
        List<LeaderLatch> leaders = new ArrayList<>();

        for (int i = 1; i<= customerProperties.getClientSize(); i++){

            clients.add(curatorZkTemplate.curatorFramework);

            LeaderLatch leaderLatch = new LeaderLatch(curatorZkTemplate.curatorFramework, customerProperties.getLeaderLatchPath(), "Client #" + i);

            leaderLatch.addListener(new LeaderLatchListener() {
                @Override
                public void isLeader() {
                    System.out.println(leaderLatch.getId() +  ":I am leader. I will do something!");
                    try {
                        Thread.sleep(2000);
                        leaderLatch.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void notLeader() {
                    System.out.println(leaderLatch.getId() +  ":I am not leader. I will do nothing!");
                }
            });
            leaderLatch.start();
            leaders.add(leaderLatch);
        }


        Thread.sleep(Integer.MAX_VALUE);

        for(LeaderLatch leader:leaders){
            CloseableUtils.closeQuietly(leader);
        }

        for(CuratorFramework client:clients){
            CloseableUtils.closeQuietly(client);
        }
    }



    /**
     * Leader Election
     * 通过LeaderSelectorListener可以对领导权进行控制， 在适当的时候释放领导权，这样每个节点都有可能获得领导权。
     * 而LeaderLatch则一直持有leadership， 除非调用close方法，否则它不会释放领导权。
     */
    public synchronized void leaderElection() throws InterruptedException {

        List<CuratorFramework> clients = new ArrayList<>();
        List<LeaderSelector> selectors = new ArrayList<>();

        for (int i = 1; i <= customerProperties.getClientSize(); i++) {

            clients.add(curatorZkTemplate.curatorFramework);

            int finalI = i;
            LeaderSelector leaderSelector = new LeaderSelector(curatorZkTemplate.curatorFramework, customerProperties.getLeaderElectionPath(), new LeaderSelectorListener() {
                @Override
                public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                    System.out.println("Client#" + finalI + ":I am leader.");

                    Thread.sleep(5000);
                }

                @Override
                public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                }
            });
            leaderSelector.autoRequeue();
            leaderSelector.start();
            selectors.add(leaderSelector);
        }

        Thread.sleep(Integer.MAX_VALUE);

        for (CuratorFramework client : clients) {
            CloseableUtils.closeQuietly(client);
        }
        for (LeaderSelector selector : selectors) {
            CloseableUtils.closeQuietly(selector);
        }
    }

}
