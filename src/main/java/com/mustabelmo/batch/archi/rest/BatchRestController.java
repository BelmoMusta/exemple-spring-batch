package com.mustabelmo.batch.archi.rest;

import com.mustabelmo.batch.archi.api.AppBatchLauncher;
import com.mustabelmo.batch.archi.dto.LaunchResult;
import com.mustabelmo.batch.archi.launcher.LaunchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchRestController {
    @Autowired
    private AppBatchLauncher appBatchLauncher;
    @PostMapping(value = "/batch/launch", produces = "application/json")
    public LaunchResult handle(@RequestBody LaunchParams params) throws Exception {
       return appBatchLauncher.launch(params);
    }

}