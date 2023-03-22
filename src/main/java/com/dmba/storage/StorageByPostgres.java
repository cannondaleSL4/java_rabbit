package com.dmba.storage;

import com.dmba.dao.UsersOrder;
import com.dmba.repository.UsersOrderRepository;
import org.hibernate.exception.LockAcquisitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A {@link Storage} implementation that manages the storage of {@link UsersOrder} entities
 * using PostgreSQL as the database.
 */
@Service
@Transactional
public class StorageByPostgres implements Storage {

    @Value("${spring.rabbitmq.batch.size}")
    public Integer batchSize;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UsersOrderRepository usersOrderRepository;

    private Set<UsersOrder> messageSet = ConcurrentHashMap.newKeySet();

    /**
     * Saves a {@link UsersOrder} entity to the storage if the batch size is reached.
     *
     * @param usersOrderEntity The UsersOrder entity to save.
     */
    @Override
    public void saveMessage(UsersOrder usersOrderEntity) {
        messageSet.add(usersOrderEntity);
        if (messageSet.size() >= batchSize) {
            saveMessages();
        }
    }

    /**
     * Saves messages to the storage concurrently and retries on lock acquisition failure.
     * The method is synchronized to ensure thread safety during concurrent requests.
     */
    @Retryable(value = { LockAcquisitionException.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public void saveMessages() {
        if (!messageSet.isEmpty()) {
            synchronized (this) {
                for (UsersOrder usersOrder : messageSet) {
                    // Check if the record exists in the database
                    UsersOrder existing = usersOrderRepository.findByUuidAndTimeStamp(usersOrder.getUuid(), usersOrder.getTimeStamp());
                    if (existing == null) {
                        usersOrderRepository.save(usersOrder);
                    }
                }
                messageSet.clear();
            }
        }
    }
}
