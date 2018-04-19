package es.skills2018.api.enums;

public enum SearchFilter {
	NONE(""),
	EQUAL(" AND <columnName> = ?"),
	LIKE(" AND <columnName> LIKE CONCAT('%',?,'%')"),
	LOWER(" AND <columnName> < ?"),
	BIGGER(" AND <columnName> > ?"),
	LOWER_OR_EQUAL(" AND <columnName> <= ?"),
	BIGGER_OR_EQUAL(" AND <columnName> >= ?"),
	EQUAL_CASE_SENSITIVE(" AND <columnName> COLLATE utf8_bin = ?"),
	EQUALS_IGNORE_CASE(" AND LOWER(<columnName>) = LOWER(?)");
	private String text;
	SearchFilter(String text){
		this.text = text;
	}
	public String getText(String columnName) {
		return text.replace("<columnName>", columnName);
	}
}
