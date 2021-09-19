package com.github;

import com.github.collections.Entry;
import com.github.collections.HashMap;
import com.github.javabean.BeanDriverManager;
import com.github.javabean.BeanLoader;
import com.github.javabean.Beans;
import com.github.model.ResultInfo;
import com.github.service.IUserService;
import com.github.model.User;
import com.github.service.impl.ProxyRetryerServiceImpl;
import com.github.service.impl.ResultServiceImpl;
import com.github.service.impl.SaveServiceImpl;
import com.github.service.impl.UserServiceImpl;
import com.github.service.impl.VipUserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * @author 康盼Java开发工程师
 */
public class AllTest {

    protected ResultServiceImpl resultService;
    protected SaveServiceImpl saveService;
    protected ProxyRetryerServiceImpl proxyRetryer;


    @Before
    public void init() throws Exception {
        resultService = (ResultServiceImpl) Beans.getByType(ResultServiceImpl.class);
        saveService = (SaveServiceImpl) Beans.getByType(SaveServiceImpl.class);
        proxyRetryer = (ProxyRetryerServiceImpl) Beans.getByType(ProxyRetryerServiceImpl.class);
    }

    @Test
    public void getByTypeForSaveServiceImpl() {
        SaveServiceImpl saveService = (SaveServiceImpl) Beans.getByType(SaveServiceImpl.class);
        System.out.println(saveService);
    }


    @Test
    public void getByTypeForUser() {
        User user = (User) Beans.getByType(User.class);
        System.out.println(user);
    }

    @Test
    public void getByNameForUser() {
        User user = (User) Beans.getByName("user");
        System.out.println(user);
    }

    @Test
    public void getByTypeForUserServiceImpl() {
        IUserService userService = (UserServiceImpl) Beans.getByType(UserServiceImpl.class);
        System.out.println(userService.getUser());
    }

    @Test
    public void getByNameForUserServiceImpl() {
        IUserService userService = (UserServiceImpl) Beans.getByName("userServiceImpl");
        System.out.println(userService.getUser());
    }


    @Test
    public void getByTypeForVipUserServiceImpl() {
        IUserService userService = (VipUserServiceImpl) Beans.getByType(VipUserServiceImpl.class);
        System.out.println(userService.getUser());
    }

    @Test
    public void getByNameForVipUserServiceImpl() {
        IUserService userService = (VipUserServiceImpl) Beans.getByName("vipUserServiceImpl");
        System.out.println(userService.getUser());
    }

    @Test
    public void getByTypeForIUserService() {
        IUserService userService = (IUserService) Beans.getByType(IUserService.class);
        System.out.println(userService.getUser());
    }

    @Test
    public void classForNameTest() throws Exception {
        Class.forName("com.github.javabean.BeanDriverManager");
        BeanDriverManager manager = (BeanDriverManager) Beans.cache.get("beanDriverManager");
        Beans.getByType(UserServiceImpl.class);
        Beans.getByType(VipUserServiceImpl.class);
        Beans.getByType(User.class);
        manager.printAllBean();
        manager = (BeanDriverManager) Beans.cache.get("beanDriverManager");
        Assert.assertNull(manager);
    }

    @Test
    public void hashMapTest() {
        HashMap hashMap = new HashMap(16, 100);
        hashMap.put("8", "a");
        hashMap.put("9", "b");
        hashMap.put("3", "c");
        Iterator iterator = hashMap.iterator();
        while (iterator.hasNext()) {
            Entry<String, String> entry = (Entry<String, String>) iterator.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        Assert.assertEquals(hashMap.get("3"), "c");
        Assert.assertTrue(hashMap.containsKey("3"));
        hashMap.clear();
        Assert.assertTrue(hashMap.isEmpty());
        Assert.assertEquals(hashMap.size(), 0);
        Assert.assertNull(hashMap.get("3"));
    }

    @Test
    public void destroyBeanTest() throws Exception {
        User user = (User) Beans.getByName("user");
        IUserService userService = (VipUserServiceImpl) Beans.getByName("vipUserServiceImpl");
        System.out.println(userService.getUser());
        Beans.destroy();
        Beans.init("vipUserServiceImpl");
        Class.forName("com.github.javabean.BeanDriverManager");
        BeanDriverManager manager = (BeanDriverManager) Beans.cache.get("beanDriverManager");
        manager.printAllBean();
    }

    @Test
    public void loadSpringFactoriesTest() {
        Map<String, List<String>> map = BeanLoader.loadSpringFactories();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    /**
     * 对象头(Object Header):
     * 对象头分为两部分：Mark Word 与 Class Pointer(类型指针)。
     * Mark Word存储了对象的hashCode、GC信息、锁信息三部分，Class Pointer存储了指向类对象信息的指针。
     * 在32位JVM上对象头占用的大小是8字节，64位JVM则是16字节，两种类型的Mark Word 和 Class Pointer各占一半空间大小。
     * 在64位JVM上有一个压缩指针选项-XX:+UseCompressedOops，默认是开启的。
     * 开启之后Class Pointer部分就会压缩为4字节，此时对象头大小就会缩小到12字节。
     * 测试对象占多少字节
     */
    @Test
    public void memoryLayoutTest() {
        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseClass(User.class).toPrintable());
    }

    /**
     * 重试机制调用模拟
     *
     * @throws Exception
     */
    @Test
    public void retryerTest() throws Exception {
        ResultServiceImpl proxy = proxyRetryer.getProxy();
        String code = saveService.getCode(1);
        ResultInfo resultInfo = proxy.callback(code);
        System.out.println(resultInfo.getState());
        System.out.println(resultInfo.getMessage());
    }

}
