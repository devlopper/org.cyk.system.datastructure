package org.cyk.system.datastructure.server.business.impl.integration.collection.set.nested;

@javax.annotation.sql.DataSourceDefinition(
		name="java:/datastructure/server/dataSource",
		className="org.h2.jdbcx.JdbcDataSource",
		url="jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
		user="sa",
		password="sa"
)
public class DataSourceDefinition {}
