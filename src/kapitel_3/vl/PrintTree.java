package kapitel_3.vl;

public class PrintTree {
    ListMap map = new ListMap();
    BTree tree = null;

    private class PGFFormat {
        String pathFormat;
        String nodeFormat;

        PGFFormat(String nodeFormat, String pathFormat) {
            this.nodeFormat = nodeFormat;
            this.pathFormat = pathFormat;
        }
    }

    private static final String pgfHeader = "    \\tikzset{\n"
            + "        every node/.style={\n" 
            + "            inner sep=0,\n"
            + "            outer sep=0,\n"
            + "            minimum width=11pt,\n"
            + "            minimum height=11pt,\n"
            + "            draw,\n"
            + "            font=\\scriptsize,\n" 
            + "            circle\n"
            + "        },\n" 
            + "        blank/.style={\n"
            + "            draw=none\n" 
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
            + "        level distance=1cm,\n" 
            + "        level/.style={\n"
            + "            sibling distance=3cm/(2^(#1-1))\n" 
            + "        },\n"
            + "        edge from parent/.style={\n" 
            + "            draw,\n"
            + "            edge from parent path={\n"
            + "                (\\tikzparentnode) -- (\\tikzchildnode)\n"
            + "            }\n" 
            + "        },\n"
            + "    };\n";

    public PrintTree(BTree tree) {
        this.tree = tree;
    }

    public void addFormat(Object data, String nodeFormat, String pathFormat) {
        map.insert(data, new PGFFormat(nodeFormat, pathFormat));
    }

    protected String treeToPGF(BTree.Node currentRoot, String tabs) {
        String pgfTree = "[missing]\n";

        if (currentRoot != null) {
            PGFFormat format = (PGFFormat) map.search(currentRoot.data);
            
            String nodeFormat = (format != null) ? !format.nodeFormat.equals("") ? format.nodeFormat : "[normal node]" : "[normal node]";
            String pathFormat = (format != null) ? !format.pathFormat.equals("") ? format.pathFormat : "[normal node]" : "[normal node]";
            
            String pgfLeftSubTree = treeToPGF(currentRoot.left, tabs + "    ");
            String pgfRightSubTree = treeToPGF(currentRoot.right, tabs + "    ");
            
            pgfTree = pathFormat + " {\n" 
                    + tabs + "    node " + nodeFormat + " (" + currentRoot.data + ") {" + currentRoot.data + "}\n";
            if (!pgfLeftSubTree.equals("[missing]\n") || !pgfRightSubTree.equals("[missing]\n")) {
                pgfTree += tabs + "    child " + pgfLeftSubTree;
                pgfTree += tabs + "    child " + pgfRightSubTree;
            }
            pgfTree += tabs + "}\n";
        }
        return pgfTree;
    }

    protected static String treeToQTree(BTree.Node currentRoot) {
        String qTree = null;

        if (currentRoot != null) {
            qTree = "[.\\node[circle,inner sep=0,minimum width=15,draw]{"
                    + currentRoot.data + "}; ";
            String qTreeLeft = treeToQTree(currentRoot.left);
            String qTreeRight = treeToQTree(currentRoot.right);
            if (qTreeLeft != null || qTreeRight != null) {
                if (qTreeLeft == null && qTreeRight != null) {
                    qTreeLeft = "\\edge[draw=white];{} ";
                } else if (qTreeRight == null && qTreeLeft != null) {
                    qTreeRight = "\\edge[draw=white];{} ";
                }
                qTree = qTree + qTreeLeft + qTreeRight;
            }
            qTree = qTree + "] ";
        }

        return qTree;
    }

    public String treeToPGF() {
        String pgfTree = treeToPGF(tree.root, "    ");
        pgfTree = "\\begin{tikzpicture}\n" 
                    + pgfHeader 
                    + "    \\path "
                    + pgfTree 
                    + "    ;\n"
                    + "\\end{tikzpicture}\n";
        return pgfTree;
    }
    
    public String treeToQTree() {
        return "\\tikzset{edge from parent/.style={draw, edge from parent path={(\\tikzparentnode) -- (\\tikzchildnode)}}}\n\\Tree "
                + treeToQTree(tree.root);
    }
}
