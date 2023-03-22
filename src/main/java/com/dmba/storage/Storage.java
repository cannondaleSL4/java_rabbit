package com.dmba.storage;

import com.dmba.dao.UsersOrder;

/**
 * An interface for managing the storage of {@link UsersOrder} entities.
 */
public interface Storage {

    /**
     * Saves a {@link UsersOrder} entity to the storage.
     *
     * @param usersOrderEntity The UsersOrder entity to save.
     */
    void saveMessage(UsersOrder usersOrderEntity);
}
