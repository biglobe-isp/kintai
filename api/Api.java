package api;

import service.Service;

public class Api {
    public static void main(String[] args) {
        Service sv = new Service();
        sv.service(args);
    }
}
