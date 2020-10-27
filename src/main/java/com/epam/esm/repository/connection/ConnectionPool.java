package com.epam.esm.repository.connection;

import javax.sql.DataSource;
import java.util.Queue;

public class ConnectionPool {
    private static int INITIAL_POOL_SIZE = 10;
    private Queue<DataSource> connectionPool;
    private Queue<DataSource> usedConnections;

    public DataSource getConnection() {
        DataSource connection = connectionPool.remove();
        usedConnections.add(connection);
        return connection;
    }

    public boolean releaseConnection(DataSource connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }
}
