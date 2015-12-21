package net.mutil.view.aystree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.mutil.util.BaseModel;
import net.mutil.util.HttpUtil;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 
 * 描述
 * 
 * @author Mars zhang
 * @created 2015-12-15 上午11:10:49
 */
public class AsyTreeItemOnClick implements OnItemClickListener {

    private AsyTreeAdapter treeAdapter;
    private HashMap<String, AsyTreeData> loadedParentDadte = new HashMap<String, AsyTreeData>();
    public static final int DATA_LOADSUCCESS = 1;// 数据加载成功
    public static final int DATA_LOADSUCCESS_NODATA = 2;// 数据加载成功但是没数据
    private AsyTreeData currentAsyTreeData;
    private int currentposition = 0;

    public AsyTreeItemOnClick(AsyTreeAdapter treeAdapter) {
        super();
        this.treeAdapter = treeAdapter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        treeAdapter.selectPosition = position;
        final AsyTreeData itemData = (AsyTreeData) treeAdapter.getItem(position);
        if (null != treeAdapter.getAsyTreeItemclick()) {
            treeAdapter.getAsyTreeItemclick().onItemClick(itemData.getId(), itemData.getContentText());
        }
        currentAsyTreeData = (AsyTreeData) treeAdapter.getItem(position);
        currentposition = position;
        if (!itemData.isHasChildren()) {// 无子节点
            treeAdapter.notifyDataSetChanged();
            return;
        }
        List<AsyTreeData> topTreeDatas = treeAdapter.getTopNodes();
        List<AsyTreeData> allTreeDatas = treeAdapter.getAllNodes();
        if (itemData.isExpanded()) {// 张开的
            itemData.setExpanded(false);
            List<AsyTreeData> removeTreeData = new ArrayList<AsyTreeData>();
            for (int i = position + 1; i < topTreeDatas.size(); i++) {
                AsyTreeData indexTreeData = topTreeDatas.get(i);
                if (!(indexTreeData.getLevel() > itemData.getLevel()))
                    break;
                removeTreeData.add(indexTreeData);
            }
            topTreeDatas.removeAll(removeTreeData);// 删除不要的
            treeAdapter.notifyDataSetChanged();
        } else {// 关闭的
            itemData.setExpanded(true);
            final String currentid = itemData.getId();
            if (!loadedParentDadte.containsKey(currentid)) {// 判断是否已经加载过了
                loadedParentDadte.put(itemData.getId(), itemData);
                final Message message = new Message();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            treeAdapter.postentityMap.put("currentid", currentid);
                            String resultStr = HttpUtil.connServerForResultPost(treeAdapter.postUrl,
                                    treeAdapter.postentityMap);
                            List<BaseModel> baseModels = HttpUtil.getObjsInfo(resultStr);
                            if (null == baseModels || baseModels.size() == 0) { // 没有数据就返回
                                message.what = DATA_LOADSUCCESS_NODATA;
                                uiHandler.sendMessage(message);
                                return;
                            }
                            for (int i = 0; i < baseModels.size(); i++) {
                                BaseModel mbaseModel = baseModels.get(i);
                                boolean hasChildren = true;
                                String hasChildrenStr = mbaseModel.getStr("haschildren");
                                if (null != hasChildrenStr && hasChildrenStr.equals("false")) {
                                    hasChildren = false;
                                }
                                AsyTreeData asyTreeData = new AsyTreeData(mbaseModel.getStr("name"),
                                        itemData.getLevel() + 1, mbaseModel.getStr("id"), itemData.getId(),
                                        hasChildren, false);
                                treeAdapter.getAllNodes().add(asyTreeData);
                            }
                            message.what = DATA_LOADSUCCESS; 
                            uiHandler.sendMessage(message);
                        } catch (ClientProtocolException e) {
                            System.out.println("Mutil:" + e.getMessage());
                        } catch (IOException e) {
                            System.out.println("Mutil:" + e.getMessage());
                        } catch (JSONException e) {
                            System.out.println("Mutil:" + e.getMessage());
                        }
                    }
                }).start();
            } else {// 加载过了
                int i = 1;
                for (AsyTreeData treeData : allTreeDatas) {
                    if (treeData.getParendId() == itemData.getId()) {
                        treeData.setExpanded(false);
                        topTreeDatas.add(position + i, treeData);
                        i++;
                    }
                }
                treeAdapter.notifyDataSetChanged();
            }
        }

    }

    /**
     * 异步树数据加载UI刷新
     */
    public Handler uiHandler = new Handler() {
        public void handleMessage(Message msg) {
            List<AsyTreeData> topTreeDatas = treeAdapter.getTopNodes();
            List<AsyTreeData> allTreeDatas = treeAdapter.getAllNodes();
            switch (msg.what) {
                case DATA_LOADSUCCESS:
                    int i = 1;
                    for (AsyTreeData treeData : allTreeDatas) {
                        if (treeData.getParendId() == currentAsyTreeData.getId()) {
                            treeData.setExpanded(false);
                            topTreeDatas.add(currentposition + i, treeData);
                            i++;
                        }
                    }
                    treeAdapter.notifyDataSetChanged();
                    break;
                case DATA_LOADSUCCESS_NODATA:
                    currentAsyTreeData.setHasChildren(false);
                    treeAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        };
    };
}
