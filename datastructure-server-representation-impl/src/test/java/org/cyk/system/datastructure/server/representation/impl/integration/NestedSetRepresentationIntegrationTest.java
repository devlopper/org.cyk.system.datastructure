package org.cyk.system.datastructure.server.representation.impl.integration;

import org.cyk.system.datastructure.server.representation.api.collection.set.nested.NestedSetRepresentation;
import org.cyk.system.datastructure.server.representation.entities.collection.set.nested.NestedSetDto;
import org.cyk.system.datastructure.server.representation.entities.collection.set.nested.NestedSetDtoCollection;
import org.cyk.utility.server.representation.AbstractEntityCollection;
import org.cyk.utility.server.representation.RepresentationEntity;
import org.cyk.utility.server.representation.test.TestRepresentationCreate;
import org.cyk.utility.server.representation.test.arquillian.AbstractRepresentationArquillianIntegrationTestWithDefaultDeploymentAsSwram;
import org.junit.Test;

public class NestedSetRepresentationIntegrationTest extends AbstractRepresentationArquillianIntegrationTestWithDefaultDeploymentAsSwram {
	private static final long serialVersionUID = 1L;
	
	@Test
	public void createOneNestedSet() {
		NestedSetDto nestedSetDto = new NestedSetDto();
		nestedSetDto.setCode(__getRandomCode__());
		__inject__(TestRepresentationCreate.class).addObjects(nestedSetDto).execute();
	}
	
	@Test
	public void createManyNestedSet() {
		String geographicZoneCode = __getRandomCode__();
		String africaCode = __getRandomCode__();
		String coteDivoireCode = __getRandomCode__();
		
		__inject__(NestedSetRepresentation.class).createOne(new NestedSetDto().setCode(geographicZoneCode));
		__inject__(NestedSetRepresentation.class).createOne(new NestedSetDto().setCode(africaCode).setParent(new NestedSetDto().setCode(geographicZoneCode)));
		__inject__(NestedSetRepresentation.class).createOne(new NestedSetDto().setCode(coteDivoireCode).setParent(new NestedSetDto().setCode(africaCode)));
		
		NestedSetDto geographicZone = (NestedSetDto) __inject__(NestedSetRepresentation.class).getOne(geographicZoneCode, "business").getEntity();
		assertionHelper.assertNull(geographicZone.getParent());
		
		NestedSetDto africa = (NestedSetDto) __inject__(NestedSetRepresentation.class).getOne(africaCode, "business").getEntity();
		assertionHelper.assertNotNull(africa.getParent());
		assertionHelper.assertNull(africa.getParent().getParent());
		assertionHelper.assertEquals(geographicZoneCode, africa.getParent().getCode());
		
		NestedSetDto coteDivoire = (NestedSetDto) __inject__(NestedSetRepresentation.class).getOne(coteDivoireCode, "business").getEntity();
		assertionHelper.assertNotNull(coteDivoire.getParent());
		assertionHelper.assertNull(coteDivoire.getParent().getParent());
		assertionHelper.assertEquals(africaCode, coteDivoire.getParent().getCode());
		
		
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Class<? extends RepresentationEntity> __getLayerEntityInterfaceClass__() {
		return NestedSetRepresentation.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected <ENTITY> Class<? extends AbstractEntityCollection<ENTITY>> __getEntityCollectionClass__(Class<ENTITY> aClass) {
		try {
			return (Class<? extends AbstractEntityCollection<ENTITY>>) Class.forName(NestedSetDtoCollection.class.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}	

}
