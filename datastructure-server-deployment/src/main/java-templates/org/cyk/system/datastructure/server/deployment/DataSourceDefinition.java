package ci.gouv.dgbf.system.usermanagement.server.deployment;

@javax.annotation.sql.DataSourceDefinition(
		name="${data.source.jndi.name}",
		className="${data.source.className}",
		url="${data.source.url}",
		user="${data.source.user}",
		password="${data.source.password}"
)
public class DataSourceDefinition {}
