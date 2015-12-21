package net.mutil.view.aystree;

/**
 * 
 * 描述 树型数据结构
 * 
 * @author Mars zhang
 * @created 2015-12-15 上午10:56:33
 */
public class AsyTreeData {
    /** 文字内容 */
    private String contentText;
    /** 在tree中的层级 */
    private int level;
    /** 元素的id */
    private String id;
    /** 父元素的id */
    private String parendId;
    /** 是否有子元素 */
    private boolean hasChildren;
    /** item是否展开 */
    private boolean isExpanded;

    /** 表示该节点没有父元素，也就是level为0的节点 */
    public static final int NO_PARENT = -1;
    /** 表示该元素位于最顶层的层级 */
    public static final int TOP_LEVEL = 0;

    /**
     * 
     * 描述
     * 
     * @author Mars zhang
     * @created 2015-12-15 上午10:58:52
     * @param contentText
     * @param level
     * @param id
     * @param parendId
     * @param hasChildren
     * @param isExpanded
     */
    public AsyTreeData(String contentText, int level, String id, String parendId, boolean hasChildren, boolean isExpanded) {
        super();
        this.contentText = contentText;
        this.level = level;
        this.id = id;
        this.parendId = parendId;
        this.hasChildren = hasChildren;
        this.isExpanded = isExpanded;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParendId() {
        return parendId;
    }

    public void setParendId(String parendId) {
        this.parendId = parendId;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean isExpanded) {
        this.isExpanded = isExpanded;
    }

}
