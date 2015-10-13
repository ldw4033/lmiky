package com.lmiky.platform.database.datasource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 *
 * @author lmiky
 * @date 2015年9月7日 下午2:01:31
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private Object writeDataSource;
    private List<Object> readDataSources;
    private int readDataSourceSize = 0;

    private AtomicInteger readIndex = new AtomicInteger(0);

    /**
     * 数据源键名
     */
    private static final String DATASOURCE_KEY_WRITE = "write";
    private static final String DATASOURCE_KEY_READ = "read";

    /* (non-Javadoc)
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
            for(int i=0; i<readDataSources.size(); i++) {
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
        if(DynamicDataSourceHolder.isChoiceNone() || DynamicDataSourceHolder.isChoiceWrite() || readDataSourceSize == 0) {
            return DATASOURCE_KEY_WRITE;
        }
        int index = readIndex.incrementAndGet() % readDataSourceSize;
        return DATASOURCE_KEY_READ + index;
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
