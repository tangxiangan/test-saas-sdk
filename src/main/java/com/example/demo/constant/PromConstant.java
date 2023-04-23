package com.example.demo.constant;

public interface PromConstant {
    /**
     * prometheus-查询SUCCESS
     */
    public static final String SUCCESS = "success";

    /**prometheus-查询参数*/
    public static final String QUERY = "/api/v1/query?query={query}&time={time}";

    public static final String QUERY_URL = "/api/v1/query";

    public static final String QUERY_RANGE = "/api/v1/query_range?query={query}&start={start}&end={end}&step={step}";

    public static final String QUERY_PARAM = "query";
    public static final String START = "start";

    public static final String END = "end";

    public static final String STEP = "step";

    public static final String TIME = "time";

    /**服务器的CPU利用率*/
    public static final String PROCESS_CPU_COUNT= "(1 - avg(rate(node_cpu_seconds_total{mode='idle',name=~'.*.*'}[2m])) by (instance)) * 100";

    /**服务器的内存利用率*/
    public static final String PROCESS_MEMORY_COUNT= "(1 - (node_memory_MemAvailable_bytes{name=~\".*.*\"} / (node_memory_MemTotal_bytes{name=~\".*.*\"})))* 100";

    /**服务器的磁盘用率*/
    public static final String PROCESS_DISK_COUNT= "(node_filesystem_size_bytes{name=~\".*.*\",fstype=~\"ext.*|xfs|nfs\",mountpoint !~\".*pod.*\"}-node_filesystem_free_bytes{name=~\".*.*\",fstype=~\"ext.*|xfs|nfs\",mountpoint !~\".*pod.*\"}) *100/(node_filesystem_avail_bytes {name=~\".*.*\",fstype=~\"ext.*|xfs|nfs\",mountpoint !~\".*pod.*\"}+(node_filesystem_size_bytes{name=~\".*.*\",fstype=~\"ext.*|xfs|nfs\",mountpoint !~\".*pod.*\"}-node_filesystem_free_bytes{name=~\".*.*\",fstype=~\"ext.*|xfs|nfs\",mountpoint !~\".*pod.*\"}))";

    public static final String PROCESS_DISK_COUNT_URL= "%28node_filesystem_size_bytes%7Bname%3D%7E%22.*.*%22%2Cfstype%3D%7E%22ext.*%7Cxfs%7Cnfs%22%2Cmountpoint+%21%7E%22.*pod.*%22%7D-node_filesystem_free_bytes%7Bname%3D%7E%22.*.*%22%2Cfstype%3D%7E%22ext.*%7Cxfs%7Cnfs%22%2Cmountpoint+%21%7E%22.*pod.*%22%7D%29+*100%2F%28node_filesystem_avail_bytes+%7Bname%3D%7E%22.*.*%22%2Cfstype%3D%7E%22ext.*%7Cxfs%7Cnfs%22%2Cmountpoint+%21%7E%22.*pod.*%22%7D%2B%28node_filesystem_size_bytes%7Bname%3D%7E%22.*.*%22%2Cfstype%3D%7E%22ext.*%7Cxfs%7Cnfs%22%2Cmountpoint+%21%7E%22.*pod.*%22%7D-node_filesystem_free_bytes%7Bname%3D%7E%22.*.*%22%2Cfstype%3D%7E%22ext.*%7Cxfs%7Cnfs%22%2Cmountpoint+%21%7E%22.*pod.*%22%7D%29%29";
}
