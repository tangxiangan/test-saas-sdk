package io.github.tangxiangan.pojo.comPdfKit;

import java.util.List;
import lombok.Data;

@Data
public class QueryTaskRecordsResult{
	private int total;
	private int current;
	private int pages;
	private int size;
	private boolean optimizeCountSql;
	private List<RecordsItem> records;
	private Object maxLimit;
	private boolean searchCount;
	private List<Object> orders;
	private Object countId;
}