package com.mustabelmo.batch.archi.api;

import com.mustabelmo.batch.archi.dto.LaunchResult;
import com.mustabelmo.batch.archi.launcher.LaunchParams;

public interface AppBatchLauncher {
    LaunchResult launch(LaunchParams launchParams);
}
