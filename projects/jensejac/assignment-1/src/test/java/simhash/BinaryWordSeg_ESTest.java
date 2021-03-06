/*
 * This file was automatically generated by EvoSuite
 * Tue Apr 18 04:34:09 GMT 2017
 */

package simhash;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;
import simhash.BinaryWordSeg;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true) 
public class BinaryWordSeg_ESTest extends BinaryWordSeg_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      BinaryWordSeg binaryWordSeg0 = new BinaryWordSeg();
      // Undeclared exception!
      try { 
        binaryWordSeg0.tokens((String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         assertThrownBy("simhash.BinaryWordSeg", e);
      }
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      BinaryWordSeg binaryWordSeg0 = new BinaryWordSeg();
      List<String> list0 = binaryWordSeg0.tokens(",1rHaF$r]T^ah");
      assertEquals(13, list0.size());
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      BinaryWordSeg binaryWordSeg0 = new BinaryWordSeg();
      List<String> list0 = binaryWordSeg0.tokens("");
      assertTrue(list0.isEmpty());
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      BinaryWordSeg binaryWordSeg0 = new BinaryWordSeg();
      LinkedHashSet<String> linkedHashSet0 = new LinkedHashSet<String>();
      List<String> list0 = binaryWordSeg0.tokens(",1rHaF$r]T^ah", (Set<String>) linkedHashSet0);
      assertNull(list0);
  }
}
