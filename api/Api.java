package api;

import service.Service;
import datasource.Datasource;

public class Api {
    public static void main(String[] args) {
        Service sv = new Service();
        Datasource ds = new Datasource();
        sv.service(args, ds);
    }
}
