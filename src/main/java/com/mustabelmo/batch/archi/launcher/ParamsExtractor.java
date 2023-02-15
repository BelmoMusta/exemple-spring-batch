package com.mustabelmo.batch.archi.launcher;

import org.springframework.stereotype.Component;

@Component
public class ParamsExtractor {

    public LaunchParams createLaunchParams(String[] arguments) {
        LaunchParams params = new LaunchParams();
        params.setFromCommandLine(true);
        for (String argument : arguments) {
            String[] nameValue = argument.split("[=:]");
            if (nameValue.length > 1) {
                String name = nameValue[0];
                String value = nameValue[1];
                params.put(name, value);
            }
        }
        return params;
    }
}
