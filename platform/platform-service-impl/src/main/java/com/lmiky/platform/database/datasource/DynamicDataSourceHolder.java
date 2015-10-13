package com.lmiky.platform.database.datasource;

/**
 * 数据源管理器
 *
 * @author lmiky
 * @date 2015年9月7日 下午2:02:23
 */
public class DynamicDataSourceHolder {
    private static enum DataSourceType {
        write, read;
    }

    public static final ThreadLocal<DataSourceType> holder = new ThreadLocal<>();

    /**
     * 数据源名称
     */
    public static final String DATASOURCE_WRITE = "write";
    public static final String DATASOURCE_READ = "read";

    
    /**
     * 标记为写数据源
     * @author lmiky
     * @date 2015年9月9日 下午8:57:43
     */
    public static void markWrite() {
        holder.set(DataSourceType.write);
    }
    
    /**
     * 标记为读数据源
     * @author lmiky
     * @date 2015年9月9日 下午8:57:43
     */
    public static void markRead() {
        holder.set(DataSourceType.read);
    }
    
    /**
     * 重置
     * @author lmiky
     * @date 2015年9月9日 下午8:58:01
     */
    public static void reset() {
        holder.set(null);
    }
    
    /**
     * 是否还未设置数据源
     * @author lmiky
     * @date 2015年9月9日 下午8:58:09
     * @return
     */
    public static boolean isChoiceNone() {
        return null == holder.get(); 
    }
    
    /**
     * 当前是否选择了写数据源
     * @author lmiky
     * @date 2015年9月9日 下午8:58:19
     * @return
     */
    public static boolean isChoiceWrite() {
        return DataSourceType.write == holder.get();
    }
    
    /**
     * 当前是否选择了读数据源
     * @author lmiky
     * @date 2015年9月9日 下午8:58:29
     * @return
     */
    public static boolean isChoiceRead() {
        return DataSourceType.read == holder.get();
    }
}
