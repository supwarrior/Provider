package com.github.model;


import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 重试
 *
 * @author 康盼Java开发工程师
 */
public abstract class Retryer {

    private ThreadLocal<ResultInfo> resultInfo = new ThreadLocal<>();

    private AtomicBoolean initFlag = new AtomicBoolean(false);

    private AtomicInteger maxCount = new AtomicInteger(0);

    private AtomicInteger tryCount = new AtomicInteger(0);

    /**
     * 失败场景
     */
    private final String E = "E";

    /**
     * 成功场景
     */
    private final String S = "S";

    /**
     * 进处理中场景
     */
    private final String V = "V";

    /**
     * loop
     *
     * @return ResultInfo
     * @throws Exception
     */
    public abstract ResultInfo loop() throws Exception;

    public void setBaseInfo(ResultInfo resultInfo, int count) {
        this.maxCount.set(count);
        this.initFlag.set(true);
        this.resultInfo.set(resultInfo);
    }

    public ResultInfo doRetryer() throws Exception {
        if (initFlag.get()) {
            ResultInfo resultInfo = this.resultInfo.get();
            String state = resultInfo.getState();
            if (S.equals(state)) {
                return resultInfo;
            } else if (E.equals(state)) {
                throw new RuntimeException(resultInfo.getMessage());
            } else if (V.equals(state)) {
                while (tryCount.incrementAndGet() < maxCount.get()) {
                    this.resultInfo.set(loop());
                    return doRetryer();
                }
            }
        }
        throw new RuntimeException("超过重试次数...");
    }
}
