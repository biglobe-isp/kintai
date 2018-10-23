package api;

public class ArgsChanger {

    public String[] changeArgs(String[] args) {

        String[] tmpArgs = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            if(args[i].startsWith("-date:")) {
                tmpArgs[0] = args[i];
            }
            if(args[i].startsWith("-start:")) {
                tmpArgs[1] = args[i];
            }
            if(args[i].startsWith("-end:")) {
                tmpArgs[2] = args[i];
            }
        }
        return tmpArgs;
    }
}
