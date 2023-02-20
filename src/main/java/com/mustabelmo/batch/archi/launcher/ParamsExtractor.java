package com.mustabelmo.batch.archi.launcher;

public class ParamsExtractor {

    public static LaunchParams createLaunchParams(String[] arguments) {
        LaunchParams params = new LaunchParams();
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
