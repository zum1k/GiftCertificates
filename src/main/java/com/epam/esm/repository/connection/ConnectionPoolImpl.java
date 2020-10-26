package com.epam.esm.repository.connection;

import com.epam.esm.repository.ConnectionPool;

import java.sql.Connection;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class ConnectionPoolImpl implements ConnectionPool {
    private static int INITIAL_POOL_SIZE = 10;
    private Queue<Connection> connectionPool;
    private Queue<Connection> usedConnections;


    @Override
    public Connection getConnection() {
        Connection connection = connectionPool.remove();
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }
}
