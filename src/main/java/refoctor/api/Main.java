package refoctor.api;

public class Main {
    public static void main(String[] args) {
        try {
            if(args.length < 1) {
                throw new RuntimeException("引数が足りません");
            }

            String methodType = args[0];

            if("input".equals(methodType)) {
                if(args.length < 4) {
                    throw new RuntimeException("引数が足りません");
                }

                DayWorkMinutesApi.inputApi(args);

            } else if("total".equals(methodType)) {

                if(args.length < 2) {
                    throw new RuntimeException("引数が足りません");
                }

                TotalWorkMinutesApi.totalApi(args);

            } else {
                throw new RuntimeException("methodTypeが不正です");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}