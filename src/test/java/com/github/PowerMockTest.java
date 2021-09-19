package com.github;

import com.github.javabean.BeanIterator;
import com.github.service.IUserService;
import com.github.model.User;
import com.github.service.impl.UserServiceImpl;
import com.github.resource.EnumerationIter;
import com.github.resource.ResourceUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * @author 康盼Java开发工程师
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ResourceUtil.class})
public class PowerMockTest {

    @Mock
    private IUserService userService;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    public void getUserMulti() {
        User user = PowerMockito.mock(User.class);
        PowerMockito.when(userService.getUser()).thenReturn(user)
                .thenReturn(null).thenThrow(new RuntimeException("第三次调用异常"));
        Assert.assertNotNull(userService.getUser());
        Assert.assertNull(userService.getUser());
        try {
            userService.getUser();
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    @Test
    public void staticMethod() throws IOException {
        Enumeration<URL> enumeration = PowerMockito.mock(Enumeration.class);
        ClassLoader classLoader = PowerMockito.mock(ClassLoader.class);
        PowerMockito.when(classLoader.getResources(anyString())).thenReturn(enumeration);
        EnumerationIter<URL> enumerationIter = new EnumerationIter<>(enumeration);
        PowerMockito.mockStatic(ResourceUtil.class);
        PowerMockito.when(ResourceUtil.getResourcesIterator(anyString())).thenReturn(enumerationIter);
        EnumerationIter<URL> iter = ResourceUtil.getResourcesIterator("");
        Assert.assertEquals(iter,enumerationIter);
    }

    @Test
    public void field() throws Exception {
        BeanIterator beanIterator = PowerMockito.mock(BeanIterator.class);
        MemberModifier.field(BeanIterator.class,"beans").set(beanIterator,new ArrayList());
    }
}
