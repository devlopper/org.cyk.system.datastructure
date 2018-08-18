package org.cyk.system.datastructure.server.business.impl.integration.collection.set.nested;

import static org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet.generateCode;

import org.cyk.system.datastructure.server.persistence.api.collection.set.nested.NestedSetPersistence;
import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet;
import org.cyk.utility.server.business.test.arquillian.AbstractBusinessEntityIntegrationTestWithDefaultDeploymentAsSwram;
import org.cyk.utility.value.ValueUsageType;
import org.junit.Assert;
import org.junit.Test;

public class NestedSetBusinessIntegrationTest extends AbstractBusinessEntityIntegrationTestWithDefaultDeploymentAsSwram<NestedSet> {
	private static final long serialVersionUID = 1L;
	
	@Test
	public void delete___groupWithOneNestedSet(){
		String g01 = __getRandomCode__();
		String g01_0_1 = generateCode(g01,0);		
		createNestedSets(g01, null, g01_0_1);
		assertGroup(g01, 1);
		assertNestedSet(g01_0_1,null, 0, 1, 0, 0, 0);
		__deleteEntityByBusinessIdentifier__(NestedSet.class, g01_0_1);
		assertGroup(g01, 0);
		//clean
		__deleteEntitiesAll__(NestedSet.class);
	}
	
	@Test
	public void deleteLeaf___groupWithTwoNestedSet(){
		String g01 = __getRandomCode__();
		String g01_0_3 = generateCode(g01,0), g01_1_2 = generateCode(g01_0_3,0);		
		createNestedSets(g01, null, g01_0_3);
		assertGroup(g01, 1);
		createNestedSets(null, g01_0_3, g01_1_2);
		assertGroup(g01, 2);
		
		assertNestedSet(g01_0_3,null, 0, 3, 1, 1, 0);
		assertNestedSet(g01_1_2,g01_0_3, 1, 2, 0, 0, 1);
		
		__deleteEntityByBusinessIdentifier__(NestedSet.class, g01_1_2);
		
		assertNestedSet(g01_0_3,null, 0, 1, 0, 0, 0);
		
		assertGroup(g01, 1);
		//clean
		__deleteEntitiesAll__(NestedSet.class);
	}
	
	@Test
	public void deleteRoot___groupWithTwoNestedSet(){
		String g01 = __getRandomCode__();
		String g01_0_3 = generateCode(g01,0), g01_1_2 = generateCode(g01_0_3,0);		
		createNestedSets(g01, null, g01_0_3);
		assertGroup(g01, 1);
		createNestedSets(null, g01_0_3, g01_1_2);
		assertGroup(g01, 2);
		
		assertNestedSet(g01_0_3,null, 0, 3, 1, 1, 0);
		assertNestedSet(g01_1_2,g01_0_3, 1, 2, 0, 0, 1);
		
		__deleteEntityByBusinessIdentifier__(NestedSet.class, g01_0_3);
		
		assertGroup(g01, 0);
		//clean
		__deleteEntitiesAll__(NestedSet.class);
	}
	
	@Test
	public void deleteLeaf___groupWithThreeNestedSet(){
		String g01 = __getRandomCode__();
		String g01_0_5 = generateCode(g01,0), g01_1_2 = generateCode(g01_0_5,0), g01_3_4 = generateCode(g01_0_5,1);		
		createNestedSets(g01, null, g01_0_5);
		assertGroup(g01, 1);
		createNestedSets(null, g01_0_5, g01_1_2,g01_3_4);
		
		assertGroup(g01, 3);
		assertNestedSet(g01_0_5,null, 0, 5, 2, 2, 0);
		assertNestedSet(g01_1_2,g01_0_5, 1, 2, 0, 0, 1);
		assertNestedSet(g01_3_4,g01_0_5, 3, 4, 0, 0, 1);
		
		__deleteEntityByBusinessIdentifier__(NestedSet.class, g01_3_4);
		
		assertGroup(g01, 2);
		assertNestedSet(g01_0_5,null, 0, 3, 1, 1, 0);
		assertNestedSet(g01_1_2,g01_0_5, 1, 2, 0, 0, 1);
		
		//clean
		__deleteEntitiesAll__(NestedSet.class);
	}
	
	@Test
	public void deleteRoot___groupWithThreeNestedSet(){
		String g01 = __getRandomCode__();
		String g01_0_5 = generateCode(g01,0), g01_1_2 = generateCode(g01_0_5,0), g01_3_4 = generateCode(g01_0_5,1);		
		createNestedSets(g01, null, g01_0_5);
		assertGroup(g01, 1);
		createNestedSets(null, g01_0_5, g01_1_2,g01_3_4);
		
		assertGroup(g01, 3);
		assertNestedSet(g01_0_5,null, 0, 5, 2, 2, 0);
		assertNestedSet(g01_1_2,g01_0_5, 1, 2, 0, 0, 1);
		assertNestedSet(g01_3_4,g01_0_5, 3, 4, 0, 0, 1);
		
		__deleteEntityByBusinessIdentifier__(NestedSet.class, g01_0_5);
		
		assertGroup(g01, 0);
	}
	
	/**
	 * <img src="group01.png" />
	 */
	@Test
	public void createGroup01AndAssertAfterAllNestedSetCreated(){
		String g01 = __getRandomCode__();
		String g01_0_31 = generateCode(g01,0), g01_1_10 = generateCode(g01_0_31,0), g01_11_12 = generateCode(g01_0_31,1), g01_13_30 = generateCode(g01_0_31,2);
		String g01_2_3 = generateCode(g01_1_10,0), g01_4_9 = generateCode(g01_1_10,1);
		String g01_14_17 = generateCode(g01_13_30,0), g01_18_19 = generateCode(g01_13_30,1),g01_20_25 = generateCode(g01_13_30,2), g01_26_29 = generateCode(g01_13_30,3);
		String g01_5_6 = generateCode(g01_4_9,0), g01_7_8 = generateCode(g01_4_9,1);
		String g01_15_16 = generateCode(g01_14_17,0);
		String g01_21_22 = generateCode(g01_20_25,0), g01_23_24 = generateCode(g01_20_25,1);
		String g01_27_28 = generateCode(g01_26_29,0);
		
		createNestedSets(g01, null, g01_0_31);
		createNestedSets(null, g01_0_31, g01_1_10,g01_11_12,g01_13_30);
		createNestedSets(null, g01_1_10, g01_2_3,g01_4_9);
		createNestedSets(null, g01_13_30, g01_14_17,g01_18_19,g01_20_25,g01_26_29);
		createNestedSets(null, g01_4_9, g01_5_6,g01_7_8);
		createNestedSets(null, g01_14_17, g01_15_16);
		createNestedSets(null, g01_20_25, g01_21_22,g01_23_24);
		createNestedSets(null, g01_26_29, g01_27_28);
		
		assertGroup(g01, 16);
		
		assertNestedSet(g01_0_31,null, 0, 31, 3, 15, 0);
		
		assertNestedSet(g01_1_10,g01_0_31, 1, 10, 2, 4, 1);
		assertNestedSet(g01_11_12,g01_0_31, 11, 12, 0, 0, 1);
		assertNestedSet(g01_13_30,g01_0_31, 13, 30, 4, 8, 1);

		assertNestedSet(g01_2_3,g01_1_10, 2, 3, 0, 0, 2);
		assertNestedSet(g01_4_9,g01_1_10, 4, 9, 2, 2, 2);
		assertNestedSet(g01_14_17,g01_13_30, 14, 17, 1, 1, 2);
		assertNestedSet(g01_18_19,g01_13_30, 18, 19, 0, 0, 2);
		assertNestedSet(g01_20_25,g01_13_30, 20, 25, 2, 2, 2);
		assertNestedSet(g01_26_29,g01_13_30, 26, 29, 1, 1, 2);
		
		assertNestedSet(g01_5_6,g01_4_9, 5, 6, 0, 0, 3);
		assertNestedSet(g01_7_8,g01_4_9, 7, 8, 0, 0, 3);
		assertNestedSet(g01_15_16,g01_14_17, 15, 16, 0, 0, 3);
		assertNestedSet(g01_21_22,g01_20_25, 21, 22, 0, 0, 3);
		assertNestedSet(g01_23_24,g01_20_25, 23, 24, 0, 0, 3);
		assertNestedSet(g01_27_28,g01_26_29, 27, 28, 0, 0, 3);
		
		__deleteEntityByBusinessIdentifier__(NestedSet.class, g01_27_28);
		assertGroup(g01, 15);
		
		assertNestedSet(g01_0_31,null, 0, 29, 3, 14, 0);
		
		assertNestedSet(g01_1_10,g01_0_31, 1, 10, 2, 4, 1);
		assertNestedSet(g01_11_12,g01_0_31, 11, 12, 0, 0, 1);
		assertNestedSet(g01_13_30,g01_0_31, 13, 28, 4, 7, 1);

		assertNestedSet(g01_2_3,g01_1_10, 2, 3, 0, 0, 2);
		assertNestedSet(g01_4_9,g01_1_10, 4, 9, 2, 2, 2);
		assertNestedSet(g01_14_17,g01_13_30, 14, 17, 1, 1, 2);
		assertNestedSet(g01_18_19,g01_13_30, 18, 19, 0, 0, 2);
		assertNestedSet(g01_20_25,g01_13_30, 20, 25, 2, 2, 2);
		assertNestedSet(g01_26_29,g01_13_30, 26, 27, 0, 0, 2);
		
		assertNestedSet(g01_5_6,g01_4_9, 5, 6, 0, 0, 3);
		assertNestedSet(g01_7_8,g01_4_9, 7, 8, 0, 0, 3);
		assertNestedSet(g01_15_16,g01_14_17, 15, 16, 0, 0, 3);
		assertNestedSet(g01_21_22,g01_20_25, 21, 22, 0, 0, 3);
		assertNestedSet(g01_23_24,g01_20_25, 23, 24, 0, 0, 3);
		
		__deleteEntityByBusinessIdentifier__(NestedSet.class, g01_0_31);
		assertGroup(g01, 0);
		
		//clean
		__deleteEntitiesAll__(NestedSet.class);
	}
	
	/**
	 * <img src="group01.png" />
	 */
	@Test
	public void createGroup01AndAssertAfterEachNestedSetCreated(){
		String g01 = __getRandomCode__();
		String g01_0_31 = generateCode(g01,0), g01_1_10 = generateCode(g01_0_31,0), g01_11_12 = generateCode(g01_0_31,1), g01_13_30 = generateCode(g01_0_31,2);
		String g01_2_3 = generateCode(g01_1_10,0), g01_4_9 = generateCode(g01_1_10,1);
		String g01_14_17 = generateCode(g01_13_30,0), g01_18_19 = generateCode(g01_13_30,1),g01_20_25 = generateCode(g01_13_30,2), g01_26_29 = generateCode(g01_13_30,3);
		String g01_5_6 = generateCode(g01_4_9,0), g01_7_8 = generateCode(g01_4_9,1);
		String g01_15_16 = generateCode(g01_14_17,0);
		String g01_21_22 = generateCode(g01_20_25,0), g01_23_24 = generateCode(g01_20_25,1);
		String g01_27_28 = generateCode(g01_26_29,0);
		
		assertGroup(g01, 0);
		createNestedSets(g01, null,g01_0_31);
		assertGroup(g01, 1).assertNestedSet(g01_0_31,null, 0, 1, 0, 0,0);
		
		createNestedSets(null, g01_0_31,g01_1_10);
		assertGroup(g01, 2).assertNestedSet(g01_0_31,null, 0, 3, 1, 1,0)
			.assertNestedSet(g01_1_10,g01_0_31, 1, 2, 0, 0,1);
		
		createNestedSets(null, g01_0_31,g01_11_12);
		assertGroup(g01, 3).assertNestedSet(g01_0_31,null, 0, 5, 2, 2,0)
		.assertNestedSet(g01_1_10,g01_0_31, 1, 2, 0, 0,1)
		.assertNestedSet(g01_11_12,g01_0_31, 3, 4, 0, 0,1);
		
		createNestedSets(null, g01_0_31,g01_13_30);
		assertGroup(g01, 4).assertNestedSet(g01_0_31,null, 0, 7, 3, 3,0)
		.assertNestedSet(g01_1_10,g01_0_31, 1, 2, 0, 0,1)
		.assertNestedSet(g01_11_12,g01_0_31, 3, 4, 0, 0,1)
		.assertNestedSet(g01_13_30,g01_0_31, 5, 6, 0, 0,1);
		
		createNestedSets(null, g01_1_10,g01_2_3);
		assertGroup(g01, 5).assertNestedSet(g01_0_31,null, 0, 9, 3, 4,0)
		.assertNestedSet(g01_1_10,g01_0_31, 1, 4, 1, 1,1)
		.assertNestedSet(g01_11_12,g01_0_31, 5, 6, 0, 0,1)
		.assertNestedSet(g01_13_30,g01_0_31, 7, 8, 0, 0,1)
		.assertNestedSet(g01_2_3,g01_1_10, 2, 3, 0, 0,2);
		
		createNestedSets(null, g01_1_10,g01_4_9);
		assertGroup(g01, 6).assertNestedSet(g01_0_31,null, 0, 11, 3, 5,0)
		.assertNestedSet(g01_1_10,g01_0_31, 1, 6, 2, 2,1)
		.assertNestedSet(g01_11_12,g01_0_31, 7, 8, 0, 0,1)
		.assertNestedSet(g01_13_30,g01_0_31, 9, 10, 0, 0,1)
		.assertNestedSet(g01_2_3,g01_1_10, 2, 3, 0, 0,2)
		.assertNestedSet(g01_4_9,g01_1_10, 4, 5, 0, 0,2)
		;
		
		createNestedSets(null, g01_4_9,g01_5_6);
		assertGroup(g01, 7).assertNestedSet(g01_0_31,null, 0, 13, 3, 6,0)
		.assertNestedSet(g01_1_10,g01_0_31, 1, 8, 2, 3,1)
		.assertNestedSet(g01_11_12,g01_0_31, 9, 10, 0, 0,1)
		.assertNestedSet(g01_13_30,g01_0_31, 11, 12, 0, 0,1)
		.assertNestedSet(g01_2_3,g01_1_10, 2, 3, 0, 0,2)
		.assertNestedSet(g01_4_9,g01_1_10, 4, 7, 1, 1,2)
		.assertNestedSet(g01_5_6,g01_4_9, 5, 6, 0, 0,3)
		;
		
		createNestedSets(null, g01_4_9,g01_7_8);
		assertGroup(g01, 8).assertNestedSet(g01_0_31,null, 0, 15, 3, 7,0)
		.assertNestedSet(g01_1_10,g01_0_31, 1, 10, 2, 4,1)
		.assertNestedSet(g01_11_12,g01_0_31, 11, 12, 0, 0,1)
		.assertNestedSet(g01_13_30,g01_0_31, 13, 14, 0, 0,1)
		.assertNestedSet(g01_2_3,g01_1_10, 2, 3, 0, 0,2)
		.assertNestedSet(g01_4_9,g01_1_10, 4, 9, 2, 2,2)
		.assertNestedSet(g01_5_6,g01_4_9, 5, 6, 0, 0,3)
		.assertNestedSet(g01_7_8,g01_4_9, 7, 8, 0, 0,3)
		;
		
		createNestedSets(null, g01_13_30,g01_14_17);
		assertGroup(g01, 9).assertNestedSet(g01_0_31,null, 0, 17, 3, 8,0)
		.assertNestedSet(g01_1_10,g01_0_31, 1, 10, 2, 4,1)
		.assertNestedSet(g01_11_12,g01_0_31, 11, 12, 0, 0,1)
		.assertNestedSet(g01_13_30,g01_0_31, 13, 16, 1, 1,1)
		.assertNestedSet(g01_2_3,g01_1_10, 2, 3, 0, 0,2)
		.assertNestedSet(g01_4_9,g01_1_10, 4, 9, 2, 2,2)
		.assertNestedSet(g01_5_6,g01_4_9, 5, 6, 0, 0,3)
		.assertNestedSet(g01_7_8,g01_4_9, 7, 8, 0, 0,3)
		.assertNestedSet(g01_14_17,g01_13_30, 14, 15, 0, 0,2)
		;
		
		createNestedSets(null, g01_14_17,g01_15_16);
		assertGroup(g01, 10).assertNestedSet(g01_0_31,null, 0, 19, 3, 9,0)
		.assertNestedSet(g01_1_10,g01_0_31, 1, 10, 2, 4,1)
		.assertNestedSet(g01_11_12,g01_0_31, 11, 12, 0, 0,1)
		.assertNestedSet(g01_13_30,g01_0_31, 13, 18, 1, 2,1)
		.assertNestedSet(g01_2_3,g01_1_10, 2, 3, 0, 0,2)
		.assertNestedSet(g01_4_9,g01_1_10, 4, 9, 2, 2,2)
		.assertNestedSet(g01_5_6,g01_4_9, 5, 6, 0, 0,3)
		.assertNestedSet(g01_7_8,g01_4_9, 7, 8, 0, 0,3)
		.assertNestedSet(g01_14_17,g01_13_30, 14, 17, 1, 1,2)
		.assertNestedSet(g01_15_16,g01_14_17, 15, 16, 0, 0,3)
		;
		
		createNestedSets(null, g01_13_30,g01_18_19);
		assertGroup(g01, 11).assertNestedSet(g01_0_31,null, 0, 21, 3, 10,0)
		.assertNestedSet(g01_1_10,g01_0_31, 1, 10, 2, 4,1)
		.assertNestedSet(g01_11_12,g01_0_31, 11, 12, 0, 0,1)
		.assertNestedSet(g01_13_30,g01_0_31, 13, 20, 2, 3,1)
		.assertNestedSet(g01_2_3,g01_1_10, 2, 3, 0, 0,2)
		.assertNestedSet(g01_4_9,g01_1_10, 4, 9, 2, 2,2)
		.assertNestedSet(g01_5_6,g01_4_9, 5, 6, 0, 0,3)
		.assertNestedSet(g01_7_8,g01_4_9, 7, 8, 0, 0,3)
		.assertNestedSet(g01_14_17,g01_13_30, 14, 17, 1, 1,2)
		.assertNestedSet(g01_15_16,g01_14_17, 15, 16, 0, 0,3)
		.assertNestedSet(g01_18_19,g01_13_30, 18, 19, 0, 0,2)
		;
		
		createNestedSets(null, g01_13_30,g01_20_25);
		assertGroup(g01, 12).assertNestedSet(g01_0_31,null, 0, 23, 3, 11,0)
		.assertNestedSet(g01_1_10,g01_0_31, 1, 10, 2, 4,1)
		.assertNestedSet(g01_11_12,g01_0_31, 11, 12, 0, 0,1)
		.assertNestedSet(g01_13_30,g01_0_31, 13, 22, 3, 4,1)
		.assertNestedSet(g01_2_3,g01_1_10, 2, 3, 0, 0,2)
		.assertNestedSet(g01_4_9,g01_1_10, 4, 9, 2, 2,2)
		.assertNestedSet(g01_5_6,g01_4_9, 5, 6, 0, 0,3)
		.assertNestedSet(g01_7_8,g01_4_9, 7, 8, 0, 0,3)
		.assertNestedSet(g01_14_17,g01_13_30, 14, 17, 1, 1,2)
		.assertNestedSet(g01_15_16,g01_14_17, 15, 16, 0, 0,3)
		.assertNestedSet(g01_18_19,g01_13_30, 18, 19, 0, 0,2)
		.assertNestedSet(g01_20_25,g01_13_30, 20, 21, 0, 0,2)
		;
		
		createNestedSets(null, g01_13_30,g01_26_29);
		assertGroup(g01, 13).assertNestedSet(g01_0_31,null, 0, 25, 3, 12,0)
		.assertNestedSet(g01_1_10,g01_0_31, 1, 10, 2, 4,1)
		.assertNestedSet(g01_11_12,g01_0_31, 11, 12, 0, 0,1)
		.assertNestedSet(g01_13_30,g01_0_31, 13, 24, 4, 5,1)
		.assertNestedSet(g01_2_3,g01_1_10, 2, 3, 0, 0,2)
		.assertNestedSet(g01_4_9,g01_1_10, 4, 9, 2, 2,2)
		.assertNestedSet(g01_5_6,g01_4_9, 5, 6, 0, 0,3)
		.assertNestedSet(g01_7_8,g01_4_9, 7, 8, 0, 0,3)
		.assertNestedSet(g01_14_17,g01_13_30, 14, 17, 1, 1,2)
		.assertNestedSet(g01_15_16,g01_14_17, 15, 16, 0, 0,3)
		.assertNestedSet(g01_18_19,g01_13_30, 18, 19, 0, 0,2)
		.assertNestedSet(g01_20_25,g01_13_30, 20, 21, 0, 0,2)
		.assertNestedSet(g01_26_29,g01_13_30, 22, 23, 0, 0,2)
		;
		
		createNestedSets(null, g01_26_29,g01_27_28);
		assertGroup(g01, 14).assertNestedSet(g01_0_31,null, 0, 27, 3, 13,0)
		.assertNestedSet(g01_1_10,g01_0_31, 1, 10, 2, 4,1)
		.assertNestedSet(g01_11_12,g01_0_31, 11, 12, 0, 0,1)
		.assertNestedSet(g01_13_30,g01_0_31, 13, 26, 4, 6,1)
		.assertNestedSet(g01_2_3,g01_1_10, 2, 3, 0, 0,2)
		.assertNestedSet(g01_4_9,g01_1_10, 4, 9, 2, 2,2)
		.assertNestedSet(g01_5_6,g01_4_9, 5, 6, 0, 0,3)
		.assertNestedSet(g01_7_8,g01_4_9, 7, 8, 0, 0,3)
		.assertNestedSet(g01_14_17,g01_13_30, 14, 17, 1, 1,2)
		.assertNestedSet(g01_15_16,g01_14_17, 15, 16, 0, 0,3)
		.assertNestedSet(g01_18_19,g01_13_30, 18, 19, 0, 0,2)
		.assertNestedSet(g01_20_25,g01_13_30, 20, 21, 0, 0,2)
		.assertNestedSet(g01_26_29,g01_13_30, 22, 25, 1, 1,2)
		.assertNestedSet(g01_27_28,g01_26_29, 23, 24, 0, 0,3)
		;
		
		createNestedSets(null, g01_20_25,g01_21_22);
		assertGroup(g01, 15).assertNestedSet(g01_0_31,null, 0, 29, 3, 14,0)
		.assertNestedSet(g01_1_10,g01_0_31, 1, 10, 2, 4,1)
		.assertNestedSet(g01_11_12,g01_0_31, 11, 12, 0, 0,1)
		.assertNestedSet(g01_13_30,g01_0_31, 13, 28, 4, 7,1)
		.assertNestedSet(g01_2_3,g01_1_10, 2, 3, 0, 0,2)
		.assertNestedSet(g01_4_9,g01_1_10, 4, 9, 2, 2,2)
		.assertNestedSet(g01_5_6,g01_4_9, 5, 6, 0, 0,3)
		.assertNestedSet(g01_7_8,g01_4_9, 7, 8, 0, 0,3)
		.assertNestedSet(g01_14_17,g01_13_30, 14, 17, 1, 1,2)
		.assertNestedSet(g01_15_16,g01_14_17, 15, 16, 0, 0,3)
		.assertNestedSet(g01_18_19,g01_13_30, 18, 19, 0, 0,2)
		.assertNestedSet(g01_20_25,g01_13_30, 20, 23, 1, 1,2)
		.assertNestedSet(g01_26_29,g01_13_30, 24, 27, 1, 1,2)
		.assertNestedSet(g01_27_28,g01_26_29, 25, 26, 0, 0,3)
		.assertNestedSet(g01_21_22,g01_20_25, 21, 22, 0, 0,3)
		;
		
		createNestedSets(null, g01_20_25,g01_23_24);
		assertGroup(g01, 16).assertNestedSet(g01_0_31,null, 0, 31, 3, 15,0)
		.assertNestedSet(g01_1_10,g01_0_31, 1, 10, 2, 4,1)
		.assertNestedSet(g01_11_12,g01_0_31, 11, 12, 0, 0,1)
		.assertNestedSet(g01_13_30,g01_0_31, 13, 30, 4, 8,1)
		.assertNestedSet(g01_2_3,g01_1_10, 2, 3, 0, 0,2)
		.assertNestedSet(g01_4_9,g01_1_10, 4, 9, 2, 2,2)
		.assertNestedSet(g01_5_6,g01_4_9, 5, 6, 0, 0,3)
		.assertNestedSet(g01_7_8,g01_4_9, 7, 8, 0, 0,3)
		.assertNestedSet(g01_14_17,g01_13_30, 14, 17, 1, 1,2)
		.assertNestedSet(g01_15_16,g01_14_17, 15, 16, 0, 0,3)
		.assertNestedSet(g01_18_19,g01_13_30, 18, 19, 0, 0,2)
		.assertNestedSet(g01_20_25,g01_13_30, 20, 25, 2, 2,2)
		.assertNestedSet(g01_26_29,g01_13_30, 26, 29, 1, 1,2)
		.assertNestedSet(g01_27_28,g01_26_29, 27, 28, 0, 0,3)
		.assertNestedSet(g01_21_22,g01_20_25, 21, 22, 0, 0,3)
		.assertNestedSet(g01_23_24,g01_20_25, 23, 24, 0, 0,3)
		;
		
		__deleteEntityByBusinessIdentifier__(NestedSet.class, g01_0_31);
		assertGroup(g01, 0);
		
		//clean
		__deleteEntitiesAll__(NestedSet.class);
	}
	
	@Test
	public void delete_then_add_same_again(){
		String g01 = __getRandomCode__();
		String g01_0_7 = generateCode(g01,0);
		String g01_1_4 = generateCode(g01_0_7,0), g01_5_6 = generateCode(g01_0_7,1);
		String g01_2_3 = generateCode(g01_1_4,0);
		
		createNestedSets(g01, null, g01_0_7);
		createNestedSets(null, g01_0_7, g01_1_4,g01_5_6);
		createNestedSets(null, g01_1_4, g01_2_3);
		
		assertGroup(g01, 4);
		assertNestedSet(g01_0_7,null, 0, 7, 2, 3, 0);
		assertNestedSet(g01_1_4,g01_0_7, 1, 4, 1, 1, 1);
		assertNestedSet(g01_5_6,g01_0_7, 5, 6, 0, 0, 1);
		assertNestedSet(g01_2_3,g01_1_4, 2, 3, 0, 0, 2);
		
		__deleteEntityByBusinessIdentifier__(NestedSet.class, g01_2_3);
		
		assertGroup(g01, 3);
		assertNestedSet(g01_0_7,null, 0, 5, 2, 2, 0);
		assertNestedSet(g01_1_4,g01_0_7, 1, 2, 0, 0, 1);
		assertNestedSet(g01_5_6,g01_0_7, 3, 4, 0, 0, 1);
		
		createNestedSets(null, g01_1_4, g01_2_3);
		
		assertGroup(g01, 4);
		assertNestedSet(g01_0_7,null, 0, 7, 2, 3, 0);
		assertNestedSet(g01_1_4,g01_0_7, 1, 4, 1, 1, 1);
		assertNestedSet(g01_5_6,g01_0_7, 5, 6, 0, 0, 1);
		assertNestedSet(g01_2_3,g01_1_4, 2, 3, 0, 0, 2);
		
		__deleteEntityByBusinessIdentifier__(NestedSet.class, g01_2_3);
		
		assertGroup(g01, 3);
		assertNestedSet(g01_0_7,null, 0, 5, 2, 2, 0);
		assertNestedSet(g01_1_4,g01_0_7, 1, 2, 0, 0, 1);
		assertNestedSet(g01_5_6,g01_0_7, 3, 4, 0, 0, 1);
		
		createNestedSets(null, g01_5_6, g01_2_3);
		
		assertGroup(g01, 4);
		assertNestedSet(g01_0_7,null, 0, 7, 2, 3, 0);
		assertNestedSet(g01_1_4,g01_0_7, 1, 2, 0, 0, 1);
		assertNestedSet(g01_5_6,g01_0_7, 3, 6, 1, 1, 1);
		assertNestedSet(g01_2_3,g01_5_6, 4, 5, 0, 0, 2);
		
		__deleteEntityByBusinessIdentifier__(NestedSet.class, g01_2_3);
		
		assertGroup(g01, 3);
		assertNestedSet(g01_0_7,null, 0, 5, 2, 2, 0);
		assertNestedSet(g01_1_4,g01_0_7, 1, 2, 0, 0, 1);
		assertNestedSet(g01_5_6,g01_0_7, 3, 4, 0, 0, 1);
		
		createNestedSets(null, g01_1_4, g01_2_3);
		
		assertGroup(g01, 4);
		assertNestedSet(g01_0_7,null, 0, 7, 2, 3, 0);
		assertNestedSet(g01_1_4,g01_0_7, 1, 4, 1, 1, 1);
		assertNestedSet(g01_5_6,g01_0_7, 5, 6, 0, 0, 1);
		assertNestedSet(g01_2_3,g01_1_4, 2, 3, 0, 0, 2);
		/*
		moveNestedSet(g01_2_3,g01_5_6);
		
		assertGroup(g01, 4);
		assertNestedSet(g01_0_7,null, 0, 7, 2, 3, 0);
		assertNestedSet(g01_1_4,g01_0_7, 1, 2, 0, 0, 1);
		assertNestedSet(g01_5_6,g01_0_7, 3, 6, 1, 1, 1);
		assertNestedSet(g01_2_3,g01_5_6, 4, 5, 0, 0, 2);
		
		moveNestedSet(g01_2_3,g01_1_4);
		
		assertGroup(g01, 5);
		assertNestedSet(g01_0_7,null, 0, 7, 2, 3, 0);
		assertNestedSet(g01_1_4,g01_0_7, 1, 4, 1, 1, 1);
		assertNestedSet(g01_5_6,g01_0_7, 5, 6, 0, 0, 1);
		assertNestedSet(g01_2_3,g01_1_4, 2, 3, 0, 0, 2);
		*/
	}
	
	@Test
	public void move_2_3_to_5_6(){
		String g01 = __getRandomCode__();
		String g01_0_7 = generateCode(g01,0);
		String g01_1_4 = generateCode(g01_0_7,0), g01_5_6 = generateCode(g01_0_7,1);
		String g01_2_3 = generateCode(g01_1_4,0);
		
		createNestedSets(g01, null, g01_0_7);
		createNestedSets(null, g01_0_7, g01_1_4,g01_5_6);
		createNestedSets(null, g01_1_4, g01_2_3);
		
		assertGroup(g01, 4);
		assertNestedSet(g01_0_7,null, 0, 7, 2, 3, 0);
		assertNestedSet(g01_1_4,g01_0_7, 1, 4, 1, 1, 1);
		assertNestedSet(g01_5_6,g01_0_7, 5, 6, 0, 0, 1);
		assertNestedSet(g01_2_3,g01_1_4, 2, 3, 0, 0, 2);
		
		moveNestedSet(g01_2_3,g01_5_6);
		
		assertGroup(g01, 4);
		assertNestedSet(g01_0_7,null, 0, 7, 2, 3, 0);
		assertNestedSet(g01_1_4,g01_0_7, 1, 2, 0, 0, 1);
		assertNestedSet(g01_5_6,g01_0_7, 3, 6, 1, 1, 1);
		assertNestedSet(g01_2_3,g01_5_6, 4, 5, 0, 0, 2);
		
		moveNestedSet(g01_2_3,g01_1_4);
		
		assertGroup(g01, 4);
		assertNestedSet(g01_0_7,null, 0, 7, 2, 3, 0);
		assertNestedSet(g01_1_4,g01_0_7, 1, 4, 1, 1, 1);
		assertNestedSet(g01_5_6,g01_0_7, 5, 6, 0, 0, 1);
		assertNestedSet(g01_2_3,g01_1_4, 2, 3, 0, 0, 2);
	}
	
	@Test
	public void move_27_28_to_20_25(){
		String g01 = __getRandomCode__();
		String g01_0_31 = generateCode(g01,0), g01_1_10 = generateCode(g01_0_31,0), g01_11_12 = generateCode(g01_0_31,1), g01_13_30 = generateCode(g01_0_31,2);
		String g01_2_3 = generateCode(g01_1_10,0), g01_4_9 = generateCode(g01_1_10,1);
		String g01_14_17 = generateCode(g01_13_30,0), g01_18_19 = generateCode(g01_13_30,1),g01_20_25 = generateCode(g01_13_30,2), g01_26_29 = generateCode(g01_13_30,3);
		String g01_5_6 = generateCode(g01_4_9,0), g01_7_8 = generateCode(g01_4_9,1);
		String g01_15_16 = generateCode(g01_14_17,0);
		String g01_21_22 = generateCode(g01_20_25,0), g01_23_24 = generateCode(g01_20_25,1);
		String g01_27_28 = generateCode(g01_26_29,0);
		
		createNestedSets(g01, null, g01_0_31);
		createNestedSets(null, g01_0_31, g01_1_10,g01_11_12,g01_13_30);
		createNestedSets(null, g01_1_10, g01_2_3,g01_4_9);
		createNestedSets(null, g01_13_30, g01_14_17,g01_18_19,g01_20_25,g01_26_29);
		createNestedSets(null, g01_4_9, g01_5_6,g01_7_8);
		createNestedSets(null, g01_14_17, g01_15_16);
		createNestedSets(null, g01_20_25, g01_21_22,g01_23_24);
		createNestedSets(null, g01_26_29, g01_27_28);
		
		moveNestedSet(g01_27_28,g01_20_25);
		
		assertGroup(g01, 16);
		
		assertNestedSet(g01_0_31,null, 0, 31, 3, 15, 0);
		
		assertNestedSet(g01_1_10,g01_0_31, 1, 10, 2, 4, 1);
		assertNestedSet(g01_11_12,g01_0_31, 11, 12, 0, 0, 1);
		assertNestedSet(g01_13_30,g01_0_31, 13, 30, 4, 8, 1);

		assertNestedSet(g01_2_3,g01_1_10, 2, 3, 0, 0, 2);
		assertNestedSet(g01_4_9,g01_1_10, 4, 9, 2, 2, 2);
		assertNestedSet(g01_14_17,g01_13_30, 14, 17, 1, 1, 2);
		assertNestedSet(g01_18_19,g01_13_30, 18, 19, 0, 0, 2);
		assertNestedSet(g01_20_25,g01_13_30, 20, 27, 3, 3, 2);
		assertNestedSet(g01_26_29,g01_13_30, 28, 29, 0, 0, 2);
		
		assertNestedSet(g01_5_6,g01_4_9, 5, 6, 0, 0, 3);
		assertNestedSet(g01_7_8,g01_4_9, 7, 8, 0, 0, 3);
		assertNestedSet(g01_15_16,g01_14_17, 15, 16, 0, 0, 3);
		assertNestedSet(g01_21_22,g01_20_25, 21, 22, 0, 0, 3);
		assertNestedSet(g01_23_24,g01_20_25, 23, 24, 0, 0, 3);
		assertNestedSet(g01_27_28,g01_20_25, 25, 26, 0, 0, 3);
		
		//Move it back
		moveNestedSet(g01_27_28,g01_26_29);
		
		assertGroup(g01, 16);
		
		assertNestedSet(g01_0_31,null, 0, 31, 3, 15, 0);
		
		assertNestedSet(g01_1_10,g01_0_31, 1, 10, 2, 4, 1);
		assertNestedSet(g01_11_12,g01_0_31, 11, 12, 0, 0, 1);
		assertNestedSet(g01_13_30,g01_0_31, 13, 30, 4, 8, 1);

		assertNestedSet(g01_2_3,g01_1_10, 2, 3, 0, 0, 2);
		assertNestedSet(g01_4_9,g01_1_10, 4, 9, 2, 2, 2);
		assertNestedSet(g01_14_17,g01_13_30, 14, 17, 1, 1, 2);
		assertNestedSet(g01_18_19,g01_13_30, 18, 19, 0, 0, 2);
		assertNestedSet(g01_20_25,g01_13_30, 20, 25, 2, 2, 2);
		assertNestedSet(g01_26_29,g01_13_30, 26, 29, 1, 1, 2);
		
		assertNestedSet(g01_5_6,g01_4_9, 5, 6, 0, 0, 3);
		assertNestedSet(g01_7_8,g01_4_9, 7, 8, 0, 0, 3);
		assertNestedSet(g01_15_16,g01_14_17, 15, 16, 0, 0, 3);
		assertNestedSet(g01_21_22,g01_20_25, 21, 22, 0, 0, 3);
		assertNestedSet(g01_23_24,g01_20_25, 23, 24, 0, 0, 3);
		assertNestedSet(g01_27_28,g01_26_29, 27, 28, 0, 0, 3);
	}
	
	/**/
	
	private NestedSetBusinessIntegrationTest assertGroup(String code,Integer numberOfNestedSet){
		Assert.assertEquals("Number of nested set in group is not correct",new Long(numberOfNestedSet),__inject__(NestedSetPersistence.class).countByGroup(code));
		return this;
	}
	
	private NestedSetBusinessIntegrationTest assertNestedSet(String code,String parentCode,Integer leftIndex,Integer rightIndex,Integer numberOfChildren,Integer numberOfDescendant,Integer numberOfAscendant){
		NestedSet nestedSet = __inject__(NestedSetPersistence.class).readOneByBusinessIdentifier(code);
		if(parentCode!=null)
			Assert.assertEquals("Parent is not correct",parentCode,nestedSet.getParent().getCode());
		assertionHelper.assertEqualsNumber("Left index is not correct", leftIndex, nestedSet.getLeftIndex());
		assertionHelper.assertEqualsNumber("Right index is not correct", rightIndex, nestedSet.getRightIndex());
		Assert.assertEquals("Number of children from count is not correct",new Long(numberOfChildren),__inject__(NestedSetPersistence.class).countByParent(nestedSet));
		Assert.assertEquals("Number of children from get is not correct",new Long(numberOfChildren),new Long(nestedSet.getNumberOfChildren()));
		Assert.assertEquals("Number of descendant from count is not correct",new Long(numberOfDescendant),__inject__(NestedSetPersistence.class).countByGroupWhereLeftIndexAndRightIndexBetween(nestedSet));
		Assert.assertEquals("Number of descendant from get is not correct",new Long(numberOfDescendant),new Long(nestedSet.getNumberOfDescendant()));
		Assert.assertEquals("Number of ascendant from count is not correct",new Long(numberOfAscendant),__inject__(NestedSetPersistence.class).countByGroupWhereLeftIndexAndRightIndexContain(nestedSet));
		Assert.assertEquals("Number of ascendant from get is not correct",new Long(numberOfAscendant),new Long(nestedSet.getNumberOfAscendant()));
		return this;
	}
	
	private void createNestedSets(String group,String parentCode,String...children){
		for(String index : children){
			NestedSet nestedSet = new NestedSet().setCode(index).setGroup(group).setFromBusinessIdentifier(NestedSet.FIELD_PARENT,parentCode);
			__createEntity__(nestedSet);
		}
	}
	
	private void moveNestedSet(String code,String parentCode){
		NestedSet nestedSet = __readEntity__(NestedSet.class, code, ValueUsageType.BUSINESS);
		nestedSet.setParentFromCode(parentCode);
		__updateEntity__(nestedSet);
	}
	
}
