package kapitel_3.utils;

import java.lang.reflect.Field;

import kapitel_3.vl.BTree;
import kapitel_3.vl.IKey;
import kapitel_3.vl.SList;

public class PGFTreePrinter {
    private BTree bTree = null;
    
    private SList formatList = new SList();

    private Class<?> treeClass = null;
    private Class<?> nodeClass = null;
    private Field rootField = null;
    private Field leftField = null;
    private Field rightField = null;
    private Field dataField = null;
    private Field parentField = null;

    private ACommonFormat commonFormat = null;
    
    private String defaultSubTreeFormat = "";
    private String defaultNodeFormat = "";
    private String defaultChildrenFormat = "";
    private String defaultChildFormat = "";
    private String defaultEdgeFromParentFormat = "";

    
    public PGFTreePrinter(BTree bTree) {
        this.bTree = bTree;
        
        try {
            treeClass = Class.forName("kapitel_3.vl.BTree");
            nodeClass = Class.forName("kapitel_3.vl.BTree$Node");
            
            rootField = treeClass.getDeclaredField("root");
            rootField.setAccessible(true);
            
            leftField = nodeClass.getDeclaredField("left");
            leftField.setAccessible(true);

            rightField = nodeClass.getDeclaredField("right");
            rightField.setAccessible(true);

            dataField = nodeClass.getDeclaredField("data");
            dataField.setAccessible(true);

            parentField = nodeClass.getDeclaredField("parent");
            parentField.setAccessible(true);
        } catch (ClassNotFoundException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
    }

    private static final String tikzHeader = "% copy verbatim into a fragile frame\n"
            + "% or \\input this code as pgf-file\n"
            + "\\begin{tikzpicture}";
    private static final String tikzFooter = "\\end{tikzpicture}%";
    private static final String pgfStyles = "    \\tikzset{\n"
            + "        every node/.style={\n" 
            + "            inner sep=0,\n"
            + "            outer sep=0,\n"
            + "            minimum width=12pt,\n"
            + "            minimum height=12pt,\n"
            + "            draw,\n"
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
            + "            top color=white,\n"
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
            + "    }";

    
    public class Format {
        private String subTreeFormat = defaultSubTreeFormat;
        private String nodeFormat = defaultNodeFormat;
        private String childrenFormat = defaultChildrenFormat;
        private String childFormat = defaultChildFormat;
        private String edgeFromParentFormat = defaultEdgeFromParentFormat;

        protected Object data = null;
        
        private Format(Object data) {
            this.data = data;
        }
        
        public Format setSubTreeFormat(String format) {
            subTreeFormat = format;
            
            return this;
        }
        
        public Format setNodeFormat(String format) {
            nodeFormat = format;
            
            return this;
        }
        
        public Format setChildrenFormat(String format) {
            childrenFormat = format;
            
            return this;
        }
        
        public Format setChildFormat(String format) {
            childFormat = format;
            
            return this;
        }
        
        public Format setEdgeFromParentFormat(String format) {
            edgeFromParentFormat = format;
            
            return this;
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
        
        public void delete() {
            formatList.remove(this);
        }
    }
    
    public PGFTreePrinter setSubTreeFormat(String format) {
        defaultSubTreeFormat = format;
        
        return this;
    }
    
    public PGFTreePrinter setNodeFormat(String format) {
        defaultNodeFormat = format;
        
        return this;
    }
    
    public PGFTreePrinter setChildrenFormat(String format) {
        defaultChildrenFormat = format;
        
        return this;
    }
    
    public PGFTreePrinter setChildFormat(String format) {
        defaultChildFormat = format;
        
        return this;
    }
    
    public PGFTreePrinter setEdgeFromParentFormat(String format) {
        defaultEdgeFromParentFormat = format;
        
        return this;
    }
    
    
    public Format format(Object data) {
        Format format = (Format) formatList.search(new IKey() {

            @Override
            public boolean matches(Object format) {
                return ((Format) format).data == data;
            }
        });
        
        if (format == null) {
            format = new Format(data);
            formatList.prepend(format);
        }
        
        return format;
    }
    

    protected String treeToPGF(Object node, String tabs) throws IllegalArgumentException, IllegalAccessException {
        String pgfTree = "[missing]";
        
        if (node != null) {
            Format format = format(dataField.get(node));
            
            String subTreeFormat = "";
            if (commonFormat != null && !commonFormat.subTreeFormat(node).isEmpty()) {
                subTreeFormat += commonFormat.subTreeFormat(node);
            }
            if (format != null && !format.subTreeFormat().isEmpty()) {
                if (!subTreeFormat.isEmpty()) {
                    subTreeFormat += ", ";
                }
                subTreeFormat += format.subTreeFormat();
            }
            if (!subTreeFormat.isEmpty()) {
                subTreeFormat = "[" + subTreeFormat + "]";
            }
            
            String nodeFormat = "";
            if (commonFormat != null && !commonFormat.nodeFormat(node).isEmpty()) {
                nodeFormat += commonFormat.nodeFormat(node);
            }
            if (format != null && !format.nodeFormat().isEmpty()) {
                if (!nodeFormat.isEmpty()) {
                    nodeFormat += ", ";
                }
                nodeFormat += format.nodeFormat();
            }
            if (!nodeFormat.isEmpty()) {
                nodeFormat = "[" + nodeFormat + "]";
            }
            
            String childrenFormat = "";
            if (commonFormat != null && !commonFormat.nodeFormat(node).isEmpty()) {
                childrenFormat += commonFormat.childrenFormat(node);
            }
            if (format != null && !format.childrenFormat().isEmpty()) {
                if (!childrenFormat.isEmpty()) {
                    childrenFormat += ", ";
                }
                childrenFormat += format.childrenFormat();
            }
            if (!childrenFormat.isEmpty()) {
                childrenFormat = "[" + childrenFormat + "]";
            }
            
            String childFormat = "";
            if (commonFormat != null && !commonFormat.nodeFormat(node).isEmpty()) {
                childFormat += commonFormat.childrenFormat(node);
            }
            if (format != null && !format.childrenFormat().isEmpty()) {
                if (!childFormat.isEmpty()) {
                    childFormat += ", ";
                }
                childFormat += format.childrenFormat();
            }
            if (!childFormat.isEmpty()) {
                childFormat = "[" + childFormat + "]";
            }
            
            String edgeFromParentFormat = "";
            if (commonFormat != null && !commonFormat.nodeFormat(node).isEmpty()) {
                edgeFromParentFormat += commonFormat.childrenFormat(node);
            }
            if (format != null && !format.childrenFormat().isEmpty()) {
                if (!edgeFromParentFormat.isEmpty()) {
                    edgeFromParentFormat += ", ";
                }
                edgeFromParentFormat += format.childrenFormat();
            }
            if (!edgeFromParentFormat.isEmpty()) {
                edgeFromParentFormat = "[" + edgeFromParentFormat + "]";
            }
            
            String pgfLeftSubTree = treeToPGF(leftField.get(node), tabs + "    ");
            String pgfRightSubTree = treeToPGF(rightField.get(node), tabs + "    ");
            
            pgfTree = (!childFormat.equals("") ? childFormat + " " : "") + "{\n" 
                            + (!subTreeFormat.equals("") ? tabs + "    " + subTreeFormat + "\n" : "")
                            + tabs + "    node" + (!nodeFormat.equals("") ? " " + nodeFormat : "") 
                            + " (" + dataField.get(node) + ") {" + dataField.get(node) + "}\n"
                            + (!childrenFormat.equals("") ? tabs + "    " + childrenFormat + "\n" : "");
            
            if (!pgfLeftSubTree.equals("[missing]") || !pgfRightSubTree.equals("[missing]")) {
                pgfTree += tabs + "    child " + pgfLeftSubTree + "\n";
                pgfTree += tabs + "    child " + pgfRightSubTree + "\n";
            }
            
            pgfTree += (!edgeFromParentFormat.equals("") ? tabs + "    edge from parent " + edgeFromParentFormat + "\n" : "");
            pgfTree += tabs + "}";
        }
        
        return pgfTree;
    }

    
    public String header() {
        return tikzHeader + "\n" + pgfStyles;
    }
    
    
    public String footer() {
        return tikzFooter;
    }
    
    
    public String tree() {
        String treeString = "";
        
        try {
            treeString =  "    \\path " + treeToPGF(rootField.get(bTree), "    ") + ";";
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        
        return treeString;
    }

    
    public String toString() {
        return header() + "\n" + tree() + "\n" + footer();  
    }
    

    public void setCommonFormat(ACommonFormat commonFormat) {
        this.commonFormat = commonFormat;
    }
}
