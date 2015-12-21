package net.mutil.view.aystree;

import java.util.ArrayList;
import java.util.HashMap;

import net.mutil.util.MyLayoutUtil;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * 
 * ���� ����������
 * 
 * @author Mars zhang
 * @created 2015-12-15 ����10:59:51
 */
public class AsyTreeAdapter extends BaseAdapter {
    /** ���е����ݼ��� */
    private ArrayList<AsyTreeData> allNodes;
    /** ����Ԫ�ؽ�� */
    private ArrayList<AsyTreeData> topNodes;
    /** LayoutInflater */
    private LayoutInflater inflater;
    /** item�������������� */
    private int indentionBase;
    /** context */
    protected Context mcontext;

    protected String postUrl = "";
    protected HashMap<String, String> postentityMap;
    private int itemLayoutRes;
    private int itemImageId;
    private int itemTextId;
    private int drawableid;
    private int noChilddrawableid;
    public int selectPosition = -1;
    
    private String selectColor="#FFF7B2";
    private String defaultColor="#ffffff";
    public void setSelectColor(){
        
    }
    private AsyTreeItemclick asyTreeItemclick;
    public void setAsyTreeItemclick(AsyTreeItemclick asyTreeItemclick) {
        this.asyTreeItemclick = asyTreeItemclick;
    } 
    public AsyTreeItemclick getAsyTreeItemclick() {
        return asyTreeItemclick;
    }


    public interface AsyTreeItemclick {
        void onItemClick(String itemId, String itemName);
    }

    public void setLayoutResIds(int itemLayoutRes, int itemImageId, int itemTextId, int drawableid,int noChilddrawableid) {
        this.itemLayoutRes = itemLayoutRes;
        this.itemImageId = itemImageId;
        this.itemTextId = itemTextId;
        this.drawableid = drawableid;
        this.noChilddrawableid = noChilddrawableid;
    }

    public void setIndentionBase(int indentionBase) {
        this.indentionBase = indentionBase;
    }

    public AsyTreeAdapter(ArrayList<AsyTreeData> topNodes, ArrayList<AsyTreeData> allNodes, Context context,
            String postUrl, HashMap<String, String> postentityMap) {
        super();
        indentionBase = 70;
        this.inflater = LayoutInflater.from(context);
        this.allNodes = allNodes;
        this.topNodes = topNodes;
        this.mcontext = context;
        this.postUrl = postUrl;
        this.postentityMap = postentityMap;
    }

    public ArrayList<AsyTreeData> getAllNodes() {
        return allNodes;
    }

    public ArrayList<AsyTreeData> getTopNodes() {
        return topNodes;
    }

    public void setAllNodes(ArrayList<AsyTreeData> allNodes) {
        this.allNodes = allNodes;
    }

    public void setTopNodes(ArrayList<AsyTreeData> topNodes) {
        this.topNodes = topNodes;
    }

    @Override
    public int getCount() {
        return null == topNodes ? 0 : topNodes.size();
    }

    @Override
    public Object getItem(int position) {
        return topNodes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (null == convertView) {
            holder = new ViewHolder();
            // convertView = inflater.inflate(R.layout.tree_data_item, null);
            // holder.imageView = (ImageView)
            // convertView.findViewById(R.id.tree_data_item_image);
            // holder.textView = (TextView)
            // convertView.findViewById(R.id.tree_data_item_text);
            convertView = inflater.inflate(itemLayoutRes, null);
            holder.imageView = (ImageView) convertView.findViewById(itemImageId);
            holder.textView = (TextView) convertView.findViewById(itemTextId);
            convertView.setTag(holder);// setTag�洢View�Ķ�����Ϣ
        } else {
            holder = (ViewHolder) convertView.getTag(); // �������ܻ�ȡ��ViewHolder����
        }
        convertView.setBackgroundColor(Color.parseColor(defaultColor)); 
        if(selectPosition==position){
            convertView.setBackgroundColor(Color.parseColor(selectColor));  
        }
        

        AsyTreeData itemdata = topNodes.get(position);
        // ����
        MyLayoutUtil.setMargin(holder.imageView, itemdata.getLevel() * indentionBase, holder.imageView.getPaddingTop(),
                holder.imageView.getPaddingRight(), holder.imageView.getPaddingBottom());
        if (itemdata.isHasChildren() && itemdata.isExpanded()) {
            holder.imageView.setBackgroundResource(drawableid);
            holder.textView.setText(itemdata.getContentText());
            holder.imageView.setVisibility(View.VISIBLE);
            holder.textView.setVisibility(View.VISIBLE);
        } else if (itemdata.isHasChildren() && !itemdata.isExpanded()) {
            holder.imageView.setBackgroundResource(drawableid);
            holder.textView.setText(itemdata.getContentText());
            holder.textView.setText(itemdata.getContentText());
            holder.imageView.setVisibility(View.VISIBLE);
            holder.textView.setVisibility(View.VISIBLE);
        } else if (!itemdata.isHasChildren()) {
            holder.imageView.setBackgroundResource(noChilddrawableid);
            holder.textView.setText(itemdata.getContentText());
            holder.imageView.setVisibility(View.VISIBLE);
            holder.textView.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }

}
