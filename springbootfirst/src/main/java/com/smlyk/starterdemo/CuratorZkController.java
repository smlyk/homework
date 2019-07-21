package com.smlyk.starterdemo;

import com.smlyk.CuratorZkTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yekai
 */
@RestController
public class CuratorZkController {

    @Autowired
    private CuratorZkTemplate curatorZkTemplate;

    @GetMapping("create")
    public String create(@RequestParam String path, @RequestParam String data){
        try {
            curatorZkTemplate.create(path,data);
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }

        return "Success";
    }

    @GetMapping("update")
    public String update(@RequestParam String path, @RequestParam String data){
        try {
            curatorZkTemplate.update(path, data);
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
        return "success";
    }

    @GetMapping("delete")
    public String delete(@RequestParam String path){
        try {
            curatorZkTemplate.delete(path);
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
        return "success";
    }



}
