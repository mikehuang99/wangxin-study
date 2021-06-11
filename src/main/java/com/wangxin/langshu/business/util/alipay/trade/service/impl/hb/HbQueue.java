package com.wangxin.langshu.business.util.alipay.trade.service.impl.hb;

import com.wangxin.langshu.business.util.alipay.trade.model.hb.SysTradeInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by liuyangkly on 15/10/23.
 private List<BlockingQueue<E>> concurrentQueueList = Collections.synchronizedList(queueList);
 */
public class HbQueue {
    private static Log log = LogFactory.getLog(HbQueue.class);

    public static final int QUEUE_SIZE = 300;   // 最多同时保存300条交易记录
    private static final BlockingQueue<SysTradeInfo> queue = new ArrayBlockingQueue<SysTradeInfo>(QUEUE_SIZE);

    public synchronized static void offer(SysTradeInfo info) {
        // blockingQueue不需要考虑队列满的情况，生产者会被阻塞直到队列被消耗
        if (info != null) {
            try {
                // 使用阻塞put
                queue.put(info);
            } catch (InterruptedException e) {
                log.warn("interrupted for tradeInfo:" + info);
                e.printStackTrace();
            }
        }
    }

    public synchronized static List<SysTradeInfo> poll() {
        if (queue.isEmpty()) {
            // 如果队列为空，则直接返回
            return null;
        }

        int size = 30;
        List<SysTradeInfo> tradeInfoList = new ArrayList<SysTradeInfo>(size);
        for (int i = 0; i < size; i++) {
            // 使用非阻塞poll
            SysTradeInfo info = queue.poll();
            if (info == null) {
                break;
            }
            tradeInfoList.add(info);
        }
        return tradeInfoList;
    }
}
