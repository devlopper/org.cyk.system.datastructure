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
	
	@Test
	public void updateCodeOneNestedSet() {
		String geographicZoneCode01 = "01_"+__getRandomCode__();
		
		__inject__(NestedSetRepresentation.class).createOne(new NestedSetDto().setCode(geographicZoneCode01));
		
		NestedSetDto geographicZone = (NestedSetDto) __inject__(NestedSetRepresentation.class).getOne(geographicZoneCode01, "business").getEntity();
		assertionHelper.assertNull(geographicZone.getParent());

		String geographicZoneCode02 = "02_"+__getRandomCode__();
		geographicZone.setCode(geographicZoneCode02);
		__inject__(NestedSetRepresentation.class).updateOne(geographicZone, "code");
		
		geographicZone = (NestedSetDto) __inject__(NestedSetRepresentation.class).getOne(geographicZoneCode01, "business").getEntity();
		assertionHelper.assertNull(geographicZone);
		
		geographicZone = (NestedSetDto) __inject__(NestedSetRepresentation.class).getOne(geographicZoneCode02, "business").getEntity();
		assertionHelper.assertNotNull(geographicZone);
	}
	
	@Test
	public void updateParentOneNestedSet() {
		String geographicZoneCode = "ZG_"+__getRandomCode__();
		String africaCode = "AF_"+__getRandomCode__();
		String coteDivoireCode = "CI_"+__getRandomCode__();
		
		__inject__(NestedSetRepresentation.class).createOne(new NestedSetDto().setCode(geographicZoneCode));
		__inject__(NestedSetRepresentation.class).createOne(new NestedSetDto().setCode(africaCode).setParent(new NestedSetDto().setCode(geographicZoneCode)));
		__inject__(NestedSetRepresentation.class).createOne(new NestedSetDto().setCode(coteDivoireCode).setParent(new NestedSetDto().setCode(africaCode)));
		
		NestedSetDto coteDivoire = (NestedSetDto) __inject__(NestedSetRepresentation.class).getOne(coteDivoireCode, "business").getEntity();
		assertionHelper.assertNotNull(coteDivoire.getParent());
		assertionHelper.assertEquals(africaCode, coteDivoire.getParent().getCode());
		assertionHelper.assertEquals("2", coteDivoire.getLeftIndex());
		assertionHelper.assertEquals("3", coteDivoire.getRightIndex());
		
		//coteDivoire.getParent().setIdentifier(null);//object should not be linked using identifier but code instead
		coteDivoire.getParent().setCode(geographicZoneCode);
		__inject__(NestedSetRepresentation.class).updateOne(coteDivoire, "code,parent");
		
		coteDivoire = (NestedSetDto) __inject__(NestedSetRepresentation.class).getOne(coteDivoireCode, "business").getEntity();
		assertionHelper.assertNotNull(coteDivoire.getParent());
		assertionHelper.assertEquals(geographicZoneCode, coteDivoire.getParent().getCode());
		assertionHelper.assertEquals("3", coteDivoire.getLeftIndex());
		assertionHelper.assertEquals("4", coteDivoire.getRightIndex());
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
