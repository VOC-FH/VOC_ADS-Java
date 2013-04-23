package kapitel_5.tests;

public class ArrayPlotter {
    int[] array = null;
    int lastFirst = -1;
    boolean first = true;
    String tikzString = "";
    String exchangeString = "";
    int plotCount = 0;
    boolean monitoredNode[] = null;
    int specialAMonitor = -1;
    int specialBMonitor = -1;
    
    public ArrayPlotter(int[] array) {
        this.array = array;
    }
    
    public void beginPlot() {
        first = true;
        tikzString = "\\begin{tikzpicture}[\n" +
        "    normal node/.style={\n" +
        "        draw,\n" +
        "        circle,\n" +
        "        minimum width=11pt,\n" +
        "        inner sep=0,\n" +
        "        font=\\scriptsize,\n" +
        "        node distance=0.5\n" +
        "    },\n" +
        "    monitored node/.style={\n" + 
        "        normal node,\n" +
        "        top color=white,\n" +
        "        bottom color=red!20,\n" +
        "    },\n" +
        "    special a monitored node/.style={\n" + 
        "        normal node,\n" +
        "        top color=white,\n" +
        "        bottom color=blue!20,\n" +
        "    },\n" +
        "    special b monitored node/.style={\n" + 
        "        normal node,\n" +
        "        top color=white,\n" +
        "        bottom color=green!20,\n" +
        "    },\n" +
        "    exchange1/.style={\n" +
        "        -stealth,\n" +
        "        color=blue\n" + 
        "    },\n" +
        "    exchange2/.style={\n" +
        "        -stealth,\n" +
        "        color=blue\n" + 
        "    }\n" +
    "]\n";
    }
    
    public void plot() {
        for (int i = 0; i < array.length; i++) {
            String style = (monitoredNode[i]) ? "monitored node" : "normal node";
            style = (specialAMonitor == i) ? "special a monitored node" : style;
            style = (specialBMonitor == i) ? "special b monitored node" : style;
            if (i == 0) {
                if (!first) {
                    tikzString += "\\node[" + style + ", below=of " + lastFirst + "" + (plotCount - 1) + "] (" + array[i] + "" + plotCount + ") {" + array[i] + "};\n";
                } else {
                    tikzString += "\\node[" + style + "] (" + array[i] + "" + plotCount + ") {" + array[i] + "};\n";
                }
                lastFirst = array[i];
                first = false;
            } else {
                tikzString += "\\node[" + style + ", right=of " + array[i - 1] + "" + plotCount + "] (" + array[i] + "" + plotCount + ") {" + array[i] + "};\n";
                tikzString += "\\draw (" + array[i - 1] + "" + plotCount + ") -- (" + array[i] + "" + plotCount + ");\n";
            }
        }
    }
    
    public void beginArray() {
        plotCount++;
        exchangeString = "";
        this.monitoredNode = new boolean[array.length];
        specialAMonitor = -1;
        specialBMonitor = -1;
    }
    
    public void monitor(int i) {
        monitoredNode[i] = true;
    }
    
    public void specialAMonitor(int i) {
        specialAMonitor = i;
    }
    
    public void specialBMonitor(int i) {
        specialBMonitor = i;
    }
    
    public void exchange(int a, int b) {
        exchangeString += "\\draw[exchange1] (" + a + "" + (plotCount - 1) + ") -- (" + a + "" + plotCount +");\n";
        exchangeString += "\\draw[exchange2] (" + b + "" + (plotCount - 1) + ") -- (" + b + "" + plotCount +");\n";
    }
    
    public void endArray() {
        tikzString = tikzString + exchangeString;
    }
    
    public String endPlot() {
        return tikzString + "\\end{tikzpicture}";
    }
}
