package org.w4a.tasks;

public interface TaskInterface {

	/*
	 * Can you do this Use Case?
	 */
	
	void doPreConditions() throws Exception;
	
	/*
	 * Has the event/state for this Use Case occured?
	 */
	
	boolean doUseCaseTrigger() throws Exception;
	
	/*
	 * Do the Use Case assuming perfect circumstances ... 
	 */
	
	void doPrepareUseCase() throws Exception;
	void doWishCase() throws Exception;
	void doCleanupUseCase() throws Exception;
	
	/*
	 * Ensure that the Use Case executed successfully ...  
	 */
	
	void doPostConditions() throws Exception;
	
	/*
	 * Do alternative case in the case of a different state ... 
	 */
	
	void doAlternateCase() throws Exception;
	
	/*
	 * Do exception case in the case of an exception ... 
	 */
	
	void doExceptionCase() throws Exception;
	
}
