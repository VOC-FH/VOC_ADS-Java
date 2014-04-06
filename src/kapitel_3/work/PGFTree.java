package kapitel_3.work;

import kapitel_3.vl.IComparator;
import kapitel_3.vl.IKey;

public class PGFTree {
    Tree tree = null;
    
    private static final String pgfHeader = "\\begin{tikzpicture}\n" 
            + "    \\tikzset{\n"
            + "        every node/.style={\n" 
            + "            inner sep=0,\n"
            + "            outer sep=0,\n"
            + "            minimum width=12pt,\n"
            + "            minimum height=12pt,\n"
            + "            draw,\n"
            + "            top color=white,\n"
            + "            bottom color=yellow!20,\n"
            + "            font=\\scriptsize,\n" 
            + "            circle\n"
            + "        },\n" 
            + "        blank/.style={\n"
            + "            draw=none\n" 
            + "        },\n"
            + "        normal line/.style={\n"
            + "            draw=black,\n"
            + "        },\n"
            + "        normal node/.style={\n"
            + "            draw=black,\n"
            + "            solid,\n"
            + "            top color=white,\n"
            + "            bottom color=yellow!20,\n"
            + "        },\n" 
            + "        inserted node/.style={\n"
            + "            draw=red,\n"
            + "            top color=white,\n"
            + "            bottom color=red!20\n" 
            + "        },\n"
            + "        removed node/.style={\n" 
            + "            draw=blue,\n"
            + "            bottom color=blue!20,\n"
            + "            dash pattern=on 3pt off 3pt\n" 
            + "        },\n"
            + "        level distance=0.75cm,\n" 
            + "        level/.style={\n"
            + "            sibling distance=3cm/(2^(#1-1))\n" 
            + "        },\n"
            + "        edge from parent/.style={\n" 
            + "            draw,\n"
            + "            edge from parent path={\n"
            + "                (\\tikzparentnode) -- (\\tikzchildnode)\n"
            + "            }\n" 
            + "        },\n"
            + "    }\n";
    
    private String defaultSubTreeFormat = "";
    private String defaultNodeFormat = "";
    private String defaultChildrenFormat = "";
    private String defaultChildFormat = "";
    private String defaultEdgeFromParentFormat = "";
    
    public class PGFProxy {
        private String subTreeFormat = defaultSubTreeFormat;
        private String nodeFormat = defaultNodeFormat;
        private String childrenFormat = defaultChildrenFormat;
        private String childFormat = defaultChildFormat;
        private String edgeFromParentFormat = defaultEdgeFromParentFormat;

        protected Object data = null;
        
        public PGFProxy(Object data) {
            this.data = data;
        }
        
        public void setSubTreeFormat(String format) {
            subTreeFormat = format;
        }
        
        public void setNodeFormat(String format) {
            nodeFormat = format;
        }
        
        public void setChildrenFormat(String format) {
            childrenFormat = format;
        }
        
        public void setChildFormat(String format) {
            childFormat = format;
        }
        
        public void setEdgeFromParentFormat(String format) {
            edgeFromParentFormat = format;
        }
        
        public String subTreeFormat() {
            return subTreeFormat;
        }
        
        public String nodeFormat() {
            return nodeFormat;
        }
        
        public String childrenFormat() {
            return childrenFormat;
        }
        
        public String childFormat() {
            return childFormat;
        }
        
        public String edgeFromParentFormat() {
            return edgeFromParentFormat;
        }
        
        public String toString() {
            return data.toString();
        }
    }
    
    public String getDefaultSubTreeFormat() {
        return defaultSubTreeFormat;
    }

    public void setDefaultSubTreeFormat(String format) {
        defaultSubTreeFormat = format;
    }

    public String getDefaultNodeFormat() {
        return defaultNodeFormat;
    }

    public void setDefaultNodeFormat(String format) {
        defaultNodeFormat = format;
    }

    public String getDefaultChildrenFormat() {
        return defaultChildrenFormat;
    }

    public void setDefaultChildrenFormat(String format) {
        defaultChildrenFormat = format;
    }

    public String getDefaultChildFormat() {
        return defaultChildFormat;
    }

    public void setDefaultChildFormat(String format) {
        defaultChildFormat = format;
    }
    
    public String getDefaultEdgeFromParentFormat() {
        return defaultEdgeFromParentFormat;
    }
    
    public void setDefaultEdgeFromParentFormat(String format) {
        defaultEdgeFromParentFormat = format;
    }
    
    public static void setSubTreeFormat(Object pgfProxy, String format) {
        ((PGFProxy) pgfProxy).setSubTreeFormat(format);
    }
    
    public static void setNodeFormat(Object pgfProxy, String format) {
        ((PGFProxy) pgfProxy).setNodeFormat(format);
    }
    
    public static void setChildrenFormat(Object pgfProxy, String format) {
        ((PGFProxy) pgfProxy).setChildrenFormat(format);
    }
    
    public static void setChildFormat(Object pgfProxy, String format) {
        ((PGFProxy) pgfProxy).setChildFormat(format);
    }
    
    public static void setEdgeFromParentFormat(Object pgfProxy, String format) {
        ((PGFProxy) pgfProxy).setEdgeFromParentFormat(format);
    }
     
    private static class PGFComparator implements IComparator {
        IComparator comparator = null;
        
        public PGFComparator(IComparator comparator) {
            this.comparator = comparator;
        }
        @Override
        public int compare(Object data1, Object data2) {
            return comparator.compare(((PGFProxy) data1).data, ((PGFProxy) data2).data);
        }

        @Override
        public int compare(Object data, IKey key) {
            return comparator.compare(((PGFProxy) data).data, ((PGFKey) key).key);
        }

    }
    
    public static IComparator comparator(IComparator comparator) {
        return new PGFComparator(comparator);
    }
    
    private static class PGFKey implements IKey {
        protected IKey key = null;
        
        public PGFKey(IKey key) {
            this.key = key;
        }
        
        @Override
        public boolean matches(Object data) {
            return key.matches(((PGFProxy) data).data);
        }
    }
    
    public static IKey key(IKey key) {
        return new PGFKey(key);
    }
    
    public PGFTree(Tree tree) {
        this.tree = tree;
    }
    
    protected String treeToPGF(Tree.Node currentRoot, String tabs) {
        String pgfTree = "[missing]";
        
        if (currentRoot != null) {
            PGFProxy proxy = (PGFProxy) currentRoot.data;
            String subTreeFormat = proxy.subTreeFormat();
            String nodeFormat = proxy.nodeFormat();
            String childrenFormat = proxy.childrenFormat();
            String childFormat = proxy.childFormat();
            String edgeFromParentFormat = proxy.edgeFromParentFormat();
            
            String pgfLeftSubTree = treeToPGF(currentRoot.left, tabs + "    ");
            String pgfRightSubTree = treeToPGF(currentRoot.right, tabs + "    ");
            
            pgfTree = (childFormat != "" ? childFormat + " " : "") + "{\n" + (subTreeFormat != "" ? tabs + "    " + subTreeFormat + "\n" : "")
                            + tabs + "    node" + (nodeFormat != "" ? " " + nodeFormat : "") + " (" + currentRoot.data + ") {" + currentRoot.data + "}\n"
                            + (childrenFormat != "" ? tabs + "    " + childrenFormat + "\n" : "");
            
            if (!pgfLeftSubTree.equals("[missing]") || !pgfRightSubTree.equals("[missing]")) {
                pgfTree += tabs + "    child " + pgfLeftSubTree + "\n";
                pgfTree += tabs + "    child " + pgfRightSubTree + "\n";
            } 
            pgfTree += (edgeFromParentFormat != "" ? tabs + "    edge from parent " + edgeFromParentFormat + "\n" : "");
            pgfTree += tabs + "}"; //\n";
        }
        
        return pgfTree;
    }

    public String treeToPGF() {
        String pgfTree = treeToPGF(tree.root, "    ");
        pgfTree = pgfHeader 
                    + "    \\path " + pgfTree + ";\n"
                    + "\\end{tikzpicture}\n";
        return pgfTree;
    }
    
    public String toString() {
        return treeToPGF();
    }
    
    public PGFProxy pgfProxy(Object data) {
        return new PGFProxy(data);
    }
}
