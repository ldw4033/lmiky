package com.lmiky.platform.service.exception;
/**
 * 业务异常
 * @author lmiky
 * @date 2013-4-15
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 5318767125657099881L;

    public ServiceException() {
		super();
	}

	/**
	 * @param message
	 */
	public ServiceException(String message) {
		super(message);
	}
}
