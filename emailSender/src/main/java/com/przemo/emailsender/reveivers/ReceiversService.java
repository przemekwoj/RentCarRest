package com.przemo.emailsender.reveivers;

import java.util.List;

public interface ReceiversService {
    int amountOfBatchFetch(QueueType queueType);

    List<String> getPartOfReceivers(QueueType queueType, int index);
}
