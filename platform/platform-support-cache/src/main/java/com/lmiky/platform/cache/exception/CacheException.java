package com.lmiky.platform.cache.exception;
/**
 * 缓存异常
 * @author lmiky
 * @date 2013-4-23
 */
public class CacheException extends RuntimeException {
    private static final long serialVersionUID = 6586809315285846429L;

    /**
	 *
	 */
	public CacheException(){
		super();
	}

	/**
	 * @param message
	 */
	public CacheException(String message) {
		super(message);
	}
}
