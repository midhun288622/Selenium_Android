package com.scs.exceptions;

/**
 * Customized exception for handling exceptions related to
 *         configuration in the framework.
 * @author MXC0RO7 
 */
@SuppressWarnings("serial")
public class ConfigurationException extends RuntimeException {

    public ConfigurationException(String message) {
        super(message);
    }
}
