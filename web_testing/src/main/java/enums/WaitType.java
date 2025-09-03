package enums;

/**
 * Enum defining different wait types with their corresponding timeouts.
 * Used by WaitHelper to provide predefined wait configurations.
 * 
 * @author Testing Framework
 * @version 2.0.0
 * @since 1.0.0
 */
public enum WaitType {
    /**
     * Short wait (5 seconds) for quick operations
     */
    SHORT,
    
    /**
     * Medium wait (10 seconds) for standard operations
     */
    MEDIUM,
    
    /**
     * Default wait (15 seconds) for typical operations
     */
    DEFAULT,
    
    /**
     * Long wait (30 seconds) for slow operations
     */
    LONG,
    
    /**
     * Extended wait (60 seconds) for very slow operations
     */
    EXTENDED
} 