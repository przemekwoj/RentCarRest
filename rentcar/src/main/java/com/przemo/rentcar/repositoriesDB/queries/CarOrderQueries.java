package com.przemo.rentcar.repositoriesDB.queries;

public interface CarOrderQueries {
     String allOrdersInfo = "select \n" +
            "carorder.order_id as orderId, \n" +
            "client.email as clientName, \n" +
            "administration.email as administrationName,\n" +
            "car_order_details.date_of_rental as dateOfRental,\n" +
            "car_order_details.date_of_return as dateOfReturn,\n" +
            "brand.brand_name as carBrandName\n" +
            "from carorder\n" +
            "join client on carorder.client_id = client.user_id\n" +
            "join administration on carorder.stuff_id = administration.user_id\n" +
            "join car_order_details on carorder.order_id = car_order_details.id\n" +
            "join car on car.car_id = car_order_details.car_id\n" +
            "join brand on brand.brand_id = car.brand_id";

     String setDetainedOrders = "update CarOrderDetails cod set cod.detained = true";

}
