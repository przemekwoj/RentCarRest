package com.przemo.emailsender.repositories.queries;

public interface ClientQueries {
    String allClientsWithDetainedOrder = "SELECT DISTINCT client.email as email\n" +
            "FROM client\n" +
            "join carorder on client.user_id = carorder.client_id\n" +
            "join car_order_details on car_order_details.id = carorder.order_id and car_order_details.is_detained ='1'" +
            "";

    String allClientsEmails = "SELECT DISTINCT client.email as email\n" +
            "FROM client\n";

    String numberOfClientDetainedRows = "SELECT COUNT(*)\n" +
            "FROM client\n" +
            "join carorder on client.user_id = carorder.client_id\n" +
            "join car_order_details on car_order_details.id = carorder.order_id and car_order_details.is_detained ='1'" +
            "";

    String numberOfClientRecords = "SELECT COUNT(*)\n" +
            "FROM client\n";
}
