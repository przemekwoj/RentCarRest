package com.przemo.rentcar.RabbitQueue;

public enum QueueType {
    NOTIFY_ALL("notifyAllRoutingKey"),
    NOTIFY_ADMINISTRATION("notifyAdministrationRoutingKey"),
    DETAINED_ORDER("detainedOrderRoutingKey");

    private String queueKey;

    QueueType(String queueKey) {
        this.queueKey = queueKey;
    }

    public String getQueueKey(){
        return queueKey;
    }
}
