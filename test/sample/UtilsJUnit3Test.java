/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample;

import java.util.concurrent.TimeoutException;
import junit.framework.TestCase;

/**
 *
 * @author bardakov
 */
public class UtilsJUnit3Test extends TestCase {
    
    public UtilsJUnit3Test(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.out.println("* UtilsJUnit3Test: setUp() method");
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        System.out.println("* UtilsJUnit3Test: tearDown() method");
    }

    /**
     * Тестирование с помощью простого подтверждения
     */
    public void testHelloWorld(){
        System.out.println("* UtilsJUnit3Test: test method 1 - testHelloWorld()");
        assertEquals("Hello, world!", Utils.concatWords("Hello", ", ", "world","!"));
    }

    /**
     * Тестирование с использованием тайм-аута
     * @throws InterruptedException
     * @throws TimeoutException 
     */
    public void testWithTimeout() throws InterruptedException, TimeoutException{
        System.out.println("* UtilsJUnit3Test: test method 2 - testWithTimeout()");
        final int factorialOf = 1 + (int)(30000 * Math.random());
        System.out.println("computing " + factorialOf + "!");
        
        Thread testThread = new Thread(){
            public void run(){
                System.out.println(factorialOf + "! = " + Utils.computeFactorial(factorialOf));
            }
        };
        testThread.start();
        Thread.sleep(100);
        testThread.interrupt();
        
        if(testThread.isInterrupted()){
            throw new TimeoutException("The test took too long to complete");
        }
    }
    
    /**
     * тест на ожидаемое исключение
     */
    public void testExpectedException(){
        System.out.println("* UtilsJUnit3Test: test method 3 - testExpectedException()");
        try{
            final int factorialOf = -5;
            System.out.println(factorialOf + "! = " + Utils.computeFactorial(factorialOf));
            fail("IllegalArgumentException was expected");
        }catch(IllegalArgumentException ex){
            
        }
    }
    
    /**
     * временное отключение теста
     * @throws Exception 
     */
    public void DISABLED_testTemporarilyDisabled()throws Exception{
        System.out.println("* UtilsJUnit3Test: test method 4 - checkExpectedException()");
        assertEquals("Malm\u00f6", Utils.normalizeWord("Malmo\u0308"));
    }
    
}
