package com.example.gauravnivsarkar.projectutils;

import android.text.TextUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(TextUtils.class)
public class ExampleUnitTest {

    @Before
    public void setup() {
        PowerMockito.mockStatic(TextUtils.class);
        PowerMockito.when(TextUtils.isEmpty(any(CharSequence.class))).thenAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                CharSequence a = (CharSequence) invocation.getArguments()[0];
                return !(a != null && a.length() > 0);
            }
        });
//        PowerMockito.when(android.util.Patterns.EMAIL_ADDRESS.matcher(any(CharSequence.class))).
//                then(new Answer<Boolean>() {
//                    @Override
//                    public Boolean answer(InvocationOnMock invocation) throws Throwable {
//                      boolean  hitEnd = false;
//                      boolean  requireEnd = false;
//                        int from= 0;
//                       int oldLast = 0;
//                        for (int i = 0; i < groups.length; i++)
//                            groups[i] = -1;
//                        acceptMode = anchor;
//                        boolean result = parentPattern.matchRoot.match(this, from, text);
//                        if (!result)
//                            this.first = -1;
//                        this.oldLast = this.last;
//                        return result;
//                    }
//                });
    }
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testPasswordLogic() throws Exception{
        assertEquals(Utils.isValidPassword("password",3,false,null),true);
        assertEquals(Utils.isValidPassword("pass",3,false,null),true);
        assertEquals(Utils.isValidPassword("ps",3,false,null),false);
    }

    @Test
    public void testEmailLogic(){
        assertEquals(Utils.isValidEmail("a@b.c"),true);
        assertEquals(Utils.isValidEmail("a@bc"),false);
        assertEquals(Utils.isValidEmail("ab.c"),false);
        assertEquals(Utils.isValidEmail("abc"),false);
    }
}