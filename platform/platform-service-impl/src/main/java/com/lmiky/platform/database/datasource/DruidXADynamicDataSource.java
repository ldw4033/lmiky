package com.lmiky.platform.database.datasource;

import com.alibaba.druid.pool.xa.DruidXADataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.XAConnection;
import javax.sql.XADataSource;

/**
 * Druid动态事务数据源
 *
 * @author lmiky
 * @date 2015年9月7日 下午2:01:31
 */
public class DruidXADynamicDataSource extends AbstractRoutingDataSource implements XADataSource {

    private Object writeDataSource;
    private List<Object> readDataSources;
    private int readDataSourceSize = 0;

    private AtomicInteger readIndex = new AtomicInteger(0);

    /**
     * 数据源键名
     */
    private static final String DATASOURCE_KEY_WRITE = "write";
    private static final String DATASOURCE_KEY_READ = "read";

    /*
     * (non-Javadoc)
     * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() {
        if (this.writeDataSource == null) {
            throw new IllegalArgumentException("Property 'writeDataSource' is required");
        }
        setDefaultTargetDataSource(writeDataSource);
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DATASOURCE_KEY_WRITE, writeDataSource);
        if (this.readDataSources == null) {
            readDataSourceSize = 0;
        } else {
            for (int i = 0; i < readDataSources.size(); i++) {
                targetDataSources.put(DATASOURCE_KEY_READ + i, readDataSources.get(i));
            }
            readDataSourceSize = readDataSources.size();
        }
        setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineCurrentLookupKey()
     */
    @Override
    protected Object determineCurrentLookupKey() {
        if (DynamicDataSourceHolder.isChoiceNone() || DynamicDataSourceHolder.isChoiceWrite()
                || readDataSourceSize == 0) {
            System.out.println("=====================" + DATASOURCE_KEY_WRITE);
            return DATASOURCE_KEY_WRITE;
        }
        int index = readIndex.incrementAndGet() % readDataSourceSize;
        System.out.println("=====================" + DATASOURCE_KEY_READ + index);
        return DATASOURCE_KEY_READ + index;
    }

    /* (non-Javadoc)
     * @see javax.sql.XADataSource#getXAConnection()
     */
    @Override
    public XAConnection getXAConnection() throws SQLException {
        DruidXADataSource druidXADataSource = (DruidXADataSource)determineTargetDataSource();
        return druidXADataSource.getXAConnection();
    }

    /* (non-Javadoc)
     * @see javax.sql.XADataSource#getXAConnection(java.lang.String, java.lang.String)
     */
    @Override
    public XAConnection getXAConnection(String user, String password) throws SQLException {
        DruidXADataSource druidXADataSource = (DruidXADataSource)determineTargetDataSource();
        return druidXADataSource.getXAConnection(user, password);
    }

    /**
     * @return the writeDataSource
     */
    public Object getWriteDataSource() {
        return writeDataSource;
    }

    /**
     * @param writeDataSource the writeDataSource to set
     */
    public void setWriteDataSource(Object writeDataSource) {
        this.writeDataSource = writeDataSource;
    }

    /**
     * @return the readDataSources
     */
    public List<Object> getReadDataSources() {
        return readDataSources;
    }

    /**
     * @param readDataSources the readDataSources to set
     */
    public void setReadDataSources(List<Object> readDataSources) {
        this.readDataSources = readDataSources;
    }

}
