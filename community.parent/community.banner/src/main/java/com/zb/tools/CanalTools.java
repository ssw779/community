package com.zb.tools;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.common.utils.AddressUtils;
import com.alibaba.otter.canal.protocol.CanalEntry.*;
import com.alibaba.otter.canal.protocol.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.util.*;

@Component
public class CanalTools {
    //添加
    public void execution() {
        // 创建链接
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(AddressUtils.getHostIp(),
                11111), "example", "", "");
        int batchSize = 1000;
        int emptyCount = 0;
        try {
            connector.connect();
            connector.subscribe(".*\\..*");
            connector.rollback();
            int totalEmtryCount = 1200;
            while (emptyCount < totalEmtryCount) {
                Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
                long batchId = message.getId();
                int size = message.getEntries().size();
                if (batchId == -1 || size == 0) {
                    emptyCount++;
//                    System.out.println("empty count : " + emptyCount);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    emptyCount = 0;
                    // System.out.printf("message[batchId=%s,size=%s] \n", batchId, size);
                    printEntry(message.getEntries());
                }

                connector.ack(batchId); // 提交确认
                // connector.rollback(batchId); // 处理失败, 回滚数据
            }

            System.out.println("empty too many times, exit");
        } finally {
            connector.disconnect();
        }
    }

    private void printEntry(List<Entry> entrys) {
        for (Entry entry : entrys) {
            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                continue;
            }

            RowChange rowChage = null;
            try {
                rowChage = RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                        e);
            }

            EventType eventType = rowChage.getEventType();
            System.out.println(String.format("================> binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                    eventType));

            for (RowData rowData : rowChage.getRowDatasList()) {
                if (eventType == EventType.DELETE) {
                    printColumn(rowData.getBeforeColumnsList());
                } else if (eventType == EventType.INSERT) {
                    printColumn(rowData.getAfterColumnsList());
                } else {
                    //是这个表“tb_content”才能执行
                    if (entry.getHeader().getTableName().equals("tb_content")) {
                        updateRedisData(rowData.getAfterColumnsList());
                    }
                    if (entry.getHeader().getTableName().equals("store")) {

                        updateRecommendRedisData(rowData.getAfterColumnsList());
                    }

                }
            }
        }
    }

    @Autowired
    private RestTemplate restTemplate;

    private void updateRedisData(List<Column> columns) {
        Set<Integer> categoryId = new HashSet<>();

        for (Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + "  update=" + column.getUpdated());
            if (column.getName().equals("category_id")) {
                categoryId.add(Integer.parseInt(column.getValue()));
            }
            //获取到编号然后一次调用nginx的请求，把id传进去 lua
            for (Integer integer : categoryId) {
                String url = "http://localhost:9000/bannerUpdate?id=" + integer;
                String result = restTemplate.getForObject(url, String.class);
                System.out.println("远程调用nginx中的接口程序:" + result);
            }


        }

    }

    /**
     * 同步修改店铺信息 调用lua文件
     *
     * @param columns
     */
    public void updateRecommendRedisData(List<Column> columns) {
        String[] arry = new String[2];

        for (Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + "  update=" + column.getUpdated());

            if (column.getName().equals("storeType")) {
                arry[0] = column.getValue();
            }
            if (column.getName().equals("shopParenType")) {

                arry[1] = column.getValue();
            }


        }
        System.out.println(arry[0] + "\t" + arry[1]);
        String url = "http://localhost:9000/recUpdate?storeType=" + arry[0] + "&shopParenType=" + arry[1];
        String result = restTemplate.getForObject(url, String.class);
        System.out.println("远程调用nginx中的接口程序:" + result);

    }

    private void printColumn(List<Column> columns) {
        for (Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + "  update=" + column.getUpdated());
        }
    }
}
