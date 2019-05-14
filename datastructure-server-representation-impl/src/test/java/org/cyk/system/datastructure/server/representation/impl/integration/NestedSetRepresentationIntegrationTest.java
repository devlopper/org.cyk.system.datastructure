package org.cyk.system.datastructure.server.representation.impl.integration;

import org.cyk.system.datastructure.server.representation.api.collection.set.nested.NestedSetRepresentation;
import org.cyk.system.datastructure.server.representation.entities.collection.set.nested.NestedSetDto;
import org.cyk.system.datastructure.server.representation.entities.collection.set.nested.NestedSetDtoCollection;
import org.cyk.utility.server.representation.AbstractEntityCollection;
import org.cyk.utility.server.representation.RepresentationEntity;
import org.cyk.utility.server.representation.test.TestRepresentationCreate;
import org.cyk.utility.server.representation.test.arquillian.AbstractRepresentationArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;

public class NestedSetRepresentationIntegrationTest extends AbstractRepresentationArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Test
	public void createOneNestedSet() {
		NestedSetDto nestedSetDto = new NestedSetDto();
		nestedSetDto.setIdentifier(__getRandomCode__());
		__inject__(TestRepresentationCreate.class).addObjects(nestedSetDto).execute();
	}
	
	@Test
	public void createManyNestedSet() {
		String geographicZoneCode = __getRandomCode__();
		String africaCode = "AFRICA_"+__getRandomCode__();
		String coteDivoireCode = "COTE_IVOIRE_"+__getRandomCode__();
		
		__inject__(NestedSetRepresentation.class).createOne(new NestedSetDto().setIdentifier(geographicZoneCode));
		__inject__(NestedSetRepresentation.class).createOne(new NestedSetDto().setIdentifier(africaCode).setParent(new NestedSetDto().setIdentifier(geographicZoneCode)));
		__inject__(NestedSetRepresentation.class).createOne(new NestedSetDto().setIdentifier(coteDivoireCode).setParent(new NestedSetDto().setIdentifier(africaCode)));
		
		NestedSetDto geographicZone = (NestedSetDto) __inject__(NestedSetRepresentation.class).getOne(geographicZoneCode, null,null).getEntity();
		assertionHelper.assertNotNull(geographicZone);
		assertionHelper.assertEquals(geographicZoneCode, geographicZone.getIdentifier());
		assertionHelper.assertNull(geographicZone.getParent());
		
		NestedSetDto africa = (NestedSetDto) __inject__(NestedSetRepresentation.class).getOne(africaCode, null,null).getEntity();
		assertionHelper.assertNotNull(africa);
		assertionHelper.assertEquals(africaCode, africa.getIdentifier());
		assertionHelper.assertNotNull(africa.getParent());
		assertionHelper.assertNull(africa.getParent().getParent());
		assertionHelper.assertEquals(geographicZoneCode, africa.getParent().getIdentifier());
		
		NestedSetDto coteDivoire = (NestedSetDto) __inject__(NestedSetRepresentation.class).getOne(coteDivoireCode, null,null).getEntity();
		assertionHelper.assertNotNull(coteDivoire.getParent());
		assertionHelper.assertNull(coteDivoire.getParent().getParent());
		assertionHelper.assertEquals(africaCode, coteDivoire.getParent().getIdentifier());
	}
	
	/*@Test
	public void updateCodeOneNestedSet() {
		String geographicZoneCode01 = "01_"+__getRandomCode__();
		
		__inject__(NestedSetRepresentation.class).createOne(new NestedSetDto().setIdentifier(geographicZoneCode01));
		
		NestedSetDto geographicZone = (NestedSetDto) __inject__(NestedSetRepresentation.class).getOne(geographicZoneCode01, null,null).getEntity();
		assertionHelper.assertNull(geographicZone.getParent());

		String geographicZoneCode02 = "02_"+__getRandomCode__();
		geographicZone.setIdentifier(geographicZoneCode02);
		__inject__(NestedSetRepresentation.class).updateOne(geographicZone, "code");
		
		geographicZone = (NestedSetDto) __inject__(NestedSetRepresentation.class).getOne(geographicZoneCode01, null,null).getEntity();
		assertionHelper.assertNull(geographicZone);
		
		geographicZone = (NestedSetDto) __inject__(NestedSetRepresentation.class).getOne(geographicZoneCode02, null,null).getEntity();
		assertionHelper.assertNotNull(geographicZone);
	}*/
	
	@Test
	public void updateParentOneNestedSet() {
		String geographicZoneCode = "ZG_"+__getRandomCode__();
		String africaCode = "AF_"+__getRandomCode__();
		String coteDivoireCode = "CI_"+__getRandomCode__();
		
		__inject__(NestedSetRepresentation.class).createOne(new NestedSetDto().setIdentifier(geographicZoneCode));
		__inject__(NestedSetRepresentation.class).createOne(new NestedSetDto().setIdentifier(africaCode).setParent(new NestedSetDto().setIdentifier(geographicZoneCode)));
		__inject__(NestedSetRepresentation.class).createOne(new NestedSetDto().setIdentifier(coteDivoireCode).setParent(new NestedSetDto().setIdentifier(africaCode)));
		
		NestedSetDto coteDivoire = (NestedSetDto) __inject__(NestedSetRepresentation.class).getOne(coteDivoireCode, null,null).getEntity();
		assertionHelper.assertNotNull(coteDivoire.getParent());
		assertionHelper.assertEquals(africaCode, coteDivoire.getParent().getIdentifier());
		assertionHelper.assertEquals("2", coteDivoire.getLeftIndex());
		assertionHelper.assertEquals("3", coteDivoire.getRightIndex());
		
		coteDivoire.getParent().setIdentifier(geographicZoneCode);
		__inject__(NestedSetRepresentation.class).updateOne(coteDivoire, "parent");
		
		coteDivoire = (NestedSetDto) __inject__(NestedSetRepresentation.class).getOne(coteDivoireCode, null,null).getEntity();
		assertionHelper.assertNotNull(coteDivoire.getParent());
		assertionHelper.assertEquals(geographicZoneCode, coteDivoire.getParent().getIdentifier());
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
