package com.herestt.common.nio;

import java.nio.ByteOrder;
import java.nio.channels.FileChannel;

/**
 * A byte channel whose I/O operations are performed regarding a {@link ByteOrder}.
 * 
 * @author Herestt
 *
 * @param <E> - the {@link FileChannel} class implementing the interface.
 */
public interface OrderSensitiveChannel<E extends FileChannel>{

	/**
	 * The order used for the next I/O operations.
	 * 
	 * @param order - the order to apply.
	 * @return the current {@link FileChannel} instance.
	 */
	public E order(ByteOrder order);
	
	/**
	 * Gets the used {@link ByteOrder}. 
	 * 
	 * @return the used byte order.
	 */
	public ByteOrder order();
}
